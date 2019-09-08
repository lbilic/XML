package com.uns.ac.rs.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.services.nonProcessService.TransformationsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/transformations")
public class TransformationsController {

    @Autowired
    private TransformationsService transformationsService;

    @GetMapping("/prescription/{id}")
    public ResponseEntity<String> transformPrescription(@PathVariable Long id) {
        String xmlPath = "src/main/resources/generated/prescriptions.xml";
        String xslPath = "src/main/resources/xsl/prescriptions.xsl";
        String htmlPath = "src/main/resources/static/prescriptions" + id.toString() + ".html";
        String pdfPath = "src/main/resources/static/prescriptions" + id.toString() + ".pdf";
        String xml = transformationsService.transformPrescription(id, xmlPath, xslPath, htmlPath, pdfPath);
        return new ResponseEntity<String>(xml, HttpStatus.OK);
    }

    @GetMapping("/referral/{id}")
    public ResponseEntity<String> transformReferral(@PathVariable Long id) {
        String xmlPath = "src/main/resources/generated/referral.xml";
        String xslPath = "src/main/resources/xsl/referrals.xsl";
        String htmlPath = "src/main/resources/static/referral" + id.toString() + ".html";
        String pdfPath = "src/main/resources/static/referral" + id.toString() + ".pdf";
        String xml = transformationsService.transformReferral(id, xmlPath, xslPath, htmlPath, pdfPath);
        return new ResponseEntity<String>(xml, HttpStatus.OK);
    }

    @GetMapping("/choice/{id}")
    public ResponseEntity<String> transformChoice(@PathVariable Long id) {
        String xmlPath = "src/main/resources/generated/choices.xml";
        String xslPath = "src/main/resources/xsl/choice.xsl";
        String htmlPath = "src/main/resources/static/choice"+id.toString()+".html";
        String pdfPath = "src/main/resources/static/choice"+id.toString()+"pdf";
        String xml = transformationsService.transformChoice(id, xmlPath, xslPath, htmlPath, pdfPath);
        return new ResponseEntity<String>(xml, HttpStatus.OK);
    }

    @GetMapping("/report/{id}")
    public ResponseEntity<String> transformReport(@PathVariable Long id) {
        String xmlPath = "src/main/resources/generated/reports.xml";
        String xslPath = "src/main/resources/xsl/report1.xsl";
        String htmlPath = "src/main/resources/static/report"+id.toString()+".html";
        String pdfPath = "src/main/resources/static/report"+id.toString()+"pdf";
        String xml = transformationsService.transformReport(id, xmlPath, xslPath, htmlPath, pdfPath);
        return new ResponseEntity<String>(xml, HttpStatus.OK);
    }
}
