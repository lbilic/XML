package com.uns.ac.rs.xml.domain.entity.collections;


import com.uns.ac.rs.xml.domain.entity.User;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "user"
})
@XmlRootElement(name = "users", namespace = "http://zis.rs/xml/schemes/users")
public class Users {

    @XmlElement(namespace = "http://zis.rs/xml/schemes/user")
    protected List<User> user;

    /**
     * Gets the value of the user property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the user property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUser().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     */
    public List<User> getUser() {
        if (user == null) {
            user = new ArrayList<User>();
        }
        return this.user;
    }

}
