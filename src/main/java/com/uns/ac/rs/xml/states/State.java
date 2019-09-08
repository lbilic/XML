package com.uns.ac.rs.xml.states;

import com.uns.ac.rs.xml.domain.enums.Options;

public abstract class State {

    private Options option;

    abstract String processRequest(com.uns.ac.rs.xml.util.actions.Action action);

    public Options getOptions() {
        return option;
    }

    public void setOption(Options option) {
        this.option = option;
    }
}
