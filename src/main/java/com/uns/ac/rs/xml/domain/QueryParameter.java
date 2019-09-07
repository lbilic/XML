package com.uns.ac.rs.xml.domain;

public class QueryParameter {

    private String attributeName;
    private String value;
    private String operator;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public QueryParameter() {
    }

    public QueryParameter(String attributeName, String value, String operator) {
        this.attributeName = attributeName;
        this.value = value;
        this.operator = operator;
    }
}
