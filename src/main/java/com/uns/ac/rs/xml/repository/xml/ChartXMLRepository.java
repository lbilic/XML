package com.uns.ac.rs.xml.repository.xml;

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
import com.uns.ac.rs.xml.domain.enums.UserType;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Repository
public class ChartXMLRepository extends IOStreamer {

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private DoctorXMLRepository doctorXMLRepository;

    @Autowired
    private Operations operations;

    @Autowired
    private ConstraintsRepository constraintsRepository;

    @Autowired
    private MetadataGenerator metadataGenerator;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Validator validator;

    public String getAll() {
        return operations.getAll("charts",
                "getAllCharts");
    }

    public String findById(String id) {
        return operations.findById(id, "charts", "findChartById");
    }

    public String getDocuments(String id) {
        return operations.findById(id, "charts", "getAllDocuments");
    }

    public void delete(String id) {
        String path = constraintsRepository.findPaths(id, "charts",
                "Chart does not exist!");
        String prefix = path.split("/")[2].split(":")[0];
        String prePrefix = path.split("/")[1].split(":")[0];

        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("charts"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("delete")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("chart"), path,
                    mapper.getPrefix("charts").replace(":charts",
                            ":" + prePrefix));
            long mods = xupdateService.updateResource(mapper.getDocument("charts"), queryContent);

            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }

            path = String.format(mapper.getPath("deleteLinkedPrescriptions"), id);
            operations.delete("prescriptions", "prescription", "prescription", path);
            path = String.format(mapper.getPath("deleteLinkedReferrals"), id);
            operations.delete("referrals", "referral", "referral", path);
            path = String.format(mapper.getPath("deleteLinkedReports"), id);
            operations.delete("reports", "report", "report", path);
            path = String.format(mapper.getPath("deleteLinkedChoices"), id);
            operations.delete("choices", "choice", "choice", path);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public String editChart(com.uns.ac.rs.xml.util.actions.Action action) {
        validator.processAction(action, mapper.getScheme("chart"));
        Document doc = mapper.convertToDocument(action);
        String id = doc.getFirstChild().getLastChild().getFirstChild().getAttributes()
                .getNamedItem("id").getNodeValue();
        String doctorId = doc.getFirstChild().getLastChild().getFirstChild().getChildNodes().item(1)
                .getAttributes().item(0).getNodeValue();
        doctorXMLRepository.findById(doctorId);
        DatabaseResources resources = null;
        String backup = this.findById(id);
        String path = constraintsRepository.findPaths(id, "charts",
                "Chart does not exist!");
        String prefix = path.split("/")[2].split(":")[0];
        String prePrefix = path.split("/")[1].split(":")[0];
        String edit;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Node chart = doc.getFirstChild().getLastChild().getFirstChild();
            doc = factory.newDocumentBuilder().newDocument();
            Node imported = doc.importNode(chart, true);
            doc.appendChild(imported);

            metadataGenerator.addPersonMetadata(doc, UserType.PATIENT, id);

            this.physicalDelete(prefix, prePrefix, path);
            constraintsRepository.checkPatientConstraints(doc);
            edit = mapper.convertToString(doc);

            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("charts"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("chart"),
                    mapper.getPath("charts"), edit,
                    mapper.getPrefix("charts"));
            long mods = xupdateService.updateResource(mapper.getDocument("charts"), queryContent);

            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        } catch (ParserConfigurationException e) {
            throw new TransformerException("Error while processing data");
        } catch (ValidationException e) {
            this.addChart(backup, prefix);
            throw new ValidationException(e.getMessage());
        }
        this.editUser(doc, id);
        return edit;
    }


    public void editChosenDoctor(String chart, String doctor) {
        DatabaseResources resources = null;
        String path = constraintsRepository.findPaths(chart, "charts",
                "Chart does not exist!");
        String prefix = path.split("/")[2].split(":")[0];
        String prePrefix = path.split("/")[1].split(":")[0];
        String backup = this.findById(chart);
        String edit;
        try {
            this.physicalDelete(prefix, prePrefix, path);
            Document doc = mapper.convertToDocument(backup);
            NodeList elements = doc.getFirstChild().getChildNodes();
            Element element;
            for (int i = 0; i < elements.getLength(); i++) {
                try {
                    element = (Element) elements.item(i);
                    if ("chosen_doctr".equals(element.getTagName().split(":")[1])) {
                        element.setAttribute(prefix + ":identifier", doctor);
                        element.setAttribute("href", doctor);
                        break;
                    }
                } catch (Exception ignored) {
                }
            }
            edit = mapper.convertToString(doc);

            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("charts"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("chart"),
                    mapper.getPath("charts"), edit,
                    mapper.getPrefix("charts"));
            long mods = xupdateService.updateResource(mapper.getDocument("charts"), queryContent);

            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        } catch (ValidationException e) {
            this.addChart(backup, prefix);
            throw new ValidationException(e.getMessage());
        }
    }

    public String generalSearch(String text, String query) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("doctors"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery(query)).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection()
                    .getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), text);
            CompiledExpression compiledXquery = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledXquery);
            ResourceIterator i = result.getIterator();
            Resource res = null;

            StringBuilder sb = new StringBuilder();

            while (i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    sb.append(res.getContent().toString());
                    sb.append("-");
                } finally {
                    if (res != null)
                        ((EXistResource) res).freeResources();

                }
            }
            String prescriptions = sb.toString();
            connection.freeResources(resources);
            if (prescriptions.isEmpty()) {
                throw new ValidationException("Chart with text: " + text + " does not exist!");
            } else {
                return prescriptions;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private void editUser(Document document, String id) {
        String jmbg = document.getFirstChild().getAttributes().getNamedItem("jmbg").getNodeValue();
        String name = document.getFirstChild().getFirstChild().getChildNodes().item(0).getTextContent();
        String surname = document.getFirstChild().getFirstChild().getChildNodes().item(1).getTextContent();

        Document user = mapper.convertToDocument(getUser(id));
        String userId = user.getFirstChild().getAttributes().getNamedItem("id").getNodeValue();
        NodeList elements = user.getFirstChild().getChildNodes();
        Element element;
        for (int i = 0; i < elements.getLength(); i++) {
            try {
                element = (Element) elements.item(i);
                switch (element.getTagName()) {
                    case "user:jmbg":
                        element.setTextContent(jmbg);
                        break;
                    case "user:name":
                        element.setTextContent(name);
                        break;
                    case "user:surname":
                        element.setTextContent(surname);
                        break;
                }
            } catch (Exception ignored) {
            }

        }

        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("users"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("edit")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    "user", mapper.getPrefix("user"),
                    constraintsRepository.findPaths(userId, "users",
                            "User does not exist"),
                    mapper.createXMLFromNodes(user.getFirstChild().getChildNodes()), mapper.getPrefix("users"));
            long mods = xupdateService.updateResource(mapper.getDocument("users"), queryContent);
			
            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }
        } catch (Exception e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private void physicalDelete(String prefix, String prePrefix, String path) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("charts"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("physicalDelete")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("chart"), path,
                    mapper.getPrefix("charts").replace(":charts",
                            ":" + prePrefix));
            long mods = xupdateService.updateResource(mapper.getDocument("charts"), queryContent);

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

    private void addChart(String chart, String prefix) {
        DatabaseResources resources = null;
        try {
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();
            String queryContent;
            String document;
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("charts"));
            queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("chart"),
                    mapper.getPath("charts"), chart,
                    mapper.getPrefix("charts"));
            document = mapper.getDocument("charts");

            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            long mods = xupdateService.updateResource(document, queryContent);

            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
                | XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private String getUser(String chartId) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("charts"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("findUserByChart"))
                    .getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), chartId);
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
            String user = sb.toString();
            connection.freeResources(resources);
            if (user.isEmpty()) {
                throw new ValidationException("User does not exist!");
            } else {
                return user;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }


}
