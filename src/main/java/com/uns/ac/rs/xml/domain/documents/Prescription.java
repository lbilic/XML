package com.uns.ac.rs.xml.domain.documents;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "healthInstitutionName",
        "insuredPerson",
        "participationReleaseReason",
        "country",
        "prescriptionDate",
        "issueDate",
        "diagnosis",
        "description",
        "prescribedDrug",
        "issuedDrug",
        "doctor"
})
@XmlRootElement(name = "prescription", namespace = "http://zis.rs/xml/schemes/prescription")
public class Prescription {

    @XmlElement(name = "health_institution_name", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    protected String healthInstitutionName;
    @XmlElement(name = "insured_person", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    protected Prescription.InsuredPerson insuredPerson;
    @XmlElement(name = "participation_release_reason", namespace = "http://zis.rs/xml/schemes/prescription", defaultValue = "512")
    protected int participationReleaseReason;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    protected String country;
    @XmlElement(name = "prescription_date", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar prescriptionDate;
    @XmlElement(name = "issue_date", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar issueDate;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    protected String diagnosis;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    protected String description;
    @XmlElement(name = "prescribed_drug", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    protected Prescription.PrescribedDrug prescribedDrug;
    @XmlElement(name = "issued_drug", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    protected Prescription.IssuedDrug issuedDrug;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/prescription", required = true)
    protected Prescription.Doctor doctor;
    @XmlAttribute(name = "label", required = true)
    protected String label;
    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;
    @XmlAttribute(name = "barcode", required = true)
    protected String barKod;
    @XmlAttribute(name = "active", required = true)
    protected boolean active;

    /**
     * Gets the value of the healthInstitutionName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHealthInstitutionName() {
        return healthInstitutionName;
    }

    /**
     * Sets the value of the healthInstitutionName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHealthInstitutionName(String value) {
        this.healthInstitutionName = value;
    }

    /**
     * Gets the value of the insuredPerson property.
     *
     * @return possible object is
     * {@link Prescription.InsuredPerson }
     */
    public Prescription.InsuredPerson getInsuredPerson() {
        return insuredPerson;
    }

    /**
     * Sets the value of the insuredPerson property.
     *
     * @param value allowed object is
     *              {@link Prescription.InsuredPerson }
     */
    public void setInsuredPerson(Prescription.InsuredPerson value) {
        this.insuredPerson = value;
    }

    /**
     * Gets the value of the participationReleaseReason property.
     */
    public int getParticipationReleaseReason() {
        return participationReleaseReason;
    }

    /**
     * Sets the value of the participationReleaseReason property.
     */
    public void setParticipationReleaseReason(int value) {
        this.participationReleaseReason = value;
    }

    /**
     * Gets the value of the country property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the prescriptionDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getPrescriptionDate() {
        return prescriptionDate;
    }

    /**
     * Sets the value of the prescriptionDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setPrescriptionDate(XMLGregorianCalendar value) {
        this.prescriptionDate = value;
    }

    /**
     * Gets the value of the issueDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setIssueDate(XMLGregorianCalendar value) {
        this.issueDate = value;
    }

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
     * Gets the value of the description property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the prescribedDrug property.
     *
     * @return possible object is
     * {@link Prescription.PrescribedDrug }
     */
    public Prescription.PrescribedDrug getPrescribedDrug() {
        return prescribedDrug;
    }

    /**
     * Sets the value of the prescribedDrug property.
     *
     * @param value allowed object is
     *              {@link Prescription.PrescribedDrug }
     */
    public void setPrescribedDrug(Prescription.PrescribedDrug value) {
        this.prescribedDrug = value;
    }

    /**
     * Gets the value of the issuedDrug property.
     *
     * @return possible object is
     * {@link Prescription.IssuedDrug }
     */
    public Prescription.IssuedDrug getIssuedDrug() {
        return issuedDrug;
    }

    /**
     * Sets the value of the issuedDrug property.
     *
     * @param value allowed object is
     *              {@link Prescription.IssuedDrug }
     */
    public void setIssuedDrug(Prescription.IssuedDrug value) {
        this.issuedDrug = value;
    }

    /**
     * Gets the value of the doctor property.
     *
     * @return possible object is
     * {@link Prescription.Doctor }
     */
    public Prescription.Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the value of the doctor property.
     *
     * @param value allowed object is
     *              {@link Prescription.Doctor }
     */
    public void setDoctor(Prescription.Doctor value) {
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
            return "lr1";
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
     * Gets the value of the barKod property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBarcode() {
        return barKod;
    }

    /**
     * Sets the value of the barKod property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBarcode(String value) {
        this.barKod = value;
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
    @XmlType(name = "", propOrder = {
            "amount"
    })
    public static class IssuedDrug {

        @XmlElement(namespace = "http://zis.rs/xml/schemes/prescription")
        protected int amount;
        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String identifier;

        /**
         * Gets the value of the amount property.
         */
        public int getAmount() {
            return amount;
        }

        /**
         * Sets the value of the amount property.
         */
        public void setAmount(int value) {
            this.amount = value;
        }

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
    public static class Doctor {

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
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
    public static class InsuredPerson {

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
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
    public static class PrescribedDrug {

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/prescription", required = true)
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
