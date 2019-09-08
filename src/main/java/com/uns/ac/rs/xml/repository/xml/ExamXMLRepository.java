package com.uns.ac.rs.xml.repository.xml;

import org.exist.xmldb.EXistResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;
import com.uns.ac.rs.xml.util.*;

import javax.xml.crypto.dsig.Transform;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ExamXMLRepository {

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Validator validator;

    @Autowired
    private Sequencer sequencer;

    @Autowired
    private DoctorXMLRepository doctorXMLRepository;

    @Autowired
    private ChartXMLRepository chartXMLRepository;

    public String findById(String id) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("exams"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("findExamById")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection()
                    .getService("XQueryService", "1.0");
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
            String exams = sb.toString();
            connection.freeResources(resources);
            if (exams.isEmpty()) {
                throw new ValidationException("No exam in database!");
            } else {
                return exams;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public String save(com.uns.ac.rs.xml.util.actions.Action action) {
        String exam = validator.processAction(action, mapper.getScheme("exam"));
        checkExam(mapper.getDocument(action, "exam"));
        checkDoctor(mapper.convertToDocument(action));

        String prefix = mapper.convertToDocument(exam).getFirstChild().getNodeName().split(":")[0];
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("exams"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            Long id = sequencer.getId();

            String addedId = this.emplaceId(mapper.convertToDocument(exam).getFirstChild(), id, prefix);

            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("exam"), mapper.getPath("exams"), addedId,
                    mapper.getPrefix("exams"));
            long mods = xupdateService.updateResource(mapper.getDocument("exams"), queryContent);

            connection.freeResources(resources);
            return "Successfully scheduled an exam!";
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException | NullPointerException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private void checkExam(Node exam) {
        String doctorId = "";
        String userId = "";
        NodeList list = exam.getChildNodes();
        Node element;
        for (int i = 0; i < list.getLength(); i++) {
            try {
                element = list.item(i);
                if (element.getLocalName().equals("patient")) {
                    userId = element.getAttributes().item(0).getNodeValue();
                } else if (element.getLocalName().equals("doctor")) {
                    doctorId = element.getAttributes().item(0).getNodeValue();
                }
            } catch (Exception ignored) {
            }
        }
        doctorXMLRepository.findById(doctorId);
        chartXMLRepository.findById(userId);
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {

        String id = action.getContext();
        String exam = mapper.convertToString(mapper.convertToDocument(action).getFirstChild()
                .getLastChild().getFirstChild());
        String prefix = mapper.convertToDocument(exam).getFirstChild().getNodeName().split(":")[0];

        String pathToExam = findExam(id);
        DatabaseResources resources = null;

        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("exams"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("delete")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("exam"), pathToExam,
                    mapper.getPrefix("exams"));
            long mods = xupdateService.updateResource(mapper.getDocument("exams"), queryContent);

            connection.freeResources(resources);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }
            return "Exam successfully deleted!";
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {

        String exam = validator.processAction(action, mapper.getScheme("exam"));
        checkExam(mapper.getDocument(action, "exam"));
        Node node = mapper.convertToDocument(exam).getFirstChild();
        Element el = (Element) node;
        String id = el.getAttribute("id");

        checkDoctor(mapper.convertToDocument(action));

        String prefix = mapper.convertToDocument(exam).getFirstChild().getNodeName().split(":")[0];
        NodeList nodesExama = mapper.convertToDocument(exam).getFirstChild().getChildNodes();

        String examContent = null;
        try {
            examContent = mapper.createXMLFromNodes(nodesExama);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("exams"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("edit")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    prefix, mapper.getPrefix("exam"), findExam(id), examContent,
                    mapper.getPrefix("exams"));
            long mods = xupdateService.updateResource(mapper.getDocument("exams"), queryContent);

            connection.freeResources(resources);
            return "Successfully changed exam!";
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private void checkDoctor(Document document) {
        String doctorId = document.getFirstChild().getLastChild().getFirstChild().getFirstChild().getAttributes().item(0).getNodeValue();
        doctorXMLRepository.findById(doctorId);
    }

    private String findExam(String id) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("exams"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("getPaths")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection()
                    .getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    mapper.getCollection() + mapper.getDocument("exams"), id);
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
                throw new ValidationException("Exam does not exist!");
            }
            return rez;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }


    /**
     * @param exam kojeg treba editti, id koji treba ubaciti i prefix namespace
     * @return izmenjena reprezentacija exama
     */
    private String emplaceId(Node exam, Long id, String prefix) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            Document doc = factory.newDocumentBuilder().newDocument();
            Node imported = doc.importNode(exam, true);
            doc.appendChild(imported);

            this.proveriOgranicenjaExama(doc, prefix);
            ((Element) doc.getFirstChild()).setAttribute("id", mapper.getURI("exam") + id);

            return this.convertToString(doc);
        } catch (ParserConfigurationException e) {
            throw new com.uns.ac.rs.xml.util.TransformerException("Data processing not possible!");
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }


    public String printXmlDocument(Document document) {
        DOMImplementationLS domImplementationLS =
                (DOMImplementationLS) document.getImplementation();
        LSSerializer lsSerializer =
                domImplementationLS.createLSSerializer();
        String string = lsSerializer.writeToString(document);
        return string;
    }

    /*
        Exam ne sme biti zakazan kod doctora u isto vreme kao i neki drugi njegov exam
     */
    private void proveriOgranicenjaExama(Document document, String prefix) {
        String doctor = document.getElementsByTagName(prefix + ":doctor")
                .item(0).getAttributes().item(0).getNodeValue();

        String date = document.getElementsByTagName(prefix + ":date").item(0).getTextContent();

        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("exams"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("examConstraints")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), doctor, date);
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
            String greska = sb.toString();
            connection.freeResources(resources);
            if (!greska.isEmpty()) {
                throw new ValidationException(greska);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException | ParserConfigurationException | SAXException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private String convertToString(Document document) {
        StringWriter w = new StringWriter();

        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(document), new StreamResult(w));

            String content = w.toString();
            if (content.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")) {
                content = content.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
            }
            return content;
        } catch (TransformerException e) {
            throw new com.uns.ac.rs.xml.util.TransformerException("Data processing not possible!");
        }
    }

    String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
