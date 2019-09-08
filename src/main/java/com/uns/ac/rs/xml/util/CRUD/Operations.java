package com.uns.ac.rs.xml.util.CRUD;

import com.uns.ac.rs.xml.util.database.ConfigureConnection;
import com.uns.ac.rs.xml.util.database.DatabaseConnectionException;
import com.uns.ac.rs.xml.util.database.DatabaseResources;
import com.uns.ac.rs.xml.util.database.Mapper;
import com.uns.ac.rs.xml.util.transformators.TransformerException;
import com.uns.ac.rs.xml.util.validator.ValidationException;
import com.uns.ac.rs.xml.util.validator.Validator;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;
import com.uns.ac.rs.xml.repository.RDFRepository;
import com.uns.ac.rs.xml.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class Operations {

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Validator validator;

    @Autowired
    private Sequencer sequencer;

    @Autowired
    private MetadataGenerator metadataGenerator;

    @Autowired
    private RDFRepository rdfRepository;

    public String getAll(String document, String queryPath) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument(document));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery(queryPath)).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection()
                    .getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = this.loadFileContents(pathToQuery);
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
            String rez = sb.toString();
            connection.freeResources(resources);
            if (rez.isEmpty()) {
                String name = document.substring(0, document.length() - 1);
                throw new ValidationException("No " + name + " in database!");
            } else {
                return rez;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException | NullPointerException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }


    public String findById(String id, String document, String queryPath) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument(document));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery(queryPath)).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection()
                    .getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), id);
            CompiledExpression compiledXquery = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledXquery);
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
            String resultString = sb.toString();
            connection.freeResources(resources);
            if (resultString.isEmpty()) {
                String name = document.substring(0, document.length() - 1);
                throw new ValidationException(name + " not found in database!");
            } else {
                return resultString;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action, String document, String documentPrefix, String queryPath) {
        String id = action.getContext();
        String prescription = findById(id, document, queryPath);
        String prefix = mapper.convertToDocument(prescription).getFirstChild().getNodeName().split(":")[0];

        String pathToDrug = findPrescription(id, document);
        DatabaseResources resources = null;

        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument(document));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("delete")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix(documentPrefix), pathToDrug,
                    mapper.getPrefix(document));
            long mods = xupdateService.updateResource(mapper.getDocument(document), queryContent);

            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }

            rdfRepository.delete(mapper.getGraph(document), id);

            return documentPrefix + " successfully deleted!";
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public void delete(String document, String prefix, String documentPrefix, String path) {
        DatabaseResources resources = null;

        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument(document));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("delete")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix(documentPrefix), path,
                    mapper.getPrefix(document));
            long mods = xupdateService.updateResource(mapper.getDocument(document), queryContent);

            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public String save(Node node, String document, String documentPrefix) {
        String entityContent = validator.processAction(node, mapper.getScheme(documentPrefix));

        String prefix = mapper.convertToDocument(entityContent).getFirstChild().getNodeName().split(":")[0];
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument(document));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            Long id = sequencer.getId();

            String newId = mapper.getURI(documentPrefix) + id.toString();
            Document addedId = this.emplaceId(mapper.convertToDocument(entityContent).getFirstChild(), id, documentPrefix);
            Document nodesWithMeta = addMetadata(prefix, addedId, newId);

            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix(documentPrefix), mapper.getPath(document), mapper.convertToString(nodesWithMeta),
                    mapper.getPrefix(document));
            long mods = xupdateService.updateResource(mapper.getDocument(document), queryContent);

            if (mods == 0) {
                throw new DatabaseConnectionException("Error while creating " + documentPrefix);
            }

            connection.freeResources(resources);
            return mapper.convertToString(nodesWithMeta);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action, String document, String documentPrefix) {

        String entity = validator.processAction(action, mapper.getScheme(documentPrefix));
        Node node = mapper.convertToDocument(entity).getFirstChild();
        Element el = (Element) node;
        String id = el.getAttribute("id");


        String prefix = mapper.convertToDocument(entity).getFirstChild().getNodeName().split(":")[0];

        NodeList entityNodes = mapper.convertToDocument(entity).getFirstChild().getChildNodes();
        NodeList nodesWithMeta = addMetadataEdit(prefix, entityNodes, id);
        String entityContent;
        DatabaseResources resources = null;
        try {
            entityContent = mapper.createXMLFromNodes(nodesWithMeta);
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument(document));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("edit")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix(documentPrefix), findPrescription(id, document), entityContent,
                    mapper.getPrefix(document));
            long mods = xupdateService.updateResource(mapper.getDocument(document), queryContent);

            connection.freeResources(resources);
            String name = document.substring(0, document.length() - 1);

            String result = "<" + el.getTagName() + " id=\"" + id + "\" about=\"" + id + "\" "
                    + "xmlns:" + prefix + "=\"" + mapper.getPrefix(documentPrefix) + "\">" +
                    entityContent
                    + "</" + el.getNodeName() + ">";

            String newResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    result.trim().replaceFirst(" ", "  " + mapper.getPrefix("vocabulary")
                            + mapper.getPrefix("xmlScheme"));
            rdfRepository.edit(newResult, mapper.getGraph(document), false);

            return "Successfully edited " + name + "!";
        } catch (Exception e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private Document emplaceId(Node node, Long id, String document) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            Document doc = factory.newDocumentBuilder().newDocument();
            Node imported = doc.importNode(node, true);
            doc.appendChild(imported);

            ((Element) doc.getFirstChild()).setAttribute("id", mapper.getURI(document) + id);

            return doc;
        } catch (ParserConfigurationException e) {
            throw new TransformerException("Data processing not possible!");
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    private String findPrescription(String id, String document) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument(document));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("getPaths")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection()
                    .getService("XQueryService", "1.0");
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
                throw new ValidationException(document.substring(0, document.length() - 1)
                        + " does not exist!");
            }
            return rez;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }


    private NodeList addMetadataEdit(String entity, NodeList document, String id) {
        switch (entity) {
            case "report":
                metadataGenerator.addReportMetadata(document, id);
                break;
            case "referral":
                metadataGenerator.addPrescriptionMetadata(document, id);
                break;
            case "prescription":
                metadataGenerator.addDrugMetadata(document, id);
                break;
            case "drug":
                metadataGenerator.addDrugMetadata(document, id);
        }
        return document;
    }

    private Document addMetadata(String entity, Document document, String id) {
        switch (entity) {
            case "report":
                metadataGenerator.addReportMetadata(document.getFirstChild().getChildNodes(), id);
                break;
            case "referral":
                metadataGenerator.addReferralMetadata(document.getFirstChild().getChildNodes(), id);
                break;
            case "prescription":
                metadataGenerator.addPrescriptionMetadata(document.getFirstChild().getChildNodes(), id);
                break;
            case "drug":
                metadataGenerator.addDrugMetadata(document.getFirstChild().getChildNodes(), id);
            case "choice":
                metadataGenerator.addChoiceMetadata(document.getFirstChild().getChildNodes(), id);
        }
        return document;
    }

    public String getDrugForDiagnosis(String diagnosis, String patientId) {

        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("doctors"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("getDrugForDiagnosis")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection()
                    .getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), diagnosis, patientId);
            CompiledExpression compiledXquery = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledXquery);
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
            String prescriptions = sb.toString();
            connection.freeResources(resources);
            if (prescriptions.isEmpty()) {
                throw new ValidationException("No drug with diagnosis: " + diagnosis
                        + "to witch patient with id: " + patientId + " isn't allergic");
            } else {
                return prescriptions;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
