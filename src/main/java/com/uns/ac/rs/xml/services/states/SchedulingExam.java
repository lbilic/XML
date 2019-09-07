package com.uns.ac.rs.xml.services.states;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.repository.xml.ExamXMLRepository;

@Service
public class SchedulingExam extends State {

    @Autowired
    private SchedulingExam schedulingExam;

    @Autowired
    private ExamXMLRepository examXMLRepository;

    @Override
    public String processRequest(com.uns.ac.rs.xml.util.actions.Action action) {
        return schedulingExam.createExam(action);
    }

    public String createExam(com.uns.ac.rs.xml.util.actions.Action action) {
        return examXMLRepository.save(action);
    }
}
