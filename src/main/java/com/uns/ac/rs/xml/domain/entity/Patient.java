package com.uns.ac.rs.xml.domain.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "user",
        "chart"
})
@XmlRootElement(name = "patient", namespace = "http://zis.rs/xml/schemes/patient")
public class Patient {
    @XmlElement(namespace = "http://zis.rs/xml/schemes/pacijent", required = true)
    protected User user;

    @XmlElement(name = "chart", namespace = "http://zis.rs/xml/schemes/patient", required = true)
    protected Patient.Chart chart;

    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;

    public User getUser() {
        return user;
    }

    public void setUser(User value) {
        this.user = value;
    }

    public Patient.Chart getChart() {
        return chart;
    }

    public void setChart(Patient.Chart value) {
        this.chart = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class User {

        @XmlAttribute(name = "id", namespace = "http://zis.rs/xml/schemes/patient", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String id;

        public String getId() {
            return id;
        }

        public void setId(String value) {
            this.id = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Chart {

        @XmlAttribute(name = "id", namespace = "http://zis.rs/xml/schemes/patient", required = true)
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
