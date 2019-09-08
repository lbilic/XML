package com.uns.ac.rs.xml.repository.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.Mapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ReferralXMLRepository {

    @Autowired
    private DoctorXMLRepository doctorXMLRepository;

    @Autowired
    private ChartXMLRepository userXMLRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Operations operations;

    private String document = "referrals";
    private String documentPrefix = "referral";

    public String getAll() {
        return operations.getAll(document, "getAllReferrals");
    }

    public String findById(String id) {
        return operations.findById(id, document, "findReferralById");
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return operations.delete(action, document, documentPrefix, "findReferralById");
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        checkPrescription(mapper.getDocument(action, documentPrefix));
        return operations.edit(action, document, documentPrefix);
    }

    public void checkPrescription(Node content) {
        String doctorId = "";
        String userId = "";
        String specialistId = "";
        NodeList list = content.getChildNodes();
        Node element;
        label:
        for (int i = 0; i < list.getLength(); i++) {
            try {
                element = list.item(i);
                switch (element.getLocalName()) {
                    case "insured_person":
                        userId = element.getAttributes().item(0).getNodeValue();
                        break;
                    case "doctor":
                        doctorId = element.getAttributes().item(0).getNodeValue();
                        break;
                    case "specialist":
                        specialistId = element.getAttributes().item(0).getNodeValue();
                        break label;
                }
            } catch (Exception ignored) {
            }
        }

        doctorXMLRepository.findById(doctorId);
        doctorXMLRepository.findById(specialistId);
        userXMLRepository.findById(userId);

    }

    private String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
