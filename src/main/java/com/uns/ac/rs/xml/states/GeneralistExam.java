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
import com.uns.ac.rs.xml.repository.ReferralXMLRepository;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.database.Mapper;

@Service
public class GeneralistExam extends State {

    @Autowired
    private GeneralistExam generalistExam;

    @Autowired
    private ReportXMLRepository reportXMLRepository;

    @Autowired
    private ReferralXMLRepository referralXMLRepository;

    @Autowired
    private ReferralXMLRepository prescriptionXMLRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private RDFRepository rdfRepository;

    @Autowired
    private Operations operations;

    @Override
    public String processRequest(com.uns.ac.rs.xml.util.actions.Action action) {
        return generalistExam.createDocumentation(action);
    }

    public String createDocumentation(com.uns.ac.rs.xml.util.actions.Action action) {
        Node report = getDocumentFromList(action, "report");
        if (report == null) {
            throw new ValidationException("Report not passed!");
        }
        Node referral = getDocumentFromList(action, "referral");
        Node prescription = getDocumentFromList(action, "prescription");
        String result;
        String graph;
        if (referral != null && prescription != null) {
            throw new ValidationException("Referral and report passed!");
        } else if (referral != null) {
            referralXMLRepository.checkPrescription(referral);
            result = operations.save(referral, "referrals", "referral");
            graph = "referrals";
            if (result.equals(""))
                return "Greska prilikom snimanja referrala";
            String newResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    result.trim().replaceFirst(" ", "  " + mapper.getPrefix("vocabulary")
                            + mapper.getPrefix("xmlScheme"));
            rdfRepository.save(newResult, mapper.getGraph(graph), false);
        } else if (prescription != null) {
            prescriptionXMLRepository.checkPrescription(prescription);
            result = operations.save(prescription, "prescriptions", "prescription");
            graph = "prescriptions";
            if (result.equals(""))
                return "Greska prilikom snimanja prescriptiona";
            String newResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    result.trim().replaceFirst(" ", "  " + mapper.getPrefix("vocabulary")
                            + mapper.getPrefix("xmlScheme"));
            rdfRepository.save(newResult, mapper.getGraph(graph), false);
        }

        reportXMLRepository.checkReport(report);
        result = operations.save(report, "reports", "report");
        graph = "reports";
        String newResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                result.trim().replaceFirst(" ", "  " + mapper.getPrefix("vocabulary")
                        + mapper.getPrefix("xmlScheme"));
        rdfRepository.save(newResult, mapper.getGraph(graph), false);

        if (!result.equals(""))
            return "Report successfully saved";
        else
            return "Error while saving report";
    }

    private Node getDocumentFromList(com.uns.ac.rs.xml.util.actions.Action action, String documentName) {
        Document doc = ((ElementNSImpl) action.getContent().getAny()).getOwnerDocument();
        NodeList list = doc.getFirstChild().getChildNodes();
        Node element;
        try {
            for (int i = 0; i < list.getLength(); i++) {
                element = list.item(i);
                if (element.getLocalName().equals(documentName)) {
                    return element;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
