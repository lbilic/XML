package com.uns.ac.rs.xml.states;

import com.uns.ac.rs.xml.domain.enums.States;
import com.uns.ac.rs.xml.services.service.ExamStateService;
import com.uns.ac.rs.xml.util.database.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Process {

    @Autowired
    private ExamStateService service;

    @Autowired
    private SchedulingExam schedulingExam;

    @Autowired
    private AcceptAppointment acceptAppointment;

    @Autowired
    private ChangedAppointment changedAppointment;

    @Autowired
    private GeneralistExam generalistExam;

    @Autowired
    private SpecialistExam specialistExam;

    @Autowired
    private Mapper mapper;

    private Map<String, State> processes;

    public Process() {
        this.processes = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        this.initialization();
    }

    private void initialization() {
        List<String> states = service.getProcesses();
        for (String state : states) {
            String[] tokens = state.split(",");
            if (tokens[1].equals(States.GENERALIST_EXAM.toString())) {
                processes.put(tokens[0], generalistExam);
            } else if (tokens[1].equals(States.SPECIALIST_EXAM.toString())) {
                processes.put(tokens[0], specialistExam);
            } else if (tokens[1].equals(States.SCHEDULING.toString())) {
                processes.put(tokens[0], schedulingExam);
            } else if (tokens[1].equals(States.WAITING.toString())) {
                processes.put(tokens[0], acceptAppointment);
            } else if (tokens[1].equals(States.EXAM_CHANGED.toString())) {
                processes.put(tokens[0], changedAppointment);
            }
        }
    }

    public String processRequest(com.uns.ac.rs.xml.util.actions.Action action) {
        try {
            return this.processes.getOrDefault(mapper.getPatientFromExam(action), schedulingExam)
                    .processRequest(action);
        } catch (NullPointerException e) {
            try {
                return this.processes.getOrDefault(mapper.getPatientFromReport(action), schedulingExam)
                        .processRequest(action);
            } catch (NullPointerException ex) {
                return this.processes.getOrDefault(mapper.getPatientFromDocumentation(action), schedulingExam)
                        .processRequest(action);
            }
        }
    }

    public Map<String, State> getProcesses() {
        return processes;
    }

    public void setProcesses(Map<String, State> processes) {
        this.processes = processes;
    }

    public com.uns.ac.rs.xml.states.SchedulingExam getSchedulingExam() {
        return schedulingExam;
    }

    public com.uns.ac.rs.xml.states.AcceptAppointment getAcceptAppointment() {
        return acceptAppointment;
    }

    public com.uns.ac.rs.xml.states.ChangedAppointment getChangedAppointment() {
        return changedAppointment;
    }

    public com.uns.ac.rs.xml.states.GeneralistExam getGeneralistExam() {
        return generalistExam;
    }

    public com.uns.ac.rs.xml.states.SpecialistExam getSpecialistExam() {
        return specialistExam;
    }
}
