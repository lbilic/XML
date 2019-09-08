package com.uns.ac.rs.xml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.domain.enums.ActionType;
import com.uns.ac.rs.xml.services.nonProcessService.ReportService;
import com.uns.ac.rs.xml.util.ValidationException;
import com.uns.ac.rs.xml.util.actions.Action;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reports")
public class ReportController extends ValidatorController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> findById(@PathVariable String id) {
        return new ResponseEntity<>(reportService.findById(mapper.getURI("report") + id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> save(@RequestBody Action action) {
        this.validateAction(action);
        if (action.getFunction().equals(ActionType.EDIT.toString())) {
            return new ResponseEntity<>(reportService.edit(action), HttpStatus.OK);
        } else if (action.getFunction().equals(ActionType.DELETE.toString())) {
            return new ResponseEntity<>(reportService.delete(action), HttpStatus.OK);
        }
        throw new ValidationException("Incorrect action passed!");
    }
}
