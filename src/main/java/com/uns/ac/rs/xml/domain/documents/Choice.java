package com.uns.ac.rs.xml.domain.documents;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "institutionName",
        "formType",
        "reasonForChange",
        "previousDoctorInstitution",
        "previousDoctor",
        "doctor",
        "patient",
        "date"
})
@XmlRootElement(name = "choice")
public class Choice {

    @XmlElement(name = "institution_name", required = true)
    protected String institutionName;
    @XmlElement(name = "form_type", required = true, defaultValue = "izbor")
    protected String formType;
    @XmlElement(name = "reason_for_change", required = true, defaultValue = "ne_radi")
    protected String reasonForChange;
    @XmlElement(name = "previous_doctor_institution", required = true)
    protected String previousDoctorInstitution;
    @XmlElement(name = "previous_doctor", required = true)
    protected com.uns.ac.rs.xml.domain.documents.Choice.PreviousDoctor previousDoctor;
    @XmlElement(required = true)
    protected com.uns.ac.rs.xml.domain.documents.Choice.Doctor doctor;
    @XmlElement(required = true)
    protected com.uns.ac.rs.xml.domain.documents.Choice.Patient patient;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "label")
    protected String label;
    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;
    @XmlAttribute(name = "active", required = true)
    protected boolean active;

    /**
     * Gets the value of the institutionName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * Sets the value of the institutionName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInstitutionName(String value) {
        this.institutionName = value;
    }

    /**
     * Gets the value of the formType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFormType() {
        return formType;
    }

    /**
     * Sets the value of the formType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFormType(String value) {
        this.formType = value;
    }

    /**
     * Gets the value of the reasonForChange property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReasonForChange() {
        return reasonForChange;
    }

    /**
     * Sets the value of the reasonForChange property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReasonForChange(String value) {
        this.reasonForChange = value;
    }

    /**
     * Gets the value of the previousDoctorInstitution property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPreviousDoctorInstitution() {
        return previousDoctorInstitution;
    }

    /**
     * Sets the value of the previousDoctorInstitution property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPreviousDoctorInstitution(String value) {
        this.previousDoctorInstitution = value;
    }

    /**
     * Gets the value of the previousDoctor property.
     *
     * @return possible object is
     * {@link com.uns.ac.rs.xml.domain.documents.Choice.PreviousDoctor }
     */
    public com.uns.ac.rs.xml.domain.documents.Choice.PreviousDoctor getPreviousDoctor() {
        return previousDoctor;
    }

    /**
     * Sets the value of the previousDoctor property.
     *
     * @param value allowed object is
     *              {@link com.uns.ac.rs.xml.domain.documents.Choice.PreviousDoctor }
     */
    public void setPreviousDoctor(com.uns.ac.rs.xml.domain.documents.Choice.PreviousDoctor value) {
        this.previousDoctor = value;
    }

    /**
     * Gets the value of the doctor property.
     *
     * @return possible object is
     * {@link com.uns.ac.rs.xml.domain.documents.Choice.Doctor }
     */
    public com.uns.ac.rs.xml.domain.documents.Choice.Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the value of the doctor property.
     *
     * @param value allowed object is
     *              {@link com.uns.ac.rs.xml.domain.documents.Choice.Doctor }
     */
    public void setDoctor(com.uns.ac.rs.xml.domain.documents.Choice.Doctor value) {
        this.doctor = value;
    }

    /**
     * Gets the value of the patient property.
     *
     * @return possible object is
     * {@link com.uns.ac.rs.xml.domain.documents.Choice.Patient }
     */
    public com.uns.ac.rs.xml.domain.documents.Choice.Patient getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     *
     * @param value allowed object is
     *              {@link com.uns.ac.rs.xml.domain.documents.Choice.Patient }
     */
    public void setPatient(com.uns.ac.rs.xml.domain.documents.Choice.Patient value) {
        this.patient = value;
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
     * Gets the value of the label property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLabel() {
        if (label == null) {
            return "pil1";
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

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/scheme/choice")
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

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/scheme/choice")
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
    public static class PreviousDoctor {

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/scheme/choice")
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
