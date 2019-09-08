package com.uns.ac.rs.xml.controller;

import com.uns.ac.rs.xml.domain.DTO.Login;
import com.uns.ac.rs.xml.services.nonProcessService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.domain.enums.ActionType;
import com.uns.ac.rs.xml.util.actions.Action;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController extends ValidatorController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "registration", consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> registration(@RequestBody Action action) {
        this.validateAction(action);
        return new ResponseEntity<>(userService.register(action), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> save(@RequestBody Action action) {
        this.validateAction(action);
        if (action.getFunction().equals(ActionType.DELETE.toString())) {
            return new ResponseEntity<>(userService.delete(action.getContext()), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Login> login(@RequestBody Action action, HttpSession session) {
        this.validateAction(action);
        Login login = userService.login(action);
        session.setAttribute("id", login.getId());
        session.setAttribute("type", login.getType());
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping(path = "/notifications", consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> findNotificationsByUser(@RequestBody Action action) {
        this.validateAction(action);
        return new ResponseEntity<>(userService.findNotificationsByUser(action.getContext()), HttpStatus.OK);
    }

    @GetMapping(path = "/log_out", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> logOut(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>("Successfully logged out!", HttpStatus.OK);
    }

}
