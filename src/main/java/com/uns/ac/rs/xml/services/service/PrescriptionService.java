package com.uns.ac.rs.xml.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.ReferralXMLRepository;

@Service
public class PrescriptionService {

    @Autowired
    private ReferralXMLRepository prescriptionXMLRepository;

    public String getAll() {
        return prescriptionXMLRepository.getAll();
    }

    public String findById(String id) {
        return prescriptionXMLRepository.findById(id);
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return prescriptionXMLRepository.delete(action);
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        return prescriptionXMLRepository.edit(action);
    }
}
