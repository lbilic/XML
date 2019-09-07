package com.uns.ac.rs.xml.repository.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.ConfigureConnection;
import com.uns.ac.rs.xml.util.Mapper;

@Repository
public class DoctorXMLRepository extends com.uns.ac.rs.xml.util.IOStreamer {

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private Operations operations;

    @Autowired
    private Mapper mapper;

    private String document = "doctors";
    private String documentPrefix = "doctor";

    public String getAll() {
        return operations.getAll(document, "getAllDoctors");
    }

    public String findById(String id) {
        return operations.findById(id, document, "findDoctorById");
    }

    public String countingPatients() {
        return operations.findById("", document, "countingPatients");
    }
}

