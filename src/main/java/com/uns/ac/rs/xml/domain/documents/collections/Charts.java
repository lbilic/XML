package com.uns.ac.rs.xml.domain.documents.collections;

import com.uns.ac.rs.xml.domain.documents.Chart;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "chart"
})
@XmlRootElement(name = "charts", namespace = "http://zis.rs/zis/seme/charts")
public class Charts {

    @XmlElement(name = "chart", namespace = "http://zis.rs/xml/schemes/chart")
    protected List<Chart> chart;

    /**
     * Gets the value of the chart property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chart property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChart().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Chart }
     */
    public List<Chart> getChart() {
        if (chart == null) {
            chart = new ArrayList<Chart>();
        }
        return this.chart;
    }

}
