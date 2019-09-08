package com.uns.ac.rs.xml.controller;

import com.uns.ac.rs.xml.util.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.states.Process;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private Process process;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> process(@RequestBody Action action) {
        return new ResponseEntity<>(process.processRequest(action), HttpStatus.OK);
    }
}
