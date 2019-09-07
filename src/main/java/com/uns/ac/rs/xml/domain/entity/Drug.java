package com.uns.ac.rs.xml.domain.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "code",
        "purpose"
})
@XmlRootElement(name = "drug", namespace = "http://zis.rs/xml/schemes/drug")
public class Drug {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/drug", required = true)
    protected String name;

    @XmlElement(namespace = "http://zis.rs/xml/schemes/drug", required = true)
    protected String code;

    @XmlElement(namespace = "http://zis.rs/xml/schemes/drug", required = true)
    protected String purpose;

    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;

    @XmlAttribute(name = "active", required = true)
    protected boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
