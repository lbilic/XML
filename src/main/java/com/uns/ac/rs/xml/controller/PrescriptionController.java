package com.uns.ac.rs.xml.controller;

import com.uns.ac.rs.xml.domain.enums.ActionType;
import com.uns.ac.rs.xml.util.actions.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.services.nonProcessService.PrescriptionService;
import com.uns.ac.rs.xml.util.ValidationException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("/prescriptions")
public class PrescriptionController extends ValidatorController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>(prescriptionService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> findById(@PathVariable String id) {
        return new ResponseEntity<>(prescriptionService.findById(mapper.getURI("prescription") + id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> delete(@RequestBody Action action) {
        this.validateAction(action);
        if (action.getFunction().equals(ActionType.DELETE.toString())) {
            return new ResponseEntity<>(prescriptionService.delete(action), HttpStatus.OK);
        } else if (action.getFunction().equals(ActionType.EDIT.toString())) {
            return new ResponseEntity<>(prescriptionService.edit(action), HttpStatus.OK);
        }
        throw new ValidationException("Incorrect action passed");
    }
}
