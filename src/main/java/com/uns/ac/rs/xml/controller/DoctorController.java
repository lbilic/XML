package com.uns.ac.rs.xml.controller;

import com.uns.ac.rs.xml.services.nonProcessService.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController extends ValidatorController {

    @Autowired
    private DoctorService doctorService;

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