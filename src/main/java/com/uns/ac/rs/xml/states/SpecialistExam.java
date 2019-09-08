package com.uns.ac.rs.xml.states;

import com.uns.ac.rs.xml.util.validator.ValidationException;
import org.apache.xerces.dom.ElementNSImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.uns.ac.rs.xml.repository.RDFRepository;
import com.uns.ac.rs.xml.repository.ReportXMLRepository;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.database.Mapper;

@Service
public class SpecialistExam extends State {

    @Autowired
    private ReportXMLRepository reportXMLRepository;

    @Autowired
    private SpecialistExam specialistExam;

    @Autowired
    private Mapper mapper;

    @Autowired
    private RDFRepository rdfRepository;

    @Autowired
    private Operations operations;

    @Override
    public String processRequest(com.uns.ac.rs.xml.util.actions.Action action) {
        return specialistExam.createReport(action);
    }

    public String createReport(com.uns.ac.rs.xml.util.actions.Action action) {
        Node report = getDocumentFromList(action);
        if (report == null) {
            throw new ValidationException("Report not passed!");
        }
        reportXMLRepository.checkReport(report);
        String result = operations.save(report, "reports", "report");
        String newResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                result.trim().replaceFirst(" ", "  " + mapper.getPrefix("vocabulary")
                        + mapper.getPrefix("xmlScheme"));
        rdfRepository.save(newResult, mapper.getGraph("reports"), false);

        if (!result.equals(""))
            return "Report successfully saved";
        else
            return "Error while saving report";
    }

    private Node getDocumentFromList(com.uns.ac.rs.xml.util.actions.Action action) {
        Document doc = ((ElementNSImpl) action.getContent().getAny()).getOwnerDocument();
        NodeList list = doc.getChildNodes();
        Node element;
        try {
            for (int i = 0; i < list.getLength(); i++) {
                element = list.item(i);
                if (element.getLocalName().equals("report")) {
                    return element;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
