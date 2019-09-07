package com.uns.ac.rs.xml.domain.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "user"
})
@XmlRootElement(name = "nurse", namespace = "http://zis.rs/xml/schemes/nurse")
public class Nurse {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/nurse", required = true)
    protected Nurse.User user;

    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;

    public Nurse.User getUser() {
        return user;
    }

    public void setUser(Nurse.User value) {
        this.user = value;
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

        @XmlAttribute(name = "id", namespace = "http://zis.rs/xml/schemes/nurse", required = true)
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
