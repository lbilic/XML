package com.uns.ac.rs.xml.controller;

import com.uns.ac.rs.xml.util.Mapper;
import com.uns.ac.rs.xml.util.ValidationException;
import com.uns.ac.rs.xml.util.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.SchemaFactory;
import java.io.IOException;
import com.uns.ac.rs.xml.util.TransformerException;

public class ValidatorController {

    @Autowired
    protected Mapper mapper;

    void validateAction(Action action) {
        try {
            Marshaller marshaller = JAXBContext.newInstance(com.uns.ac.rs.xml.util.actions.ObjectFactory.class)
                    .createMarshaller();
            Document dok = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            marshaller.marshal(action, dok);

            Node content = dok.getElementsByTagName("content").item(0);
            deleteContent(content);

            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(ResourceUtils.getFile(mapper.getScheme("action")))
                    .newValidator().validate(new DOMSource(dok));
        } catch (JAXBException | ParserConfigurationException | IOException e) {
            throw new TransformerException("Processing disabled!");
        } catch (SAXException e) {
            throw new ValidationException("Invalid action passed!");
        }
    }

    private void deleteContent(Node content) {
        while (content.hasChildNodes())
            content.removeChild(content.getFirstChild());
    }
}
