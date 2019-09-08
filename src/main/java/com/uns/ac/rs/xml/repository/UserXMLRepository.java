package com.uns.ac.rs.xml.repository;

import com.uns.ac.rs.xml.util.database.ConfigureConnection;
import com.uns.ac.rs.xml.util.database.DatabaseConnectionException;
import com.uns.ac.rs.xml.util.database.DatabaseResources;
import com.uns.ac.rs.xml.util.database.Mapper;
import com.uns.ac.rs.xml.util.transformators.TransformerException;
import com.uns.ac.rs.xml.util.validator.ValidationException;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;
import com.uns.ac.rs.xml.domain.DTO.Login;
import com.uns.ac.rs.xml.domain.enums.UserType;
import com.uns.ac.rs.xml.util.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@Repository
public class UserXMLRepository {

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private ChartXMLRepository chartXMLRepository;

    @Autowired
    private ConstraintsRepository constraintsRepository;

    @Autowired
    private MetadataGenerator metadataGenerator;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Sequencer sequencer;

    public Login login(String username, String password) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("users"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("login")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection()
                    .getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), username, password);
            CompiledExpression compiledQueryContent = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledQueryContent);
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
            connection.freeResources(resources);
            if (sb.toString().isEmpty()) {
                throw new ValidationException("Incorrect username or password!");
            } else {
                return new Login(sb.toString());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public String[] register(com.uns.ac.rs.xml.util.actions.Action action) {
        Document doc = mapper.convertToDocument(action);
        if (doc == null) {
            throw new TransformerException("Data processing not possible!");
        }
        return this.register(doc);
    }

    public String delete(String id) {
        String path = constraintsRepository.findPaths(id, "users",
                "User does not exist!");
        String prefix = path.split("/")[2].split(":")[0];
        String chartId = this.getChart(id);
        if (!chartId.isEmpty()) {
            chartXMLRepository.delete(chartId);
        }

        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("users"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("delete")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("user"), path,
                    mapper.getPrefix("users"));
            long mods = xupdateService.updateResource(mapper.getDocument("users"), queryContent);

            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }
            return chartId;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public void addNotification(String id, String text) {
        String path = constraintsRepository.findPaths(id, "patients",
                "Patient does not exist!");
        String prefix = path.split("/")[2].split(":")[0];

        path += "/" + prefix + ":notifications";

        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("patients"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("patient"), path, endNotification(text),
                    mapper.getPrefix("patients"));
            long mods = xupdateService.updateResource(mapper.getDocument("patients"), queryContent);

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

    public String findNotificationsByUser(String id) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("charts"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("findNotificationsByUser"))
                    .getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), id);
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
                throw new ValidationException("Patient does not exist in database!");
            } else {
                return rez;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private String getChart(String id) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("charts"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("findChartByUser"))
                    .getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), id);
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
            return rez;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private String[] register(Document document) {
        Node content = document.getElementsByTagName("content").item(0);

        if (!content.hasChildNodes()) {
            throw new ValidationException("Invalid action content, sadzaj actions je prazan!");
        }
        Node user = this.getUser(content.getFirstChild().getChildNodes());
        Node person = this.getPerson(content.getFirstChild().getChildNodes());
        if (user == null || person == null) {
            throw new ValidationException("Invalid action content, sadrzi neodgovarajuce entitye!");
        }
        String userPrefiks = user.getNodeName().split(":")[0];
        String personPrefiks = person.getNodeName().split(":")[0];
        Long userId = sequencer.getId();
        Long personId = sequencer.getId();
        String per = this.validateUser(user, userId, userPrefiks);
        String psn;
        if (person.getLocalName().equals("doctor")) {
            psn = this.validatePerson(person, mapper.getScheme("doctor"),
                    mapper.getURI("doctor"), personPrefiks, userId, UserType.DOCTOR, personId);
            this.saveUser(per, userPrefiks);
            this.savePerson(psn, personPrefiks, UserType.DOCTOR);
        } else if (person.getLocalName().equals("nurse")) {
            psn = this.validatePerson(person, mapper.getScheme("nurse"),
                    mapper.getURI("nurse"), personPrefiks, userId,
                    UserType.NURSE, personId);
            this.saveUser(per, userPrefiks);
            this.savePerson(psn, personPrefiks, UserType.NURSE);
        } else {
            psn = this.validatePerson(person, mapper.getScheme("chart"),
                    mapper.getURI("chart"), personPrefiks, userId,
                    UserType.PATIENT, personId);
            this.saveUser(per, userPrefiks);
            this.savePerson(psn, personPrefiks, UserType.PATIENT);
            this.savePatient(mapper.getURI("chart") + personId,
                    mapper.getURI("user") + userId);
        }
        return new String[]{per, psn};
    }

    private void saveUser(String user, String prefix) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("users"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("user"), mapper.getPath("users"), user,
                    mapper.getPrefix("users"));
            long mods = xupdateService.updateResource(mapper.getDocument("users"), queryContent);

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

    private void savePerson(String person, String prefix, UserType userType) {
        DatabaseResources resources = null;
        try {
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();
            String queryContent;
            String document;
            if (userType == UserType.DOCTOR) {
                resources = connection.setupConnection(mapper.getCollection(),
                        mapper.getDocument("doctors"));
                queryContent = String.format(this.loadFileContents(pathToQuery),
                        prefix, mapper.getPrefix("doctor"), mapper.getPath("doctors"), person,
                        mapper.getPrefix("doctors"));
                document = mapper.getDocument("doctors");
            } else if (userType == UserType.NURSE) {
                resources = connection.setupConnection(mapper.getCollection(),
                        mapper.getDocument("nurses"));
                queryContent = String.format(this.loadFileContents(pathToQuery),
                        prefix, mapper.getPrefix("nurse"),
                        mapper.getPath("nurses"), person,
                        mapper.getPrefix("nurses"));
                document = mapper.getDocument("nurses");
            } else {
                resources = connection.setupConnection(mapper.getCollection(),
                        mapper.getDocument("charts"));
                queryContent = String.format(this.loadFileContents(pathToQuery),
                        prefix, mapper.getPrefix("chart"),
                        mapper.getPath("charts"), person,
                        mapper.getPrefix("charts"));
                document = mapper.getDocument("charts");
            }

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

    private void savePatient(String chartId, String userId) {
        DatabaseResources resources = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setIgnoringElementContentWhitespace(true);
            dbf.setValidating(false);
            Document doc = dbf.newDocumentBuilder().newDocument();

            final String PREFIX = "patient";
            final String SUFIX = "identifier";

            Element patient = doc.createElementNS(mapper.getPrefix("patient"), "patient");
            patient.setPrefix(PREFIX);
            patient.setAttribute("id", mapper.getURI("patient") + sequencer.getId());

            Element user = doc.createElement(PREFIX + ":user");
            user.setAttribute(PREFIX + ":" + SUFIX, userId);

            Element chart = doc.createElement(PREFIX + ":chart");
            chart.setAttribute(PREFIX + ":" + SUFIX, chartId);

            Element notifications = doc.createElement(PREFIX + ":notifications");

            patient.appendChild(user);
            patient.appendChild(chart);
            patient.appendChild(notifications);

            doc.appendChild(patient);

            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("patients"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    PREFIX, mapper.getPrefix("patient"), mapper.getPath("patients"),
                    mapper.convertToString(doc), mapper.getPrefix("patients"));
            long mods = xupdateService.updateResource(mapper.getDocument("patients"), queryContent);

            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }
        } catch (ParserConfigurationException e) {
            throw new TransformerException("Error while processing data!");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private Node getUser(NodeList list) {
        Node element;
        for (int i = 0; i < list.getLength(); i++) {
            try {
                element = list.item(i);
                if (element.getLocalName().equals("user")) {
                    return element;
                }
            } catch (Exception ignore) {}
        }
        return null;
    }

    private Node getPerson(NodeList list) {
        Node element;
        for (int i = 0; i < list.getLength(); i++) {
            try {
                element = list.item(i);
                if (element.getLocalName().equals("doctor") ||
                        element.getLocalName().equals("nurse") ||
                        element.getLocalName().equals("chart")) {
                    return element;
                }
            } catch (Exception ignored) {}

        }
        return null;
    }

    private String validateUser(Node user, Long id, String prefix) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            Document doc = factory.newDocumentBuilder().newDocument();
            Node imported = doc.importNode(user, true);
            doc.appendChild(imported);

            constraintsRepository.checkUserConstraints(doc, prefix);
            ((Element) doc.getFirstChild()).setAttribute("id", mapper.getURI("user") + id);
            ((Element) doc.getFirstChild()).setAttribute("active", "true");
            metadataGenerator.addUserMetadata(doc);

            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(ResourceUtils.getFile(mapper.getScheme("user")))
                    .newValidator().validate(new DOMSource(doc));
            return mapper.convertToString(doc);
        } catch (ParserConfigurationException | IOException e) {
            throw new TransformerException("Data processing not possible!");
        } catch (SAXException e) {
            throw new ValidationException("Invalid user content!");
        }
    }

    private String validatePerson(Node person, String scheme, String uriPrefix, String prefix,
                                  Long userId, UserType userType, Long personId) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            Document doc = factory.newDocumentBuilder().newDocument();
            Node imported = doc.importNode(person, true);
            doc.appendChild(imported);

            String id = uriPrefix + personId;
            ((Element) doc.getFirstChild()).setAttribute("id", id);
            ((Element) doc.getFirstChild().getFirstChild())
                    .setAttribute(prefix + ":identifier", mapper.getURI("user") + userId);

            if (userType == UserType.PATIENT) {
                ((Element) doc.getFirstChild()).setAttribute("active", "true");
                constraintsRepository.checkPatientConstraints(doc);
            }
            metadataGenerator.addPersonMetadata(doc, userType, id);

            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(ResourceUtils.getFile(scheme))
                    .newValidator().validate(new DOMSource(doc));
            return mapper.convertToString(doc);
        } catch (ParserConfigurationException | IOException e) {
            throw new TransformerException("Data processing not possible!");
        } catch (SAXException e) {
            throw new ValidationException("Invalid person content!");
        }
    }

    private String endNotification(String text) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setValidating(false);
        Document doc;
        try {
            final String PREFIX = "patient";

            doc = dbf.newDocumentBuilder().newDocument();
            Element notification = doc.createElement(PREFIX + ":notification");
            notification.setAttribute("date", String.valueOf(LocalDateTime.now()));
            notification.setTextContent(text);
            doc.appendChild(notification);
            return mapper.convertToString(doc);
        } catch (ParserConfigurationException e) {
            throw new TransformerException("Error while processing data!");
        }
    }

    private String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
