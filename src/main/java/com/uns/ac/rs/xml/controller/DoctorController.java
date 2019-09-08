package com.uns.ac.rs.xml.controller;

import com.uns.ac.rs.xml.services.service.DoctorService;
import com.uns.ac.rs.xml.util.database.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="localhost:4200")
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private Mapper mapper;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>(doctorService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> findById(@PathVariable String id) {
        return new ResponseEntity<>(doctorService.findById(mapper.getURI("doctor") + id), HttpStatus.OK);
    }

    @GetMapping(path = "/patient_count", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getPatientCount() {
        return new ResponseEntity<>(doctorService.countingPatients(), HttpStatus.OK);
    }
}
