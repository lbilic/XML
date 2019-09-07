package com.uns.ac.rs.xml.domain.entity.collections;


import com.uns.ac.rs.xml.domain.entity.Doctor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "doctor"
})
@XmlRootElement(name = "doctors", namespace = "http://zis.rs/xml/schemes/doctors")
public class Doctors {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/doctor")
    protected List<Doctor> doctor;

    /**
     * Gets the value of the doctor property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doctor property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoctor().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Doctor }
     */
    public List<Doctor> getDoctor() {
        if (doctor == null) {
            doctor = new ArrayList<Doctor>();
        }
        return this.doctor;
    }

}
