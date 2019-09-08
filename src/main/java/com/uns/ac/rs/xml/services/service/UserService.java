package com.uns.ac.rs.xml.services.service;

import com.uns.ac.rs.xml.util.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.uns.ac.rs.xml.domain.DTO.Login;
import com.uns.ac.rs.xml.repository.RDFRepository;
import com.uns.ac.rs.xml.repository.UserXMLRepository;
import com.uns.ac.rs.xml.util.database.Mapper;

@Service
public class UserService {

    @Autowired
    private UserXMLRepository userXMLRepository;

    @Autowired
    private RDFRepository rdfRepository;

    @Autowired
    private Mapper mapper;

    public Login login(com.uns.ac.rs.xml.util.actions.Action action) {
        Document doc = mapper.convertToDocument(action);
        NodeList elements = doc.getFirstChild().getLastChild().getFirstChild().getChildNodes();
        Element element;
        String username = null;
        String password = null;
        for (int i = 0; i < elements.getLength(); i++) {
            try {
                element = (Element) elements.item(i);
                switch (element.getTagName()) {
                    case "username":
                        username = element.getTextContent();
                        break;
                    case "password":
                        password = element.getTextContent();
                        break;
                }
            } catch (Exception ignored) {}
        }
        if (username == null || password == null) {
            throw new ValidationException("Incorrect action passed!");
        }
        return userXMLRepository.login(username, password);
    }

    public String register(com.uns.ac.rs.xml.util.actions.Action action) {
        String[] results = userXMLRepository.register(action);
        results = this.preProcessing(results[1], results[0]);
        if (results[1].equals(mapper.getGraph("charts"))) {
            rdfRepository.save(results[0], results[1], true);
        } else {
            rdfRepository.save(results[0], results[1], false);
        }
        return "Successful registration!";
    }

    public String delete(String id) {
        String chartId = userXMLRepository.delete(id);
        rdfRepository.delete("charts", chartId);
        return "User successfully deleted!";
    }

    public String findNotificationsByUser(String id) {
        return userXMLRepository.findNotificationsByUser(id);
    }

    private String[] preProcessing(String glavni, String pomocni) {
        String graph = mapper.getGraph("charts");
        glavni = glavni.trim().replaceFirst(" ", "  " + mapper.getPrefix("vocabulary")
                + mapper.getPrefix("xmlScheme"));
        if (!glavni.contains("chart")) {
            glavni = glavni.replaceFirst("<.*user.*/>", pomocni);
            graph = mapper.getGraph("doctors");
        }
        return new String[]{"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + glavni, graph};

    }
}
