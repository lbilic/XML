package com.uns.ac.rs.xml.repository.rdf;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import com.uns.ac.rs.xml.util.ConfigureConnection;
import com.uns.ac.rs.xml.util.Mapper;
import com.uns.ac.rs.xml.util.MetadataExtractor;
import com.uns.ac.rs.xml.util.SPARQLMapper;

import java.io.*;

@Repository
public class RDFRepository {

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private MetadataExtractor extractor;

    @Autowired
    private Mapper mapper;

    @Autowired
    private SPARQLMapper sparqlMapper;

    public void save(String content, String graph, boolean postProcessing) {

        ByteArrayInputStream rdf = extractor.extractMetadata(new ByteArrayInputStream(content.getBytes()),
                new ByteArrayOutputStream());
        Model model = ModelFactory.createDefaultModel();
        model.read(rdf, null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        if (postProcessing) {
            this.postProcessing(model, content);
        }
        model.write(output, SPARQLMapper.NTRIPLES);
        String sparqlQuery = sparqlMapper.enterData(connection.getDataEndpoint() + "/" + graph,
                new String(output.toByteArray()));
        System.out.println(sparqlQuery);
        UpdateRequest edit = UpdateFactory.create(sparqlQuery);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(edit, connection.getUpdateEndpoint());
        processor.execute();
    }

    public void edit(String content, String graph, boolean postProcessing) {

        ByteArrayInputStream rdf = extractor.extractMetadata(new ByteArrayInputStream(content.getBytes()),
                new ByteArrayOutputStream());
        Model model = ModelFactory.createDefaultModel();
        model.read(rdf, null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        if (postProcessing) {
            this.postProcessing(model, content);
        }
        model.write(output, SPARQLMapper.NTRIPLES);
        String sparqlQuery = sparqlMapper.switchData(connection.getDataEndpoint() + "/" + graph,
                getId(content), new String(output.toByteArray()));
        System.out.println(sparqlQuery);
        UpdateRequest edit = UpdateFactory.create(sparqlQuery);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(edit, connection.getUpdateEndpoint());
        processor.execute();
    }

    public void delete(String graph, String node) {
        String sparqlQuery = sparqlMapper.deleteData(connection.getDataEndpoint() + "/" + graph, node);
        System.out.println(sparqlQuery);
        UpdateRequest edit = UpdateFactory.create(sparqlQuery);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(edit, connection.getUpdateEndpoint());
        processor.execute();
    }

    public void editChartField(String chart, String field, String value) {
        String sparqlQuery = sparqlMapper.switchField(connection.getDataEndpoint() + "/" +
                mapper.getGraph("charts"), chart, field, value);
        System.out.println(sparqlQuery);
        UpdateRequest edit = UpdateFactory.create(sparqlQuery);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(edit, connection.getUpdateEndpoint());
        processor.execute();
    }

    private void postProcessing(Model model, String chart) {
        Document doc = mapper.convertToDocument(chart);
        String id = doc.getFirstChild().getAttributes().getNamedItem("id").getNodeValue();
        String jmbg = doc.getFirstChild().getAttributes().getNamedItem("jmbg").getNodeValue();
        String lbo = doc.getFirstChild().getAttributes().getNamedItem("lbo").getNodeValue();
        String health_card_number = doc.getFirstChild().getAttributes().getNamedItem("health_card_number").getNodeValue();
        String chart_number = doc.getFirstChild().getAttributes().getNamedItem("chart_number").getNodeValue();
        String voc = mapper.getURI("vocabulary");
        Resource root = model.getResource(id);

        Property jm = model.createProperty(voc + "jmbg");
        Property lb = model.createProperty(voc + "lbo");
        Property hc_no = model.createProperty(voc + "health_card_number");
        Property ch_no = model.createProperty(voc + "chart_number");
        root.addProperty(jm, jmbg);
        root.addProperty(lb, lbo);
        root.addProperty(hc_no, health_card_number);
        root.addProperty(ch_no, chart_number);
    }

    private String getId(String content) {
        Document doc = mapper.convertToDocument(content);
        return doc.getFirstChild().getAttributes().getNamedItem("id").getNodeValue();
    }


    public String exportMetadata(String document, String format) {
        String end = connection.getDataEndpoint();
        String conditions = "?s ?p ?o";
        String sparqlQuery = sparqlMapper.sedrugtujPodatke(end + "/" + document, conditions);

        QueryExecution query = QueryExecutionFactory.sparqlService(connection.getQueryEndpoint(), sparqlQuery);

        ResultSet results = query.execSelect();

        try {
            String put = "./exports/" + document + "-" + format + ".txt";
            File file = new File(put);
            //noinspection ResultOfMethodCallIgnored
            file.getParentFile().mkdirs();
            FileOutputStream out =
                    new FileOutputStream(file);

            PrintWriter pw = new PrintWriter(new FileWriter(put));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            String retVal;
            if (format.equals("json")) {
                ResultSetFormatter.outputAsJSON(baos, results);
                retVal = baos.toString();
            } else {
                ResultSetFormatter.outputAsXML(baos, results);
                retVal = baos.toString();
            }
            pw.write(retVal);

            out.close();
            pw.close();
            return retVal;

        } catch (IOException e) {
            return "Error while exporting metadata";
        }
    }
}