package com.uns.ac.rs.xml.domain.documents.collections;

import com.uns.ac.rs.xml.domain.documents.SpecialistReferral;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "specialistReferral"
})
@XmlRootElement(name = "specialist_referrals", namespace = "http://zis.rs/xml/schemes/specialist_referrals")
public class SpecialistReferrals {

    @XmlElement(name = "specialist_referral", namespace = "http://zis.rs/xml/schemes/specialist_referral")
    protected List<SpecialistReferral> specialistReferral;

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
     * {@link SpecialistReferral }
     */
    public List<SpecialistReferral> getSpecialistReferral() {
        if (specialistReferral == null) {
            specialistReferral = new ArrayList<SpecialistReferral>();
        }
        return this.specialistReferral;
    }

}
