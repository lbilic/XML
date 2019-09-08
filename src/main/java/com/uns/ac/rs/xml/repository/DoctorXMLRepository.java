package com.uns.ac.rs.xml.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.database.ConfigureConnection;
import com.uns.ac.rs.xml.util.database.Mapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class DoctorXMLRepository {

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

    private String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}

