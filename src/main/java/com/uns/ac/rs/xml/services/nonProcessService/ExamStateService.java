package com.uns.ac.rs.xml.services.nonProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.xml.ExamStateXMLRepository;

import java.util.List;

@Service
public class ExamStateService {

    @Autowired
    private ExamStateXMLRepository examStateXMLRepository;

    public List<String> getProcesses() {
        return examStateXMLRepository.getProcesses();
    }

    public void addNewProcess(com.uns.ac.rs.xml.util.actions.Action action) {
        examStateXMLRepository.addNewProcess(action);
    }

    public void editProcess(String state, String patient) {
        examStateXMLRepository.editProcess(state, patient);
    }
}
