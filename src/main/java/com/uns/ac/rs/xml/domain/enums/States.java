package com.uns.ac.rs.xml.domain.enums;

public enum States {

    SCHEDULING("scheduling"),
    WAITING("waiting"),
    EXAM_CHANGED("exam_changed"),
    GENERALIST_EXAAM("exam_doctor_gen"),
    SPECIALIST_EXAM("exam_doctor_spec"),
    END("end");

    private final String text;

    States(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
