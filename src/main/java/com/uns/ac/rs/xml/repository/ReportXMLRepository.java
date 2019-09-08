package com.uns.ac.rs.xml.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.database.Mapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ReportXMLRepository {

    @Autowired
    private Mapper mapper;

    @Autowired
    private DoctorXMLRepository doctorXMLRepository;

    @Autowired
    private ChartXMLRepository chartXMLRepository;

    @Autowired
    private Operations operations;

    private String document = "reports";
    private String documentPrefix = "report";

    public String getAll() {
        return operations.getAll(document, "getAllReports");
    }

    public String findById(String id) {
        return operations.findById(id, document, "findReportById");
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return operations.delete(action, document, documentPrefix, "findReportById");
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        checkReport(mapper.getDocument(action, "report"));
        return operations.edit(action, document, documentPrefix);
    }


    public void checkReport(Node content) {
        String doctorId = "";
        String userId = "";
        NodeList list = content.getChildNodes();
        Node element;
        for (int i = 0; i < list.getLength(); i++) {
            try {
                element = list.item(i);
                if (element.getLocalName().equals("insured_person")) {
                    userId = element.getAttributes().item(0).getNodeValue();
                }
                if (element.getLocalName().equals("doctor")) {
                    doctorId = element.getAttributes().item(0).getNodeValue();
                    break;
                }
            } catch (Exception ignored) {
            }
        }
        doctorXMLRepository.findById(doctorId);
        chartXMLRepository.findById(userId);
    }

    String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
