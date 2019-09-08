package com.uns.ac.rs.xml.util.database;

import com.uns.ac.rs.xml.util.validator.ValidationException;
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
import java.io.*;
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
        try
        {
            FileInputStream fis = new FileInputStream("xmlDatabase.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            xmlDatabase = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try
        {
            FileInputStream fis = new FileInputStream("xmlQueries.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            xmlQueries = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try
        {
            FileInputStream fis = new FileInputStream("xmlPrefixes.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            xmlPrefixes = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try
        {
            FileInputStream fis = new FileInputStream("xmlPaths.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            xmlPaths = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try
        {
            FileInputStream fis = new FileInputStream("uriPrefix.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            uriPrefix = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try
        {
            FileInputStream fis = new FileInputStream("transformations.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            transformations = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try
        {
            FileInputStream fis = new FileInputStream("graphs.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            graphs = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Document convertToDocument(String xmlContent) {

        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlContent));

            return db.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new com.uns.ac.rs.xml.util.transformators.TransformerException("Error while processing data!");
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
            throw new com.uns.ac.rs.xml.util.transformators.TransformerException("Error while processing data!");
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
            throw new com.uns.ac.rs.xml.util.transformators.TransformerException("Data processing not possible!");
        } catch (TransformerException e) {
            throw new com.uns.ac.rs.xml.util.transformators.TransformerException("Error while processing data!");
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

    public String getPatientFromReport(com.uns.ac.rs.xml.util.actions.Action action) {
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