package com.uns.ac.rs.xml.domain.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "surname",
        "jmbg",
        "username",
        "password"
})
@XmlRootElement(name = "user", namespace = "http://zis.rs/xml/schemes/user")
public class User {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/user", required = true)
    protected String name;

    @XmlElement(namespace = "http://zis.rs/xml/schemes/user", required = true)
    protected String surname;

    @XmlElement(namespace = "http://zis.rs/xml/schemes/user", required = true)
    protected String jmbg;

    @XmlElement(name = "username", namespace = "http://zis.rs/xml/schemes/user", required = true)
    protected String username;

    @XmlElement(namespace = "http://zis.rs/xml/schemes/user", required = true)
    protected String password;

    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;

    @XmlAttribute(name = "active", required = true)
    protected boolean active;

    public String getIme() {
        return name;
    }

    public void setIme(String value) {
        this.name = value;
    }

    public String getPrezname() {
        return surname;
    }

    public void setPrezname(String value) {
        this.surname = value;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String value) {
        this.jmbg = value;
    }

    public String getKorisnickoIme() {
        return username;
    }

    public void setKorisnickoIme(String value) {
        this.username = value;
    }

    public String getLozinka() {
        return password;
    }

    public void setLozinka(String value) {
        this.password = value;
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

}
