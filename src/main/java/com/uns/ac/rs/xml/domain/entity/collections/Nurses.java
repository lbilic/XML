package com.uns.ac.rs.xml.domain.entity.collections;


import com.uns.ac.rs.xml.domain.entity.Nurse;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "nurse"
})
@XmlRootElement(name = "nurses", namespace = "http://zis.rs/xml/schemes/nurses")
public class Nurses {

    @XmlElement(name = "nurse", namespace = "http://localhost:8080/xml/schemes/nurse")
    protected List<Nurse> nurse;

    /**
     * Gets the value of the nurse property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nurse property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNurse().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Nurse }
     */
    public List<Nurse> getNurse() {
        if (nurse == null) {
            nurse = new ArrayList<Nurse>();
        }
        return this.nurse;
    }

}
