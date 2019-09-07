package com.uns.ac.rs.xml.domain.entity.collections;


import com.uns.ac.rs.xml.domain.entity.Patient;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "patient"
})
@XmlRootElement(name = "patients", namespace = "http://zis.rs/xml/schemes/patients")
public class Patients {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/patient")
    protected List<Patient> patient;

    /**
     * Gets the value of the patient property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patient property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatient().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Patient }
     */
    public List<Patient> getPatient() {
        if (patient == null) {
            patient = new ArrayList<Patient>();
        }
        return this.patient;
    }

}
