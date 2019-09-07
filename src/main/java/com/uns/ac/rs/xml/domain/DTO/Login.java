package com.uns.ac.rs.xml.domain.DTO;

public class Login {

    private String id;
    private String type;

    public Login() {
    }

    public Login(String token) {
        String[] tokeni = token.split("-");
        this.id = tokeni[0];
        this.type = tokeni[1];
    }

    public Login(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
