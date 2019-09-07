package com.uns.ac.rs.xml.repository.xml;

import org.exist.xmldb.EXistResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;
import com.uns.ac.rs.xml.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.tname.LocalDateTname;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExamStateXMLRepository extends IOStreamer {

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private Mapper mapper;

    public List<String> getProcesses() {
        DatabaseResources resources = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("exam_states"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("getExamState")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = this.loadFileContents(pathToQuery);
            CompiledExpression compiledXquery = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledXquery);
            ResourceIterator i = result.getIterator();
            Resource res = null;

            while (i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    result.add((res.getContent().toString()));
                } finally {
                    if (res != null)
                        ((EXistResource) res).freeResources();

                }
            }
            connection.freeResources(resources);
            return result;
        } catch (XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public void addNewProcess(com.uns.ac.rs.xml.util.actions.Action action) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("exam_states"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("add")).getPath();

            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    "est", mapper.getPrefix("exam_state"),
                    mapper.getPath("exam_states"),
                    this.konverturjUString(action), mapper.getPrefix("exam_states"));
            long mods = xupdateService.updateResource(mapper.getDocument("exam_states"), queryContent);

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

    public void editProcess(String state, String patient) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("exam_states"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("edit")).getPath();
            XUpdateQueryService xupdateService = (XUpdateQueryService) resources.getCollection()
                    .getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            String path = this.getProcessPath(patient);
            String queryContent = String.format(this.loadFileContents(pathToQuery),
                    "est", mapper.getPrefix("exam_state"), path + "/@datum",
                    LocalDateTname.now().toString(), mapper.getPrefix("exam_states"));
            logger.info(queryContent);
            long mods = xupdateService.updateResource(mapper.getDocument("exam_states"), queryContent);
            logger.info(mods + " changes processed.");
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }

            queryContent = String.format(this.loadFileContents(pathToQuery),
                    "est", mapper.getPrefix("exam_state"), path + "/@state",
                    state, mapper.getPrefix("exam_states"));
            mods = xupdateService.updateResource(mapper.getDocument("exam_states"), queryContent);
            if (mods == 0) {
                throw new DatabaseConnectionException("Error while saving data");
            }

            connection.freeResources(resources);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    private String getProcessPath(String id) {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(),
                    mapper.getDocument("exam_states"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("getStatePaths")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = String.format(this.loadFileContents(pathToQuery), id);
            logger.info(queryContent);
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
                throw new ValidationException("Exam state does not exist!");
            }
            return rez;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLDBException |
                IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    /**
     * @param action koju je potrebno procesirati
     * @return string reprezentaciju nove stavke poslovnog procesa
     */
    private String konverturjUString(com.uns.ac.rs.xml.util.actions.Action action) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setIgnoringElementContentWhitespace(true);
            dbf.setValidating(false);
            Document doc = dbf.newDocumentBuilder().newDocument();

            Element exam = doc.createElementNS(mapper.getPrefix("exam_state"), "exam_state");
            exam.setPrefix("est");
            exam.setAttribute("patient", mapper.findPatientByExam(action));
            exam.setAttribute("state", "cekanje");
            exam.setAttribute("datum", LocalDateTname.now().toString());
            doc.appendChild(exam);
            return mapper.convertToString(doc);
        } catch (ParserConfigurationException e) {
            throw new TransformationException("Greska pri obradi podataka!");
        }
    }
}
