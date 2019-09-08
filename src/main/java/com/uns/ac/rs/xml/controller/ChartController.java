package com.uns.ac.rs.xml.controller;

import com.uns.ac.rs.xml.domain.enums.ActionType;
import com.uns.ac.rs.xml.domain.enums.UserType;
import com.uns.ac.rs.xml.util.database.Mapper;
import com.uns.ac.rs.xml.util.validator.ValidationException;
import com.uns.ac.rs.xml.util.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.domain.DTO.SearchResult;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/charts")
public class ChartController {

    @Autowired
    private com.uns.ac.rs.xml.services.service.ChartService chartService;

    @Autowired
    private Mapper mapper;
    
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<String>(chartService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> findById(@PathVariable String id) {
        return new ResponseEntity<String>(chartService.
                findById(mapper.getURI("chart") + id), HttpStatus.OK);
    }

    @GetMapping(path = "/documents/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getDocument(@PathVariable String id) {
        return new ResponseEntity<String>(chartService.
                getDocuments(mapper.getURI("chart") + id), HttpStatus.OK);
    }

    @GetMapping(path = "search/{text}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResult> generalSearch(@PathVariable String text, HttpSession session) {
        try {
            if (session.getAttribute("type").equals(UserType.PATIENT.toString())) {
                return new ResponseEntity<>(new SearchResult(chartService.
                        generalPatientSearch(text)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new SearchResult(chartService.
                        generalDoctorSearch(text)), HttpStatus.OK);
            }} catch (NullPointerException e) {
            throw new ValidationException("User not logged in.");
        }

    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> save(@RequestBody Action action) {
        if (action.getFunction().equals(ActionType.EDIT.toString())) {
            return new ResponseEntity<>(chartService.edit(action), HttpStatus.OK);
        }
        throw new ValidationException("Incorrect action passed!");
    }
}
