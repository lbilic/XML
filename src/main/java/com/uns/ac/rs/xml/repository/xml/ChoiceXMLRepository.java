package com.uns.ac.rs.xml.repository.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.Mapper;

@Repository
public class ChoiceXMLRepository extends com.uns.ac.rs.xml.util.IOStreamer {

    @Autowired
    private DoctorXMLRepository doctorXMLRepository;

    @Autowired
    private ChartXMLRepository chartXMLRepository;

    @Autowired
    private ConstraintsRepository constraintsRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Operations operations;

    private String document = "choices";
    private String documentPrefix = "choice";

    public String getAll() {
        return operations.getAll(document, "getAllChoices");
    }

    public String findById(String id) {
        return operations.findById(id, document, "findChoiceById");
    }

    public String save(com.uns.ac.rs.xml.util.actions.Action action) {
        checkChoice(mapper.getDocument(action, documentPrefix));
        return operations.save(mapper.getDocument(action, "choice"), document, documentPrefix);
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return operations.delete(action, document, documentPrefix, "findChoiceById");
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        checkChoice(mapper.getDocument(action, documentPrefix));
        return operations.edit(action, document, documentPrefix);
    }

    private void checkChoice(Node content) {
        String doctorId = "";
        String chartId = "";
        String previousDoctor = "";
        NodeList list = content.getChildNodes();
        Node element;
        for (int i = 0; i < list.getLength(); i++) {
            try {
                element = list.item(i);
                switch (element.getLocalName()) {
                    case "insured_person":
                        chartId = element.getAttributes().item(0).getNodeValue();
                        break;
                    case "doctor":
                        doctorId = element.getAttributes().item(0).getNodeValue();
                        break;
                    case "previous_doctor":
                        previousDoctor = element.getAttributes().item(0).getNodeValue();
                        break;
                }
            } catch (Exception ignored) {
            }
        }
        doctorXMLRepository.findById(doctorId);
        doctorXMLRepository.findById(previousDoctor);
        chartXMLRepository.findById(chartId);
        if (previousDoctor.equals(doctorId)) {
            throw new com.uns.ac.rs.xml.util.ValidationException("Same doctors passed!");
        }
        constraintsRepository.checkChoiceRestraints(chartId, previousDoctor, doctorId);

    }

}
