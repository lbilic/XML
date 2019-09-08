package com.uns.ac.rs.xml.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.domain.enums.ActionType;
import com.uns.ac.rs.xml.services.nonProcessService.DrugService;
import com.uns.ac.rs.xml.util.Validator;
import com.uns.ac.rs.xml.util.actions.Action;

import java.util.Calendar;

@RestController
@RequestMapping("/drugs")
public class DrugController extends ValidatorController {

    @Autowired
    private DrugService drugService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>(drugService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> findById(@PathVariable String id) {
        return new ResponseEntity<>(drugService.findById(mapper.getURI("drug") + id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> save(@RequestBody Action action) {
        this.validateAction(action);
        if (action.getFunction().equals(ActionType.DELETE.toString())) {
            return new ResponseEntity<>(drugService.delete(action), HttpStatus.OK);
        } else if (action.getFunction().equals(ActionType.EDIT.toString())) {
            return new ResponseEntity<>(drugService.edit(action), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(drugService.save(action), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/{diagnosis}/{patientId}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> patientDiagnosis(@PathVariable String diagnosis, @PathVariable String patientId) {
        return new ResponseEntity<>(drugService.getDrugForDiagnosis(diagnosis,
                mapper.getURI("chart") + patientId), HttpStatus.OK);
    }


}
