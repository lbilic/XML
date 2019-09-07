package com.uns.ac.rs.xml.domain.documents.collections;
import com.uns.ac.rs.xml.domain.documents.Prescription;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "prescription"
})
@XmlRootElement(name = "prescriptions", namespace = "http://zis.rs/xml/schemes/prescriptions")
public class Prescriptions {

    @XmlElement(name = "prescription", namespace = "http://zis.rs/xml/schemes/prescription")
    protected List<Prescription> prescription;

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
     * {@link Prescription }
     */
    public List<Prescription> getPrescription() {
        if (prescription == null) {
            prescription = new ArrayList<Prescription>();
        }
        return this.prescription;
    }

}
