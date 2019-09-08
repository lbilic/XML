package com.uns.ac.rs.xml.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.DrugXMLRepository;


@Service
public class DrugService {

    @Autowired
    private DrugXMLRepository drugXMLRepository;

    public String getAll() {
        return drugXMLRepository.getAll();
    }

    public String findById(String id) {
        return drugXMLRepository.findById(id);
    }

    public String save(com.uns.ac.rs.xml.util.actions.Action action) {
        return drugXMLRepository.save(action);
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return drugXMLRepository.delete(action);
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        return drugXMLRepository.edit(action);
    }

    public String getDrugForDiagnosis(String diagnosis, String patientId) {
        return drugXMLRepository.getDrugForDiagnosis(diagnosis, patientId);
    }
}