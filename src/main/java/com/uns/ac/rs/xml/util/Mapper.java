package com.uns.ac.rs.xml.util;

import org.apache.xerces.dom.ElementNSImpl;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
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
import java.util.HashMap;
import java.util.Map;

@Component
public class Mapper {

    private Map<String, String> xmlDatabase;
    private Map<String, String> xmlQueries;
    private Map<String, String> xmlSchemes;
    private Map<String, String> xmlPrefixes;
    private Map<String, String> xmlPaths;
    private Map<String, String> uriPrefix;
    private Map<String, String> transformations;
    private Map<String, String> graphs;

    public Mapper() {
        this.xmlDatabase = new HashMap<>();
        this.xmlQueries = new HashMap<>();
        this.xmlSchemes = new HashMap<>();
        this.xmlPrefixes = new HashMap<>();
        this.xmlPaths = new HashMap<>();
        this.uriPrefix = new HashMap<>();
        this.transformations = new HashMap<>();
        this.graphs = new HashMap<>();
        this.initializeMap();
    }

    private void initializeMap() {
        this.xmlDatabase.put("collection", "/db/rs/zis/");
        this.xmlDatabase.put("doctors", "doctors.xml");
        this.xmlDatabase.put("users", "users.xml");
        this.xmlDatabase.put("nurses", "nurses.xml");
        this.xmlDatabase.put("patients", "patients.xml");
        this.xmlDatabase.put("exams", "exams.xml");
        this.xmlDatabase.put("exam_states", "exam_states.xml");
        this.xmlDatabase.put("reports", "reports.xml");
        this.xmlDatabase.put("charts", "charts.xml");
        this.xmlDatabase.put("drugs", "drugs.xml");
        this.xmlDatabase.put("prescriptions", "prescriptions.xml");
        this.xmlDatabase.put("referrals", "referrals.xml");
        this.xmlDatabase.put("choices", "choices.xml");

        this.xmlQueries.put("getAllDoctors", "classpath:templates/xquery/doctors/getAllDoctors.xqy");
        this.xmlQueries.put("findDoctorById", "classpath:templates/xquery/doctors/findDoctorById.xqy");
        this.xmlQueries.put("add", "classpath:templates/xquery/update/add.xml");
        this.xmlQueries.put("counting",
                "classpath:templates/xquery/sequencer/getAllEntityCount.xqy");
        this.xmlQueries.put("userRestraints",
                "classpath:templates/xquery/users/uniqueUserFieldsCheck.xq");
        this.xmlQueries.put("edit", "classpath:templates/xquery/update/edit.xml");
        this.xmlQueries.put("getPaths", "classpath:templates/xquery/update/getPaths.xq");
        this.xmlQueries.put("delete", "classpath:templates/xquery/update/delete.xml");
        this.xmlQueries.put("findExamById", "classpath:templates/xquery/exams/findExamById.xqy");
        this.xmlQueries.put("examConstraints",
                "classpath:templates/xquery/exams/uniqueExamFieldsCheck.xq");
        this.xmlQueries.put("getStatePaths",
                "classpath:templates/xquery/update/getPatientStatePaths.xq");
        this.xmlQueries.put("chartConstraints",
                "classpath:templates/xquery/charts/uniqueChartFieldsCheck.xq");
        this.xmlQueries.put("findChartByUser",
                "classpath:templates/xquery/charts/findChartByUser.xq");
        this.xmlQueries.put("physicalDelete", "classpath:templates/xquery/update/physicalDelete.xml");
        this.xmlQueries.put("findChartById",
                "classpath:templates/xquery/charts/findChartById.xq");
        this.xmlQueries.put("findUserById",
                "classpath:templates/xquery/users/findUserById.xqy");
        this.xmlQueries.put("findUserByChart",
                "classpath:templates/xquery/users/findUserByChart.xq");
        this.xmlQueries.put("getAllDrugs", "classpath:templates/xquery/drugs/getAllDrugs.xqy");
        this.xmlQueries.put("findDrugById", "classpath:templates/xquery/drugs/findDrugById.xqy");
        this.xmlQueries.put("findReportById", "classpath:templates/xquery/reports/findReportById.xqy");
        this.xmlQueries.put("getAllReports", "classpath:templates/xquery/reports/getAllReports.xqy");
        this.xmlQueries.put("getAllPrescriptions", "classpath:templates/xquery/prescriptions/getAllPrescriptions.xqy");
        this.xmlQueries.put("findPrescriptionById", "classpath:templates/xquery/prescriptions/findPrescriptionById.xqy");
        this.xmlQueries.put("getAllReferrals", "classpath:templates/xquery/referrals/getAllReferrals.xqy");
        this.xmlQueries.put("findReferralById", "classpath:templates/xquery/referrals/findReferralById.xqy");
        this.xmlQueries.put("findChoiceById", "classpath:templates/xquery/choices/findChoiceById.xqy");
        this.xmlQueries.put("getAllChoices", "classpath:templates/xquery/choices/getAllChoices.xqy");
        this.xmlQueries.put("getAllCharts",
                "classpath:templates/xquery/charts/getAllCharts.xq");
        this.xmlQueries.put("getDrugForDiagnosis", "classpath:templates/xquery/drugs/getDrugForDiagnosis.xqy");
        this.xmlQueries.put("generalChartSearch", "classpath:templates/xquery/charts/generalChartSearch.xqy");
        this.xmlQueries.put("getExamState",
                "classpath:templates/xquery/update/getExamState.xq");
        this.xmlQueries.put("countingPatients",
                "classpath:templates/xquery/doctors/countingPatients.xq");
        this.xmlQueries.put("choiceConstraints", "classpath:templates/xquery/choices/choiceConstraints.xq");
        this.xmlQueries.put("getAllDocuments",
                "classpath:templates/xquery/opsti_queryi/getAllDocuments.xq");
        this.xmlQueries.put("generalDocumentSearch",
                "classpath:templates/xquery/opsti_queryi/generalDocumentSearch.xq");
        this.xmlQueries.put("login", "classpath:templates/xquery/users/findUserByUsername.xq");
        this.xmlQueries.put("findUserByChart",
                "classpath:templates/xquery/users/findUserByChart.xq");
        this.xmlQueries.put("findNotificationsByUser",
                "classpath:templates/xquery/charts/findNotificationsByUser.xq");

        this.xmlSchemes.put("action", "classpath:static/schemes/action.xsd");
        this.xmlSchemes.put("user", "classpath:static/schemes/user.xsd");
        this.xmlSchemes.put("doctor", "classpath:static/schemes/doctor.xsd");
        this.xmlSchemes.put("nurse", "classpath:static/schemes/nurse.xsd");
        this.xmlSchemes.put("chart", "classpath:static/schemes/chart.xsd");
        this.xmlSchemes.put("exam", "classpath:static/schemes/exam.xsd");
        this.xmlSchemes.put("report", "classpath:static/schemes/report.xsd");
        this.xmlSchemes.put("drug", "classpath:static/schemes/drug.xsd");
        this.xmlSchemes.put("prescription", "classpath:static/schemes/prescription.xsd");
        this.xmlSchemes.put("referral", "classpath:static/schemes/referral.xsd");
        this.xmlSchemes.put("choice", "classpath:static/schemes/choice.xsd");


        this.xmlPrefixes.put("user", "http://www.zis.rs/schemes/user");
        this.xmlPrefixes.put("users", "xmlns:ko=\"http://www.zis.rs/schemes/users\"");
        this.xmlPrefixes.put("doctor", "http://www.zis.rs/schemes/doctor");
        this.xmlPrefixes.put("doctors", "xmlns:doctors=\"http://www.zis.rs/schemes/doctors\"");
        this.xmlPrefixes.put("nurse", "http://www.zis.rs/schemes/nurse");
        this.xmlPrefixes.put("nurses",
                "xmlns:nurses=\"http://www.zis.rs/schemes/nurses\"");
        this.xmlPrefixes.put("exam", "http://www.zis.rs/schemes/exam");
        this.xmlPrefixes.put("exams", "xmlns:pr =\"http://www.zis.rs/schemes/exams\"");
        this.xmlPrefixes.put("exam_state", "http://www.zis.rs/schemes/exam_state");
        this.xmlPrefixes.put("exam_states", "xmlns:sp=\"http://www.zis.rs/schemes/exam_states\"");
        this.xmlPrefixes.put("report", "http://www.zis.rs/schemes/report");
        this.xmlPrefixes.put("reports", "xmlns:reports =\"http://www.zis.rs/schemes/reports\"");
        this.xmlPrefixes.put("chart", "http://www.zis.rs/schemes/chart");
        this.xmlPrefixes.put("charts",
                "xmlns:charts =\"http://www.zis.rs/schemes/charts\"");
        this.xmlPrefixes.put("patient", "http://www.zis.rs/schemes/patient");
        this.xmlPrefixes.put("patients", "xmlns:pa =\"http://www.zis.rs/schemes/patients\"");
        this.xmlPrefixes.put("drug", "http://www.zis.rs/schemes/drug");
        this.xmlPrefixes.put("drugs", "xmlns:drugs =\"http://www.zis.rs/schemes/drugs\"");
        this.xmlPrefixes.put("prescription", "http://www.zis.rs/schemes/prescription");
        this.xmlPrefixes.put("prescriptions", "xmlns:prescriptions =\"http://www.zis.rs/schemes/prescriptions\"");
        this.xmlPrefixes.put("referral", "http://www.zis.rs/schemes/referral");
        this.xmlPrefixes.put("referrals", "xmlns:referrals =\"http://www.zis.rs/schemes/referrals\"");
        this.xmlPrefixes.put("choice", "http://www.zis.rs/schemes/choice");
        this.xmlPrefixes.put("choices", "xmlns:choices =\"http://www.zis.rs/schemes/choices\"");
        this.xmlPrefixes.put("vocabulary", "xmlns:voc=\"http://www.zis.rs/rdf/voc#\"");
        this.xmlPrefixes.put("xmlScheme", "  xmlns:xs=\"http://www.w3.org/2001/XMLSchema#\"  ");

        this.xmlPaths.put("users", "/ko:users");
        this.xmlPaths.put("doctors", "/doctors:doctors");
        this.xmlPaths.put("nurses", "/nurses:nurses");
        this.xmlPaths.put("exams", "/pr:exams");
        this.xmlPaths.put("exam_states", "/sp:exam_states");
        this.xmlPaths.put("reports", "/reports:reports");
        this.xmlPaths.put("charts", "/charts:charts");
        this.xmlPaths.put("patients", "/pa:patients");
        this.xmlPaths.put("drugs", "/drugs:drugs");
        this.xmlPaths.put("prescriptions", "/prescriptions:prescriptions");
        this.xmlPaths.put("referrals", "/referrals:referrals");
        this.xmlPaths.put("choices", "/choices:choices");
        this.xmlPaths.put("deleteLinkedPrescriptions",
                "/prescriptions:prescriptions/prescription:prescription[prescription:insured_person/@prescription:identifier = '%1$s']");
        this.xmlPaths.put("deleteLinkedReferrals",
                "/referrals:referrals/referral:referral[referral:insured_person/@referral:identifier = '%1$s']");
        this.xmlPaths.put("deleteLinkedReports",
                "/reports:reports/report:report[report:insured_person/@report:identifier = '%1$s']");
        this.xmlPaths.put("deleteLinkedChoices",
                "/choices:choices/choice:choice[choice:insured_person/@choice:identifier = '%1$s']");

        this.uriPrefix.put("user", "http://www.zis.rs/users/id");
        this.uriPrefix.put("doctor", "http://www.zis.rs/doctors/id");
        this.uriPrefix.put("nurse", "http://www.zis.rs/nurse/id");
        this.uriPrefix.put("patient", "http://www.zis.rs/patients/id");
        this.uriPrefix.put("exam", "http://www.zis.rs/exams/id");
        this.uriPrefix.put("report", "http://www.zis.rs/reports/id");
        this.uriPrefix.put("chart", "http://www.zis.rs/charts/id");
        this.uriPrefix.put("drug", "http://www.zis.rs/drugs/id");
        this.uriPrefix.put("prescription", "http://www.zis.rs/prescriptions/id");
        this.uriPrefix.put("referral", "http://www.zis.rs/referrals/id");
        this.uriPrefix.put("choice", "http://www.zis.rs/choices/id");
        this.uriPrefix.put("vocabulary", "http://www.zis.rs/rdf/voc#");

        this.transformations.put("metadata", "classpath:xsl/grddl.xsl");

        this.graphs.put("doctors", "doctors");
        this.graphs.put("charts", "charts");
        this.graphs.put("referrals", "referrals");
        this.graphs.put("reports", "reports");
        this.graphs.put("prescriptions", "prescriptions");
        this.graphs.put("drugs", "drugs");
        this.graphs.put("choices", "choices");
    }

    public Document convertToDocument(String xmlContent) {

        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlContent));

            return db.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new com.uns.ac.rs.xml.util.TransformerException("Error while processing data!");
        }
    }

    public String createXMLFromNodes(NodeList nodes) throws Exception {
        StringWriter buf = new StringWriter();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node elem = nodes.item(i);//Your Node

            Transformer xform = TransformerFactory.newInstance().newTransformer();
            xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            xform.setOutputProperty(OutputKeys.INDENT, "yes");
            xform.transform(new DOMSource(elem), new StreamResult(buf));
        }
        return buf.toString();
    }

    public Document convertToDocument(com.uns.ac.rs.xml.util.actions.Action action) {
        try {
            Marshaller marshaller = JAXBContext.newInstance(com.uns.ac.rs.xml.util.actions.ObjectFactory.class)
                    .createMarshaller();
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            marshaller.marshal(action, doc);
            return doc;
        } catch (JAXBException | ParserConfigurationException e) {
            return null;
        }
    }

    public String convertToString(Document document) {
        StringWriter w = new StringWriter();
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
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
            throw new com.uns.ac.rs.xml.util.TransformerException("Error while processing data!");
        }
    }

    public String convertToString(Node element) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            Document doc = factory.newDocumentBuilder().newDocument();
            Node imported = doc.importNode(element, true);
            doc.appendChild(imported);

            StringWriter w = new StringWriter();

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(w));

            String content = w.toString();
            if (content.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")) {
                content = content.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
            }
            return content;

        } catch (ParserConfigurationException e) {
            throw new com.uns.ac.rs.xml.util.TransformerException("Data processing not possible!");
        } catch (TransformerException e) {
            throw new com.uns.ac.rs.xml.util.TransformerException("Error while processing data!");
        }
    }

    public String getPatientFromExam(com.uns.ac.rs.xml.util.actions.Action action) {
        return this.convertToDocument(action).getFirstChild().getLastChild().getFirstChild().
                getChildNodes().item(2).getAttributes().item(0).getNodeValue();
    }

    public String getDoctorTypeFromExam(com.uns.ac.rs.xml.util.actions.Action action) {
        return this.convertToDocument(action).getFirstChild().getLastChild().getFirstChild()
                .getAttributes().getNamedItem("type").getNodeValue();
    }

    public String getPatientfromReport(com.uns.ac.rs.xml.util.actions.Action action) {
        return this.convertToDocument(action).getFirstChild().getLastChild().getFirstChild().getChildNodes().item(4)
                .getAttributes().item(0).getNodeValue();
    }

    public String getPatientFromDocumentation(com.uns.ac.rs.xml.util.actions.Action action) {
        NodeList list = convertToDocument(action).getFirstChild().getLastChild().getFirstChild().getChildNodes();
        Node element;
        for (int i = 0; i < list.getLength(); i++) {
            try {
                element = list.item(i);
                if (element.getLocalName().equals("report")) {
                    return element.getChildNodes().item(4)
                            .getAttributes().item(0).getNodeValue();
                }
            } catch (Exception ignored) {}
        }
        throw new ValidationException("Invalid action content!");
    }

    public String getPatientFromChoice(com.uns.ac.rs.xml.util.actions.Action action) {
        NodeList list = convertToDocument(action).getFirstChild().getLastChild().getFirstChild().getChildNodes();
        Node element;
        for (int i = 0; i < list.getLength(); i++) {
            try {
                element = list.item(i);
                if (element.getLocalName().equals("insured_person")) {
                    return element.getAttributes().item(0).getNodeValue();
                }
            } catch (Exception ignored) {}
        }
        throw new ValidationException("Invalid action content!");
    }

    public Node getDocument(com.uns.ac.rs.xml.util.actions.Action action, String documentName) {
        Document doc = ((ElementNSImpl) action.getContent().getAny()).getOwnerDocument();
        NodeList list = doc.getChildNodes();
        Node element;
        for (int i = 0; i < list.getLength(); i++) {
            try {
                element = list.item(i);
                if (element.getLocalName().equals(documentName)) {
                    return element;
                }
            } catch (Exception ignored) {}

        }
        return null;
    }

    public String getCollection() {
        return this.xmlDatabase.get("collection");
    }

    public String getDocument(String name) {
        return this.xmlDatabase.get(name);
    }

    public String getQuery(String name) {
        return this.xmlQueries.get(name);
    }

    public String getScheme(String name) {
        return this.xmlSchemes.get(name);
    }

    public String getPrefix(String name) {
        return this.xmlPrefixes.get(name);
    }

    public String getPath(String name) {
        return this.xmlPaths.get(name);
    }

    public String getURI(String name) {
        return this.uriPrefix.get(name);
    }

    public String getTransformation(String name) {
        return this.transformations.get(name);
    }

    public String getGraph(String name) {
        return this.graphs.get(name);
    }

}