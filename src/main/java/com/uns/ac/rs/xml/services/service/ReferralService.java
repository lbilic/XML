package com.uns.ac.rs.xml.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.RDFRepository;
import com.uns.ac.rs.xml.repository.ReferralXMLRepository;
import com.uns.ac.rs.xml.util.database.Mapper;

@Service
public class ReferralService {

    @Autowired
    private ReferralXMLRepository referralXMLRepository;

    @Autowired
    private RDFRepository rdfRepository;

    @Autowired
    private Mapper mapper;

    public String getAll() {
        return referralXMLRepository.getAll();
    }

    public String findById(String id) {
        return referralXMLRepository.findById(id);
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return referralXMLRepository.delete(action);
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        return referralXMLRepository.edit(action);
    }
}
