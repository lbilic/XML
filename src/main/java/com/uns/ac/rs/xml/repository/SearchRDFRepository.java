package com.uns.ac.rs.xml.repository;

import com.uns.ac.rs.xml.util.database.ConfigureConnection;
import com.uns.ac.rs.xml.util.database.Mapper;
import com.uns.ac.rs.xml.util.database.SPARQLMapper;
import com.uns.ac.rs.xml.util.validator.ValidationException;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.uns.ac.rs.xml.domain.QueryParameter;
import com.uns.ac.rs.xml.domain.SearchQuery;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

@Repository
public class SearchRDFRepository {

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private Mapper mapper;

    @Autowired
    private SPARQLMapper sparqlMapper;

    public SearchRDFRepository() {
    }

    public String generalQuery(SearchQuery searchQuery) {

        String conditions = constructQuery(searchQuery);
        String sparqlQuery = sparqlMapper.selectFilter(conditions);
        QueryExecution query = QueryExecutionFactory.sparqlService(connection.getQueryEndpoint(), sparqlQuery);
        ResultSet results = query.execSelect();

        String name;
        RDFNode value;
        StringBuilder rez = new StringBuilder();

        while (results.hasNext()) {

            QuerySolution result = results.next();
            Iterator<String> bindings = result.varNames();

            while (bindings.hasNext()) {
                name = bindings.next();
                value = result.get(name);
                rez.append(value.toString());
                rez.append("-");
                System.out.println(name + ": " + value);
            }
            System.out.println();
        }
        try {
            rez.deleteCharAt(rez.length() - 1);
            query.close();
            return rez.toString();
        } catch (StringIndexOutOfBoundsException e) {
            query.close();
            return "";
        }
    }

    public String linksToDocument(String id) {

        String sparqlQuery = sparqlMapper.selectLinks(id);
        QueryExecution query = QueryExecutionFactory.sparqlService(connection.getQueryEndpoint(), sparqlQuery);
        ResultSet results = query.execSelect();

        String name;
        RDFNode value;

        StringBuilder rez = new StringBuilder();
        while (results.hasNext()) {

            QuerySolution result = results.next();
            Iterator<String> bindings = result.varNames();
            while (bindings.hasNext()) {
                name = bindings.next();
                value = result.get(name);
                rez.append(value.toString());
                rez.append("-");
                System.out.println(name + ": " + value);
            }
            System.out.println();
        }

        rez.deleteCharAt(rez.length() - 1);
        query.close();
        return rez.toString();
    }

    private String constructQuery(SearchQuery searchQuery) {
        StringBuilder sb = new StringBuilder();
        boolean processedFirst = false;
        int numberOfQueries = searchQuery.getQueryParameters().size();
        String operator;
        int j = 0;
        if (numberOfQueries == 1) {
            sb.append("{");
        } else {
            for (int i = 0; i < numberOfQueries - 1; i++) {
                sb.append("{");
            }
        }
        for (QueryParameter parametar :
                searchQuery.getQueryParameters()) {
            sb.append("{ ");
            sb.append(" ?s ");
            sb.append("voc:");
            sb.append(parametar.getAttributeName());
            sb.append(" \"");
            if (parametar.getAttributeName().equals("date") ||
                    parametar.getAttributeName().equals("birthDate")) {
                sb.append(parametar.getValue());
                sb.append("\"^^xsd:date");
            } else {
                sb.append(parametar.getValue());
                sb.append("\"");
            }


            if (!processedFirst) {
                sb.append("} ");
                processedFirst = true;
            } else {
                sb.append("}} ");
            }
            if (parametar.getOperator().equals("AND")) {
                operator = ". ";
            } else if (parametar.getOperator().equals("OR")) {
                operator = " UNION ";
            } else {
                throw new ValidationException("Invalid operator passed!");
            }
            if (j != numberOfQueries - 1) {
                sb.append(operator);
            }

            sb.append(" ");
            ++j;
        }
        return sb.toString();
    }

    private String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
