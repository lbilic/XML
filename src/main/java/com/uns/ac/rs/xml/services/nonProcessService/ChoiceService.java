package com.uns.ac.rs.xml.services.nonProcessService;

import org.apache.xerces.dom.ElementNSImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.uns.ac.rs.xml.repository.rdf.RDFRepository;
import com.uns.ac.rs.xml.repository.xml.ChoiceXMLRepository;
import com.uns.ac.rs.xml.repository.xml.ChartXMLRepository;
import com.uns.ac.rs.xml.util.Mapper;

@Service
public class ChoiceService {

    @Autowired
    private ChoiceXMLRepository choiceXMLRepository;

    @Autowired
    private ChartXMLRepository chartXMLRepository;

    @Autowired
    private RDFRepository rdfRepository;

    @Autowired
    private Mapper mapper;

    public String getAll() {
        return choiceXMLRepository.getAll();
    }

    public String findById(String id) {
        return choiceXMLRepository.findById(id);
    }

    public String save(com.uns.ac.rs.xml.util.actions.Action action) {
        String result = choiceXMLRepository.save(action);
        String newResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                result.trim().replaceFirst(" ", "  " + mapper.getPrefix("vocabulary")
                        + mapper.getPrefix("xmlScheme"));
        rdfRepository.save(newResult, mapper.getGraph("choices"), false);
        this.editDoctorInChart(action);
        return "Uspesno promenjen doctor.";
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return choiceXMLRepository.delete(action);
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        return choiceXMLRepository.edit(action);
    }

    private void editDoctorInChart(com.uns.ac.rs.xml.util.actions.Action action) {
        Document doc = ((ElementNSImpl) action.getContent().getAny()).getOwnerDocument();
        String doctorId = "";
        String chartId = "";
        NodeList elements = doc.getFirstChild().getChildNodes();
        Element element;
        for (int i = 0; i < elements.getLength(); i++) {
            try {
                element = (Element) elements.item(i);
                switch (element.getTagName().split(":")[1]) {
                    case "doctor":
                        doctorId = element.getAttributes().item(0).getNodeValue();
                        break;
                    case "insured_person":
                        chartId = element.getAttributes().item(0).getNodeValue();
                        break;
                }
            } catch (Exception ignored) {

            }

        }
        if (doctorId.equals("") || chartId.equals("")) {
            throw new com.uns.ac.rs.xml.util.ValidationException("Invalid data passed!");
        }
        chartXMLRepository.editChosenDoctor(chartId, doctorId);
        rdfRepository.editChartField(chartId, "voc:odabraniDoctor", "<" + doctorId + ">");

    }
}
