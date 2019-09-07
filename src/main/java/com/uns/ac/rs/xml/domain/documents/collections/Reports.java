package com.uns.ac.rs.xml.domain.documents.collections;

import com.uns.ac.rs.xml.domain.documents.Report;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "report"
})
@XmlRootElement(name = "reports", namespace = "http://zis.rs/xml/schemes/reports")
public class Reports {

    @XmlElement(name = "report", namespace = "http://zis.rs/xml/schemes/report")
    protected List<Report> report;

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
     * {@link Report }
     */
    public List<Report> getReport() {
        if (report == null) {
            report = new ArrayList<Report>();
        }
        return this.report;
    }

}
