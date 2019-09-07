package com.uns.ac.rs.xml.domain.documents;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "healthInstitution",
        "insuredPerson",
        "exam",
        "date",
        "doctor"
})
@XmlRootElement(name = "lab_referral", namespace = "http://zis.rs/xml/schemes/lab_referral")
public class LabReferral {

    @XmlElement(name = "health_institution", namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
    protected LabReferral.HealthInstitution healthInstitution;
    @XmlElement(name = "insured_person", namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
    protected LabReferral.InsuredPerson insuredPerson;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
    protected LabReferral.Exam exam;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
    protected LabReferral.Doctor doctor;
    @XmlAttribute(name = "label", required = true)
    protected String label;
    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;
    @XmlAttribute(name = "active", required = true)
    protected boolean active;

    /**
     * Gets the value of the healthInstitution property.
     *
     * @return possible object is
     * {@link LabReferral.HealthInstitution }
     */
    public LabReferral.HealthInstitution getHealthInstitution() {
        return healthInstitution;
    }

    /**
     * Sets the value of the healthInstitution property.
     *
     * @param value allowed object is
     *              {@link LabReferral.HealthInstitution }
     */
    public void setHealthInstitution(LabReferral.HealthInstitution value) {
        this.healthInstitution = value;
    }

    /**
     * Gets the value of the insuredPerson property.
     *
     * @return possible object is
     * {@link LabReferral.InsuredPerson }
     */
    public LabReferral.InsuredPerson getInsuredPerson() {
        return insuredPerson;
    }

    /**
     * Sets the value of the insuredPerson property.
     *
     * @param value allowed object is
     *              {@link LabReferral.InsuredPerson }
     */
    public void setInsuredPerson(LabReferral.InsuredPerson value) {
        this.insuredPerson = value;
    }

    /**
     * Gets the value of the exam property.
     *
     * @return possible object is
     * {@link LabReferral.Exam }
     */
    public LabReferral.Exam getExam() {
        return exam;
    }

    /**
     * Sets the value of the exam property.
     *
     * @param value allowed object is
     *              {@link LabReferral.Exam }
     */
    public void setExam(LabReferral.Exam value) {
        this.exam = value;
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
     * Gets the value of the doctor property.
     *
     * @return possible object is
     * {@link LabReferral.Doctor }
     */
    public LabReferral.Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the value of the doctor property.
     *
     * @param value allowed object is
     *              {@link LabReferral.Doctor }
     */
    public void setDoctor(LabReferral.Doctor value) {
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
            return "lbl";
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

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/lab_referral")
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
    @XmlType(name = "", propOrder = {
            "registryNumber",
            "insuranceBasis"
    })
    public static class InsuredPerson {

        @XmlElement(name = "registry_number", namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
        protected String registryNumber;
        @XmlElement(name = "insurance_basis", namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
        protected String insuranceBasis;
        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/lab_referral")
        @XmlSchemaType(name = "anyURI")
        protected String identifier;

        /**
         * Gets the value of the registryNumber property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getRegistryNumber() {
            return registryNumber;
        }

        /**
         * Sets the value of the registryNumber property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRegistryNumber(String value) {
            this.registryNumber = value;
        }

        /**
         * Gets the value of the insuranceBasis property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getInsuranceBasis() {
            return insuranceBasis;
        }

        /**
         * Sets the value of the insuranceBasis property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setInsuranceBasis(String value) {
            this.insuranceBasis = value;
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
    @XmlType(name = "", propOrder = {
            "diagnosis",
            "materialArrivalDate",
            "materialSender",
            "examSender",
            "examDescription"
    })
    public static class Exam {

        @XmlElement(namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
        protected String diagnosis;
        @XmlElement(name = "material_arrival_date", namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
        protected String materialArrivalDate;
        @XmlElement(name = "material_sender", namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
        protected String materialSender;
        @XmlElement(name = "exam_sender", namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
        protected String examSender;
        @XmlElement(name = "exam_description", namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
        protected String examDescription;

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
         * Gets the value of the materialArrivalDate property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getMaterialArrivalDate() {
            return materialArrivalDate;
        }

        /**
         * Sets the value of the materialArrivalDate property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setMaterialArrivalDate(String value) {
            this.materialArrivalDate = value;
        }

        /**
         * Gets the value of the materialSender property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getMaterialSender() {
            return materialSender;
        }

        /**
         * Sets the value of the materialSender property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setMaterialSender(String value) {
            this.materialSender = value;
        }

        /**
         * Gets the value of the examSender property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getExamSender() {
            return examSender;
        }

        /**
         * Sets the value of the examSender property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setExamSender(String value) {
            this.examSender = value;
        }

        /**
         * Gets the value of the examDescription property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getExamDescription() {
            return examDescription;
        }

        /**
         * Sets the value of the examDescription property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setExamDescription(String value) {
            this.examDescription = value;
        }

    }

    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "sender",
            "receiver"
    })
    public static class HealthInstitution {

        @XmlElement(namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
        protected String sender;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/lab_referral", required = true)
        protected String receiver;

        /**
         * Gets the value of the sender property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getSender() {
            return sender;
        }

        /**
         * Sets the value of the sender property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setSender(String value) {
            this.sender = value;
        }

        /**
         * Gets the value of the receiver property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getReceiver() {
            return receiver;
        }

        /**
         * Sets the value of the receiver property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setReceiver(String value) {
            this.receiver = value;
        }

    }

}
