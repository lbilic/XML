package com.uns.ac.rs.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.domain.enums.ActionType;
import com.uns.ac.rs.xml.services.nonProcessService.ReferralService;
import com.uns.ac.rs.xml.util.ValidationException;
import com.uns.ac.rs.xml.util.actions.Action;

@RestController
@RequestMapping("/referrals")
public class ReferralController extends ValidatorController {

    @Autowired
    private ReferralService referralService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>(referralService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> findById(@PathVariable String id) {
        return new ResponseEntity<>(referralService.findById(mapper.getURI("referral") + id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> delete(@RequestBody Action action) {
        this.validateAction(action);
        if (action.getFunction().equals(ActionType.DELETE.toString())) {
            return new ResponseEntity<>(referralService.delete(action), HttpStatus.OK);
        } else if (action.getFunction().equals(ActionType.EDIT.toString())) {
            return new ResponseEntity<>(referralService.edit(action), HttpStatus.OK);
        }
        throw new ValidationException("Incorrect action passed");
    }

}
