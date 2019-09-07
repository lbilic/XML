package com.uns.ac.rs.xml.domain.documents;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "surname",
        "sex",
        "insuranceCarrier",
        "maritalStatus",
        "birthDate",
        "address",
        "phoneNumber",
        "labReferrals",
        "specialistReferrals",
        "prescriptions",
        "reports",
        "choices"
})
@XmlRootElement(name = "chart", namespace = "http://zis.rs/xml/schemes/chart")
public class Chart {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected String name;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected String surname;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected String sex;
    @XmlElement(name = "insurance_carrier", namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected Chart.InsuranceCarrier insuranceCarrier;
    @XmlElement(name = "marital_status", namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected String maritalStatus;
    @XmlElement(name = "birth_date", namespace = "http://zis.rs/xml/schemes/chart", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;
    @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected Chart.Address address;
    @XmlElement(name = "phone_number", namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected String phoneNumber;
    @XmlElement(name = "lab_referrals", namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected Chart.LabReferrals labReferrals;
    @XmlElement(name = "specialist_referrals", namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected Chart.SpecialistReferrals specialistReferrals;
    @XmlElement(name = "prescriptions", namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected Chart.Prescriptions prescriptions;
    @XmlElement(name = "reports", namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected Chart.Reports reports;
    @XmlElement(name = "choices", namespace = "http://zis.rs/xml/schemes/chart", required = true)
    protected Chart.Choices choices;
    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;
    @XmlAttribute(name = "jmbg", required = true)
    protected String jmbg;
    @XmlAttribute(name = "health_card_number", required = true)
    protected String healthCardNumber;
    @XmlAttribute(name = "chart_number", required = true)
    protected String chartNumber;
    @XmlAttribute(name = "lbo", required = true)
    protected String lbo;
    @XmlAttribute(name = "warning")
    protected String warning;
    @XmlAttribute(name = "active", required = true)
    protected boolean active;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the surname property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the sex property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * Gets the value of the insuranceCarrier property.
     *
     * @return possible object is
     * {@link Chart.InsuranceCarrier }
     */
    public Chart.InsuranceCarrier getInsuranceCarrier() {
        return insuranceCarrier;
    }

    /**
     * Sets the value of the insuranceCarrier property.
     *
     * @param value allowed object is
     *              {@link Chart.InsuranceCarrier }
     */
    public void setInsuranceCarrier(Chart.InsuranceCarrier value) {
        this.insuranceCarrier = value;
    }

    /**
     * Gets the value of the maritalStatus property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the value of the maritalStatus property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMaritalStatus(String value) {
        this.maritalStatus = value;
    }

    /**
     * Gets the value of the birthDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the address property.
     *
     * @return possible object is
     * {@link Chart.Address }
     */
    public Chart.Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     *
     * @param value allowed object is
     *              {@link Chart.Address }
     */
    public void setAddress(Chart.Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the labReferrals property.
     *
     * @return possible object is
     * {@link Chart.LabReferrals }
     */
    public Chart.LabReferrals getLabReferrals() {
        return labReferrals;
    }

    /**
     * Sets the value of the labReferrals property.
     *
     * @param value allowed object is
     *              {@link Chart.LabReferrals }
     */
    public void setLabReferrals(Chart.LabReferrals value) {
        this.labReferrals = value;
    }

    /**
     * Gets the value of the specialistReferrals property.
     *
     * @return possible object is
     * {@link Chart.SpecialistReferrals }
     */
    public Chart.SpecialistReferrals getSpecialistReferrals() {
        return specialistReferrals;
    }

    /**
     * Sets the value of the specialistReferrals property.
     *
     * @param value allowed object is
     *              {@link Chart.SpecialistReferrals }
     */
    public void setSpecialistReferrals(Chart.SpecialistReferrals value) {
        this.specialistReferrals = value;
    }

    /**
     * Gets the value of the prescriptions property.
     *
     * @return possible object is
     * {@link Chart.Prescriptions }
     */
    public Chart.Prescriptions getPrescriptions() {
        return prescriptions;
    }

    /**
     * Sets the value of the prescriptions property.
     *
     * @param value allowed object is
     *              {@link Chart.Prescriptions }
     */
    public void setPrescriptions(Chart.Prescriptions value) {
        this.prescriptions = value;
    }

    /**
     * Gets the value of the reports property.
     *
     * @return possible object is
     * {@link Chart.Reports }
     */
    public Chart.Reports getReports() {
        return reports;
    }

    /**
     * Sets the value of the reports property.
     *
     * @param value allowed object is
     *              {@link Chart.Reports }
     */
    public void setReports(Chart.Reports value) {
        this.reports = value;
    }

    /**
     * Gets the value of the choices property.
     *
     * @return possible object is
     * {@link Chart.Choices }
     */
    public Chart.Choices getChoices() {
        return choices;
    }

    /**
     * Sets the value of the choices property.
     *
     * @param value allowed object is
     *              {@link Chart.Choices }
     */
    public void setChoices(Chart.Choices value) {
        this.choices = value;
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
     * Gets the value of the jmbg property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * Sets the value of the jmbg property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setJmbg(String value) {
        this.jmbg = value;
    }

    /**
     * Gets the value of the healthCardNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    /**
     * Sets the value of the healthCardNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHealthCardNumber(String value) {
        this.healthCardNumber = value;
    }

    /**
     * Gets the value of the chartNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getChartNumber() {
        return chartNumber;
    }

    /**
     * Sets the value of the chartNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setChartNumber(String value) {
        this.chartNumber = value;
    }

    /**
     * Gets the value of the lbo property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLbo() {
        return lbo;
    }

    /**
     * Sets the value of the lbo property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLbo(String value) {
        this.lbo = value;
    }

    /**
     * Gets the value of the warning property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Sets the value of the warning property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setWarning(String value) {
        this.warning = value;
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
            "street",
            "number",
            "appartmentNumber",
            "town",
            "municipality"
    })
    public static class Address {

        @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
        protected String street;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger number;
        @XmlElement(name = "number_stana", namespace = "http://zis.rs/xml/schemes/chart", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger appartmentNumber;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
        protected String town;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
        protected Chart.Address.Municipality municipality;

        /**
         * Gets the value of the street property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getStreet() {
            return street;
        }

        /**
         * Sets the value of the street property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setStreet(String value) {
            this.street = value;
        }

        /**
         * Gets the value of the number property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getNumber() {
            return number;
        }

        /**
         * Sets the value of the number property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setNumber(BigInteger value) {
            this.number = value;
        }

        /**
         * Gets the value of the appartmentNumber property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getAppartmentNumber() {
            return appartmentNumber;
        }

        /**
         * Sets the value of the appartmentNumber property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setAppartmentNumber(BigInteger value) {
            this.appartmentNumber = value;
        }

        /**
         * Gets the value of the town property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getTown() {
            return town;
        }

        /**
         * Sets the value of the town property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTown(String value) {
            this.town = value;
        }

        /**
         * Gets the value of the municipality property.
         *
         * @return possible object is
         * {@link Chart.Address.Municipality }
         */
        public Chart.Address.Municipality getMunicipality() {
            return municipality;
        }

        /**
         * Sets the value of the municipality property.
         *
         * @param value allowed object is
         *              {@link Chart.Address.Municipality }
         */
        public void setMunicipality(Chart.Address.Municipality value) {
            this.municipality = value;
        }

        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "name",
                "zipCode"
        })
        public static class Municipality {

            @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
            protected String name;
            @XmlElement(name = "postanski_number", namespace = "http://zis.rs/xml/schemes/chart")
            protected int zipCode;

            /**
             * Gets the value of the name property.
             *
             * @return possible object is
             * {@link String }
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the zipCode property.
             */
            public int getZipCode() {
                return zipCode;
            }

            /**
             * Sets the value of the zipCode property.
             */
            public void setZipCode(int value) {
                this.zipCode = value;
            }

        }

    }
    
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "choice"
    })
    public static class Choices {

        @XmlElement(name = "choice", namespace = "http://zis.rs/xml/schemes/chart")
        protected List<Chart.Choices.Choice> choice;

        /**
         * Gets the value of the choice property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the choice property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChoice().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Chart.Choices.Choice }
         */
        public List<Chart.Choices.Choice> getChoice() {
            if (choice == null) {
                choice = new ArrayList<Chart.Choices.Choice>();
            }
            return this.choice;
        }
        
        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Choice {

            @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/chart", required = true)
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
            "report"
    })
    public static class Reports {

        @XmlElement(name = "report", namespace = "http://zis.rs/xml/schemes/chart")
        protected List<Chart.Reports.Report> report;

        /**
         * Gets the value of the report property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the report property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReport().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Chart.Reports.Report }
         */
        public List<Chart.Reports.Report> getReport() {
            if (report == null) {
                report = new ArrayList<Chart.Reports.Report>();
            }
            return this.report;
        }

        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Report {

            @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/chart", required = true)
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
            "prescription"
    })
    public static class Prescriptions {

        @XmlElement(name = "prescription", namespace = "http://zis.rs/xml/schemes/chart")
        protected List<Chart.Prescriptions.Prescription> prescription;

        /**
         * Gets the value of the prescription property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the prescription property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPrescription().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Chart.Prescriptions.Prescription }
         */
        public List<Chart.Prescriptions.Prescription> getPrescription() {
            if (prescription == null) {
                prescription = new ArrayList<Chart.Prescriptions.Prescription>();
            }
            return this.prescription;
        }

        
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Prescription {

            @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/chart", required = true)
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
            "name",
            "surname",
            "relation",
            "insuranceBasis"
    })
    public static class InsuranceCarrier {

        @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
        protected String name;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
        protected String surname;
        @XmlElement(namespace = "http://zis.rs/xml/schemes/chart", required = true)
        protected String relation;
        @XmlElement(name = "insurance_basis", namespace = "http://zis.rs/xml/schemes/chart", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger insuranceBasis;

        /**
         * Gets the value of the name property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the surname property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getSurname() {
            return surname;
        }

        /**
         * Sets the value of the surname property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setSurname(String value) {
            this.surname = value;
        }

        /**
         * Gets the value of the relation property.
         *
         * @return possible object is
         * {@link String }
         */
        public String getRelation() {
            return relation;
        }

        /**
         * Sets the value of the relation property.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRelation(String value) {
            this.relation = value;
        }

        /**
         * Gets the value of the insuranceBasis property.
         *
         * @return possible object is
         * {@link BigInteger }
         */
        public BigInteger getInsuranceBasis() {
            return insuranceBasis;
        }

        /**
         * Sets the value of the insuranceBasis property.
         *
         * @param value allowed object is
         *              {@link BigInteger }
         */
        public void setInsuranceBasis(BigInteger value) {
            this.insuranceBasis = value;
        }

    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "labReferral"
    })
    public static class LabReferrals {

        @XmlElement(name = "lab_referral", namespace = "http://zis.rs/xml/schemes/chart")
        protected List<Chart.LabReferrals.LabReferral> labReferral;

        /**
         * Gets the value of the labReferral property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the labReferral property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLabReferral().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Chart.LabReferrals.LabReferral }
         */
        public List<Chart.LabReferrals.LabReferral> getLabReferral() {
            if (labReferral == null) {
                labReferral = new ArrayList<Chart.LabReferrals.LabReferral>();
            }
            return this.labReferral;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class LabReferral {

            @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/chart", required = true)
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
            "specialistReferral"
    })
    public static class SpecialistReferrals {

        @XmlElement(name = "specialist_referral", namespace = "http://zis.rs/xml/schemes/chart")
        protected List<Chart.SpecialistReferrals.SpecialistReferral> specialistReferral;

        /**
         * Gets the value of the specialistReferral property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the specialistReferral property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSpecialistReferral().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Chart.SpecialistReferrals.SpecialistReferral }
         */
        public List<Chart.SpecialistReferrals.SpecialistReferral> getSpecialistReferral() {
            if (specialistReferral == null) {
                specialistReferral = new ArrayList<Chart.SpecialistReferrals.SpecialistReferral>();
            }
            return this.specialistReferral;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class SpecialistReferral {

            @XmlAttribute(name = "identifier", namespace = "http://zis.rs/xml/schemes/chart", required = true)
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

}
