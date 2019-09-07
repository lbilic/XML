package com.uns.ac.rs.xml.domain.entity;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "doctor",
        "date"
})
@XmlRootElement(name = "exam", namespace = "http://zis.rs/xml/schemes/exam")
public class Exam {
    @XmlElement(namespace = "http://zis.rs/xml/schemes/exam", required = true)
    protected Exam.Doctor doctor;

    @XmlElement(namespace = "http://zis.rs/xml/schemes/exam", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;

    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;

    @XmlAttribute(name = "active", required = true)
    protected boolean active;

    public Exam.Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Exam.Doctor value) {
        this.doctor = value;
    }

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean value) {
        this.active = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Doctor {

        @XmlAttribute(name = "id", namespace = "http://zis.rs/xml/schemes/exam", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String id;

        public String getId() {
            return id;
        }

        public void setId(String value) {
            this.id = value;
        }

    }
}
