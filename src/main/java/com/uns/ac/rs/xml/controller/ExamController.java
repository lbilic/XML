package com.uns.ac.rs.xml.controller;

import com.uns.ac.rs.xml.services.service.ExamService;
import com.uns.ac.rs.xml.util.database.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private Mapper mapper;

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> findById(@PathVariable String id) {
        return new ResponseEntity<>(examService.findById(mapper.getURI("exam") + id),
                HttpStatus.OK);
    }
}
