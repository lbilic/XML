package com.uns.ac.rs.xml.domain.documents.collections;


import com.uns.ac.rs.xml.domain.documents.Choice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "choice"
})
@XmlRootElement(name = "choices", namespace = "http://zis.rs/xml/schemes/choices")
public class Choices {

    @XmlElement(name = "choice")
    protected List<Choice> choice;

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
     * {@link Choice }
     */
    public List<Choice> getChoice() {
        if (choice == null) {
            choice = new ArrayList<Choice>();
        }
        return this.choice;
    }

}
