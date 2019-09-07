package com.uns.ac.rs.xml.domain.documents.collections;

import com.uns.ac.rs.xml.domain.documents.LabReferral;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "referral"
})
@XmlRootElement(name = "referrals", namespace = "http://zis.rs/xml/schemes/referrals")
public class LabReferrals {

    @XmlElement(name = "referral", namespace = "http://zis.rs/xml/schemes/referral")
    protected List<LabReferral> referral;

    /**
     * Gets the value of the referral property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referral property.
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
     * {@link LabReferral }
     */
    public List<LabReferral> getLabReferral() {
        if (referral == null) {
            referral = new ArrayList<LabReferral>();
        }
        return this.referral;
    }

}
