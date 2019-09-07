package com.uns.ac.rs.xml.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uns.ac.rs.xml.domain.DTO.SearchResult;
import com.uns.ac.rs.xml.domain.SearchQuery;
import com.uns.ac.rs.xml.services.nonProcessService.SearchService;
import com.uns.ac.rs.xml.util.actions.Action;

@RestController
@RequestMapping("/search")
public class SearchController extends ValidatorController {

    @Autowired
    private SearchService searchService;


    @PostMapping(path = "/generalQuery", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResult> findById(@RequestBody SearchQuery query) {
        return new ResponseEntity<>(new SearchResult(searchService.generalQuery(query)), HttpStatus.OK);
    }

    @GetMapping(path = "/json/{document}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResult> exportMetadataJson(@PathVariable String document) {
        return new ResponseEntity<>(new SearchResult(searchService.exportMetadata(document, "json")), HttpStatus.OK);
    }

    @GetMapping(path = "/rdf/{document}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> exportMetadataRDF(@PathVariable String document) {
        return new ResponseEntity<>(searchService.exportMetadata(document, "rdf"), HttpStatus.OK);
    }

    @PostMapping(path = "/links", consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResult> linksToDocument(@RequestBody Action action) {
        this.validateAction(action);
        return new ResponseEntity<>(new SearchResult(searchService.linksToDocument(action.getContext())),
                HttpStatus.OK);
    }
}
