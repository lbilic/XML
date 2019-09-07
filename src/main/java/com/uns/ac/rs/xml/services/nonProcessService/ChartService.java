package com.uns.ac.rs.xml.services.nonProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.rdf.RDFRepository;
import com.uns.ac.rs.xml.repository.xml.ChartXMLRepository;
import com.uns.ac.rs.xml.util.Mapper;

@Service
public class ChartService {

    @Autowired
    private ChartXMLRepository repository;

    @Autowired
    private RDFRepository rdfRepository;

    @Autowired
    private Mapper mapper;

    public String getAll() {
        return repository.getAll();
    }

    public String findById(String id) {
        return repository.findById(id);
    }

    public String getDocuments(String id) { return repository.getDocuments(id); }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        String result = repository.editChart(action);
        if (result != null) {
            String newResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    result.trim().replaceFirst(" ", "  " + mapper.getPrefix("vocabulary")
                            + mapper.getPrefix("xmlScheme"));
            rdfRepository.edit(newResult, mapper.getGraph("charts"), true);
            return "Successfully changed chart!";
        }
        throw new com.uns.ac.rs.xml.util.TransformerException("Error while processing data.");

    }

    public String generalDoctorSearch(String text) {
        return repository.generalSearch(text, "generalChartSearch");
    }

    public String generalPatientSearch(String text) {
        return repository.generalSearch(text, "generalDocumentSearch");
    }
}
