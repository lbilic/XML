package com.uns.ac.rs.xml.domain;

import java.util.ArrayList;

public class SearchQuery {

    private ArrayList<QueryParameter> queryParameters;

    public ArrayList<QueryParameter> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(ArrayList<QueryParameter> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public SearchQuery() {
    }

    public SearchQuery(ArrayList<QueryParameter> queryParameters) {
        this.queryParameters = queryParameters;
    }
}
