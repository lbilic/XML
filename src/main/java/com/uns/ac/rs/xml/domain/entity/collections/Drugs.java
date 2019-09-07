package com.uns.ac.rs.xml.domain.entity.collections;


import com.uns.ac.rs.xml.domain.entity.Drug;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "drug"
})
@XmlRootElement(name = "drugs", namespace = "http://zis.rs/xml/schemes/drugs")
public class Drugs {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/drug")
    protected List<Drug> drug;

    /**
     * Gets the value of the drug property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drug property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrug().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Drug }
     */
    public List<Drug> getDrug() {
        if (drug == null) {
            drug = new ArrayList<Drug>();
        }
        return this.drug;
    }

}
