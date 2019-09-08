package com.uns.ac.rs.xml.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.domain.SearchQuery;
import com.uns.ac.rs.xml.repository.SearchRDFRepository;
import com.uns.ac.rs.xml.repository.RDFRepository;

@Service
public class SearchService {

    @Autowired
    private SearchRDFRepository searchRDFRepository;

    @Autowired
    private RDFRepository rdfRepository;

    public SearchService() {
    }

    public String generalQuery(SearchQuery searchQuery) {
        return searchRDFRepository.generalQuery(searchQuery);
    }

    public String exportMetadata(String document, String format) {
        return rdfRepository.exportMetadata(document, format);
    }

    public String linksToDocument(String id) { return searchRDFRepository.linksToDocument(id); }
}
