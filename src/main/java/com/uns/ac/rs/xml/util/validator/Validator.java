package com.uns.ac.rs.xml.util.validator;

import org.apache.xerces.dom.ElementNSImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import java.io.StringWriter;

@Component
public class Validator {

    public String processAction(com.uns.ac.rs.xml.util.actions.Action action, String scheme) {
        Document doc = ((ElementNSImpl) action.getContent().getAny()).getOwnerDocument();
        if (((Element) doc.getFirstChild()).hasAttribute("xmlns:akc")) { 
            ((Element) doc.getFirstChild()).removeAttribute("xmlns:akc");
        }
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(ResourceUtils.getFile(scheme))
                    .newValidator().validate(new DOMSource(doc));

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
        } catch (TransformerException | IOException e) {
            throw new com.uns.ac.rs.xml.util.transformators.TransformerException("Data processing not possible!");
        } catch (SAXException e) {
            throw new ValidationException("Invalid data passed!");
        }
    }

    public String processAction(Node content, String scheme) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            Document doc = factory.newDocumentBuilder().newDocument();
            Node imported = doc.importNode(content, true);
            doc.appendChild(imported);

            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(ResourceUtils.getFile(scheme))
                    .newValidator().validate(new DOMSource(content));

            StringWriter w = new StringWriter();

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(content), new StreamResult(w));

            String result = w.toString();
            if (result.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")) {
                result = result.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
            }
            return result;
        } catch (TransformerException | IOException | ParserConfigurationException e) {
            throw new com.uns.ac.rs.xml.util.transformators.TransformerException("Data processing not possible!");
        } catch (SAXException e) {
            throw new ValidationException("Invalid data passed!");
        }
    }
}
