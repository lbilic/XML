package com.uns.ac.rs.xml.repository.xml;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;
import com.uns.ac.rs.xml.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@Repository
public class ConstraintsRepository extends IOStreamer {

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private Mapper mapper;

    @Autowired
    private DoctorXMLRepository doctorXMLRepository;

	
    public void checkUserConstraints(Document document, String prefix) {
        String username = document.getElementsByTagName(prefix + ":korisnicko_name")
                .item(0).getTextContent();
        String jmbg = document.getElementsByTagName(prefix + ":jmbg").item(0).getTextContent();

        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("users"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("userRestraints")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), jmbg, username);
            CompiledExpression compiledQueryContent = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledQueryContent);
            ResourceIterator i = result.getIterator();
            Resource res = null;

            StringBuilder sb = new StringBuilder();

            while (i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    sb.append(DocumentBuilderFactory.newInstance().newDocumentBuilder()
                            .parse(new InputSource(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                                    + res.getContent().toString()))).getFirstChild().getTextContent());
                } finally {
                    if (res != null)
                        ((EXistResource) res).freeResources();
                }
            }
            String error = sb.toString();
            connection.freeResources(resources);
            if (!error.isEmpty()) {
                throw new ValidationException(error);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException | ParserConfigurationException | SAXException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }


    public void checkPatientConstraints(Document document) {
        NodeList elements = document.getFirstChild().getChildNodes();
        for (int i = 0; i < elements.getLength(); i++) {
            if (elements.item(i).getLocalName().equals("chosen_doctr")) {
                doctorXMLRepository.findById(elements.item(i).getAttributes().item(0).getNodeValue());
                break;
            }
        }
        String jmbg = document.getFirstChild().getAttributes().getNamedItem("jmbg").getNodeValue();
        String lbo = document.getFirstChild().getAttributes().getNamedItem("lbo").getNodeValue();
        String chart_number = document.getFirstChild().getAttributes().getNamedItem("chart_number")
                .getNodeValue();
        String health_card_number = document.getFirstChild().getAttributes().getNamedItem("health_card_number")
                .getNodeValue();

        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("charts"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("chartConstraints")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    jmbg, health_card_number, chart_number, lbo);
            CompiledExpression compiledQueryContent = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledQueryContent);
            ResourceIterator i = result.getIterator();
            Resource res = null;

            StringBuilder sb = new StringBuilder();

            while (i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    sb.append(DocumentBuilderFactory.newInstance().newDocumentBuilder()
                            .parse(new InputSource(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                                    + res.getContent().toString()))).getFirstChild().getTextContent());
                } finally {
                    if (res != null)
                        ((EXistResource) res).freeResources();
                }
            }
            String error = sb.toString();
            connection.freeResources(resources);
            if (!error.isEmpty()) {
                throw new ValidationException(error);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException | ParserConfigurationException | SAXException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public void checkChoiceRestraints(String chart, String previousDoctor, String doctor) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("choices"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("choiceConstraints")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), chart,
                    previousDoctor, doctor);
            CompiledExpression compiledQueryContent = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledQueryContent);
            ResourceIterator i = result.getIterator();
            Resource res = null;

            StringBuilder sb = new StringBuilder();

            while (i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    sb.append(res.getContent().toString());
                } finally {
                    if (res != null)
                        ((EXistResource) res).freeResources();
                }
            }
            String error = sb.toString();
            connection.freeResources(resources);
            if (!error.isEmpty()) {
                throw new ValidationException(error);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public String findPaths(String id, String document, String error) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument(document));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("getPaths")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    mapper.getCollection() + mapper.getDocument(document), id);
            CompiledExpression compiledQueryContent = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledQueryContent);
            ResourceIterator i = result.getIterator();
            Resource res = null;
            String rez;

            try {
                res = i.nextResource();
                rez = (String) res.getContent();
            } finally {
                if (res != null)
                    ((EXistResource) res).freeResources();
            }

            connection.freeResources(resources);
            if (rez.equals("/")) {
                throw new ValidationException(error);
            }
            return rez;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }
}
