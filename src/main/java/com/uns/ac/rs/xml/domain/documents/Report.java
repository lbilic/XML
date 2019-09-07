package com.uns.ac.rs.xml.domain.documents;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "diagnosis",
        "anamnesis",
        "therapy",
        "date",
        "patient",
        "doctor"
})
@XmlRootElement(name = "report", namespace = "http://zis.rs/xml/schemes/report")
public class Report {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/report", required = true)
    protected String diagnosis;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/report", required = true)
    protected String anamnesis;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/report", required = true)
    protected String therapy;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/report", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/report", required = true)
    protected Report.Patient patient;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/report", required = true)
    protected Report.Doctor doctor;
    @XmlAttribute(name = "label", required = true)
    protected String label;
    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;
    @XmlAttribute(name = "active", required = true)
    protected boolean active;

    /**
     * Gets the value of the diagnosis property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the value of the diagnosis property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDiagnosis(String value) {
        this.diagnosis = value;
    }

    /**
     * Gets the value of the anamnesis property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAnamnesis() {
        return anamnesis;
    }

    /**
     * Sets the value of the anamnesis property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAnamnesis(String value) {
        this.anamnesis = value;
    }

    /**
     * Gets the value of the therapy property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTherapy() {
        return therapy;
    }

    /**
     * Sets the value of the therapy property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTherapy(String value) {
        this.therapy = value;
    }

    /**
     * Gets the value of the date property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the patient property.
     *
     * @return possible object is
     * {@link Report.Patient }
     */
    public Report.Patient getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     *
     * @param value allowed object is
     *              {@link Report.Patient }
     */
    public void setPatient(Report.Patient value) {
        this.patient = value;
    }

    /**
     * Gets the value of the doctor property.
     *
     * @return possible object is
     * {@link Report.Doctor }
     */
    public Report.Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the value of the doctor property.
     *
     * @param value allowed object is
     *              {@link Report.Doctor }
     */
    public void setDoctor(Report.Doctor value) {
        this.doctor = value;
    }

    /**
     * Gets the value of the label property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLabel() {
        if (label == null) {
            return "il1";
        } else {
            return label;
        }
    }

    /**
     * Sets the value of the label property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the active property.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     */
    public void setActive(boolean value) {
        this.active = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Doctor {

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/report", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String identifier;

        /**
         * Gets the value of the identifier property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getIdentifier() {
            return identifier;
        }

        /**
         * Sets the value of the identifier property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setIdentifier(String value) {
            this.identifier = value;
        }

    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Patient {

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/report", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String identifier;

        /**
         * Gets the value of the identifier property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getIdentifier() {
            return identifier;
        }

        /**
         * Sets the value of the identifier property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setIdentifier(String value) {
            this.identifier = value;
        }

    }

}
