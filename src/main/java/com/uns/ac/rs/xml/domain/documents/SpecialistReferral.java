package com.uns.ac.rs.xml.domain.documents;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "healthInstitution",
        "insuredPerson",
        "exam",
        "specialistReport"
})
@XmlRootElement(name = "specialist_referral", namespace = "http://zis.rs/xml/schemes/specialist_referral")
public class SpecialistReferral {

    @XmlElement(name = "health_institution", namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
    protected SpecialistReferral.HealthInstitution healthInstitution;
    @XmlElement(name = "insured_person", namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
    protected SpecialistReferral.InsuredPerson insuredPerson;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
    protected SpecialistReferral.Exam exam;
    @XmlElement(name = "specialist_report", namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
    protected SpecialistReferral.SpecialistReport specialistReport;
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
     * {@link SpecialistReferral.HealthInstitution }
     */
    public SpecialistReferral.HealthInstitution getHealthInstitution() {
        return healthInstitution;
    }

    /**
     * Sets the value of the healthInstitution property.
     *
     * @param value allowed object is
     *              {@link SpecialistReferral.HealthInstitution }
     */
    public void setHealthInstitution(SpecialistReferral.HealthInstitution value) {
        this.healthInstitution = value;
    }

    /**
     * Gets the value of the insuredPerson property.
     *
     * @return possible object is
     * {@link SpecialistReferral.InsuredPerson }
     */
    public SpecialistReferral.InsuredPerson getInsuredPerson() {
        return insuredPerson;
    }

    /**
     * Sets the value of the insuredPerson property.
     *
     * @param value allowed object is
     *              {@link SpecialistReferral.InsuredPerson }
     */
    public void setInsuredPerson(SpecialistReferral.InsuredPerson value) {
        this.insuredPerson = value;
    }

    /**
     * Gets the value of the exam property.
     *
     * @return possible object is
     * {@link SpecialistReferral.Exam }
     */
    public SpecialistReferral.Exam getExam() {
        return exam;
    }

    /**
     * Sets the value of the exam property.
     *
     * @param value allowed object is
     *              {@link SpecialistReferral.Exam }
     */
    public void setExam(SpecialistReferral.Exam value) {
        this.exam = value;
    }

    /**
     * Gets the value of the specialistReport property.
     *
     * @return possible object is
     * {@link SpecialistReferral.SpecialistReport }
     */
    public SpecialistReferral.SpecialistReport getSpecialistReport() {
        return specialistReport;
    }

    /**
     * Sets the value of the specialistReport property.
     *
     * @param value allowed object is
     *              {@link SpecialistReferral.SpecialistReport }
     */
    public void setSpecialistReport(SpecialistReferral.SpecialistReport value) {
        this.specialistReport = value;
    }

    /**
     * Gets the value of the label property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLabel() {
        if (label == null) {
            return "oz2";
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
    @XmlType(name = "", propOrder = {
            "disease",
            "results",
            "specialist"
    })
    public static class SpecialistReport {

        @XmlElement(namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        protected String disease;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        protected String results;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        protected SpecialistReferral.SpecialistReport.Specialist specialist;

        /**
         * Gets the value of the disease property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDisease() {
            return disease;
        }

        /**
         * Sets the value of the disease property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDisease(String value) {
            this.disease = value;
        }

        /**
         * Gets the value of the results property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getResults() {
            return results;
        }

        /**
         * Sets the value of the results property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setResults(String value) {
            this.results = value;
        }

        /**
         * Gets the value of the specialist property.
         *
         * @return possible object is
         * {@link SpecialistReferral.SpecialistReport.Specialist }
         */
        public SpecialistReferral.SpecialistReport.Specialist getSpecialist() {
            return specialist;
        }

        /**
         * Sets the value of the specialist property.
         *
         * @param value allowed object is
         *              {@link SpecialistReferral.SpecialistReport.Specialist }
         */
        public void setSpecialist(SpecialistReferral.SpecialistReport.Specialist value) {
            this.specialist = value;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Specialist {

            @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/specialist_referral")
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

	
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class InsuredPerson {

        @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/specialist_referral")
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
            "dateBegin",
            "dateEnd",
            "anamnesis",
            "protocolNumber",
            "doctor"
    })
    public static class Exam {

        @XmlElement(name = "date_begin", namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateBegin;
        @XmlElement(name = "date_end", namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateEnd;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        protected String anamnesis;
        @XmlElement(name = "protocol_number", namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        protected String protocolNumber;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        protected SpecialistReferral.Exam.Doctor doctor;

        /**
         * Gets the value of the dateBegin property.
         *
         * @return possible object is
         * {@link XMLGregorianCalendar }
         */
        public XMLGregorianCalendar getDateBegin() {
            return dateBegin;
        }

        /**
         * Sets the value of the dateBegin property.
         *
         * @param value allowed object is
         *              {@link XMLGregorianCalendar }
         */
        public void setDateBegin(XMLGregorianCalendar value) {
            this.dateBegin = value;
        }

        /**
         * Gets the value of the dateEnd property.
         *
         * @return possible object is
         * {@link XMLGregorianCalendar }
         */
        public XMLGregorianCalendar getDateEnd() {
            return dateEnd;
        }

        /**
         * Sets the value of the dateEnd property.
         *
         * @param value allowed object is
         *              {@link XMLGregorianCalendar }
         */
        public void setDateEnd(XMLGregorianCalendar value) {
            this.dateEnd = value;
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
         * Gets the value of the protocolNumber property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getProtocolNumber() {
            return protocolNumber;
        }

        /**
         * Sets the value of the protocolNumber property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setProtocolNumber(String value) {
            this.protocolNumber = value;
        }

        /**
         * Gets the value of the doctor property.
         *
         * @return possible object is
         * {@link SpecialistReferral.Exam.Doctor }
         */
        public SpecialistReferral.Exam.Doctor getDoctor() {
            return doctor;
        }

        /**
         * Sets the value of the doctor property.
         *
         * @param value allowed object is
         *              {@link SpecialistReferral.Exam.Doctor }
         */
        public void setDoctor(SpecialistReferral.Exam.Doctor value) {
            this.doctor = value;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Doctor {

            @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/specialist_referral")
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


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "receiver",
            "sender"
    })
    public static class HealthInstitution {

        @XmlElement(namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        protected String receiver;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/specialist_referral", required = true)
        protected String sender;

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

    }

}
