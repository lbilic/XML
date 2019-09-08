package com.uns.ac.rs.xml.repository.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.Mapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class DrugXMLRepository {

    @Autowired
    private Operations operations;

    @Autowired
    private Mapper mapper;

    private String document = "drugs";
    private String documentPrefix = "drug";

    public String getAll() {
        return operations.getAll(document, "getAllDrugs");
    }

    public String findById(String id) {
        return operations.findById(id, document, "findDrugById");
    }

    public String save(com.uns.ac.rs.xml.util.actions.Action action) {
        return operations.save(mapper.getDocument(action, "drug"), document, documentPrefix);
    }

    public String delete(com.uns.ac.rs.xml.util.actions.Action action) {
        return operations.delete(action, document, documentPrefix, "findDrugById");
    }

    public String edit(com.uns.ac.rs.xml.util.actions.Action action) {
        return operations.edit(action, document, documentPrefix);
    }

    public String getDrugForDiagnosis(String diagnosis, String patientId) {
        return operations.getDrugForDiagnosis(diagnosis, patientId);
    }

    private String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
