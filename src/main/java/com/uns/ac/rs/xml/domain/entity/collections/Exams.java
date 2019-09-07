package com.uns.ac.rs.xml.domain.entity.collections;


import com.uns.ac.rs.xml.domain.entity.Exam;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "exam"
})
@XmlRootElement(name = "exams", namespace = "http://zis.rs/xml/schemes/exams")
public class Exams {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/exam")
    protected List<Exam> exam;

    /**
     * Gets the value of the exam property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exam property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExam().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Exam }
     */
    public List<Exam> getExam() {
        if (exam == null) {
            exam = new ArrayList<Exam>();
        }
        return this.exam;
    }

}
