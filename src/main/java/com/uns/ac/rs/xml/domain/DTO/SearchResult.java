package com.uns.ac.rs.xml.domain.DTO;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchResult {

    private ArrayList<String> idList;

    public SearchResult() {
        this.idList = new ArrayList<>();
    }

    public SearchResult(String text) {
        this.idList = new ArrayList<>();
        this.idList.addAll(Arrays.asList(text.split("-")));
    }

    public ArrayList<String> getText() {
        return idList;
    }

    public void setText(ArrayList<String> text) {
        this.idList = text;
    }
}
