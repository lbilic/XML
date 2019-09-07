package com.uns.ac.rs.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.domain.enums.ActionType;
import com.uns.ac.rs.xml.services.nonProcessService.ChoiceService;
import com.uns.ac.rs.xml.util.actions.Action;

@RestController
@RequestMapping("/choices")
public class ChoiceController extends ValidatorController {

    @Autowired
    private ChoiceService choiceService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>(choiceService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> findById(@PathVariable String id) {
        return new ResponseEntity<>(choiceService.findById(mapper.getURI("choice") + id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> save(@RequestBody Action action) {
        this.validateAction(action);
        if (action.getFunction().equals(ActionType.DELETE.toString())) {
            return new ResponseEntity<>(choiceService.delete(action), HttpStatus.OK);
        } else if (action.getFunction().equals(ActionType.EDIT.toString())) {
            return new ResponseEntity<>(choiceService.edit(action), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(choiceService.save(action), HttpStatus.OK);
        }

    }
}

