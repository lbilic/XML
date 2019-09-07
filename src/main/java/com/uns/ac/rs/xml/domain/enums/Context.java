package com.uns.ac.rs.xml.domain.enums;

public enum Context {

    ACCEPT("ACCEPT"),
    EDIT("EDIT");

    private final String text;

    Context(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
