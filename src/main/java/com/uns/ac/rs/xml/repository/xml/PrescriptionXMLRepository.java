package com.uns.ac.rs.xml.repository.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.Mapper;

@Repository
public class PrescriptionXMLRepository extends com.uns.ac.rs.xml.util.IOStreamer {

    @Autowired
    private DoctorXMLRepository doctorXMLRepository;

    @Autowired
    private DrugXMLRepository drugXMLRepository;

    @Autowired
    private ChartXMLRepository userXMLRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Operations operations;

    private String document = "prescriptions";
    private String documentPrefix = "prescription";

    public String getAll() {
        return operations.getAll(document, "getAllPrescriptions");
    }

    public String findById(String id) {
        return operations.findById(id, document, "findPrescriptionById");
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return operations.delete(action, document, documentPrefix, "findPrescriptionById");
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        proveriRecept(mapper.getDocument(action, "prescription"));
        return operations.edit(action, document, documentPrefix);
    }


    public void proveriRecept(Node content) {
        String doctorId = "";
        String userId = "";
        String drugId = "";
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
                    case "prescribed_drug":
                        drugId = element.getAttributes().item(0).getNodeValue();
                        break;
                    case "doctor":
                        doctorId = element.getAttributes().item(0).getNodeValue();
                        break label;
                }
            } catch (Exception ignored) {
            }
        }
        doctorXMLRepository.findById(doctorId);
        drugXMLRepository.findById(drugId);
        userXMLRepository.findById(userId);

    }
}