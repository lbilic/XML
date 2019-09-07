package com.uns.ac.rs.xml.domain.enums;

public enum UserType {

    DOCTOR("DOCTOR"),
    NURSE("NURSE"),
    PATIENT("PATIENT");

    private final String text;

    UserType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
