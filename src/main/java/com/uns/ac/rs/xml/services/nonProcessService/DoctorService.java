package com.uns.ac.rs.xml.services.nonProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.xml.DoctorXMLRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorXMLRepository doctorXMLRepository;

    public String getAll() {
        return doctorXMLRepository.getAll();
    }

    public String findById(String id) {
        return doctorXMLRepository.findById(id);
    }

    public String countingPatients() {
        return doctorXMLRepository.countingPatients();
    }
}
