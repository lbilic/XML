package com.uns.ac.rs.xml.domain.enums;

public enum ActionType {

    ADD("ADD"),
    EDIT("EDIT"),
    DELETE("DELETE");

    private final String text;

    ActionType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() { return text; }
}
