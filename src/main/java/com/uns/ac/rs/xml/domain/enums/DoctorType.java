package com.uns.ac.rs.xml.domain.enums;

public enum DoctorType {

    GENERAL_PRACTICE("general_practice"),
    SPECIALIST("specialist");

    private final String text;

    DoctorType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
