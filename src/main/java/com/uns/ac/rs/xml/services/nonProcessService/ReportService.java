package com.uns.ac.rs.xml.services.nonProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.xml.ReportXMLRepository;

@Service
public class ReportService {

    @Autowired
    private ReportXMLRepository reportXMLRepository;

    public String getAll() {
        return reportXMLRepository.getAll();
    }

    public String findById(String id) {
        return reportXMLRepository.findById(id);
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return reportXMLRepository.delete(action);
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        return reportXMLRepository.edit(action);
    }
}
