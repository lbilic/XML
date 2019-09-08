package com.uns.ac.rs.xml.util.actions;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "context",
        "function",
        "content"
})
@XmlRootElement(name = "action")
public class Action {

    @XmlElement(required = true)
    protected String context;
    @XmlElement(required = true)
    protected String function;
    @XmlElement(required = true)
    protected com.uns.ac.rs.xml.util.actions.Action.Content content;

    /**
     * Gets the value of the context property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets the value of the context property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setContext(String value) {
        this.context = value;
    }

    /**
     * Gets the value of the function property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFunction() {
        return function;
    }

    /**
     * Sets the value of the function property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFunction(String value) {
        this.function = value;
    }

    /**
     * Gets the value of the content property.
     *
     * @return possible object is
     * {@link com.uns.ac.rs.xml.util.actions.Action.Content }
     */
    public com.uns.ac.rs.xml.util.actions.Action.Content getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     *
     * @param value allowed object is
     *              {@link com.uns.ac.rs.xml.util.actions.Action.Content }
     */
    public void setContent(com.uns.ac.rs.xml.util.actions.Action.Content value) {
        this.content = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "any"
    })
    public static class Content {

        @XmlAnyElement(lax = true)
        protected Object any;

        /**
         * Gets the value of the any property.
         *
         * @return possible object is
         * {@link Object }
         */
        public Object getAny() {
            return any;
        }

        /**
         * Sets the value of the any property.
         *
         * @param value allowed object is
         *              {@link Object }
         */
        public void setAny(Object value) {
            this.any = value;
        }

    }

}
