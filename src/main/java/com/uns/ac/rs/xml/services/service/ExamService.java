package com.uns.ac.rs.xml.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.ExamXMLRepository;

@Service
public class ExamService {

    @Autowired
    private ExamXMLRepository examXMLRepository;

    public String findById(String id) {
        return examXMLRepository.findById(id);
    }
}
