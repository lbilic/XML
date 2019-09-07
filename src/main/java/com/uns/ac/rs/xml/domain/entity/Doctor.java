package com.uns.ac.rs.xml.domain.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "user",
        "type",
        "levelOfCare"
})
@XmlRootElement(name = "doctor", namespace = "http://zis.rs/xml/schemes/doctor")
public class Doctor {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/doctor", required = true)
    protected Doctor.User user;

    @XmlElement(namespace = "http://zis.rs/xml/schemes/doctor", required = true, defaultValue = "generalist")
    protected String type;

    @XmlElement(name = "levelOfCare", namespace = "http://zis.rs/xml/schemes/doctor", required = true)
    protected String levelOfCare;

    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;

    public Doctor.User getUser() {
        return user;
    }

    public void setUser(Doctor.User value) {
        this.user = value;
    }

    public String getTip() {
        return type;
    }

    public void setTip(String value) {
        this.type = value;
    }

    public String getLevelOfCare() {
        return levelOfCare;
    }

    public void setLevelOfCare(String value) {
        this.levelOfCare = value;
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

        @XmlAttribute(name = "id", namespace = "http://zis.rs/xml/schemes/doctor", required = true)
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
