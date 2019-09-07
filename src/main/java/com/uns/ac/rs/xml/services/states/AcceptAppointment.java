package com.uns.ac.rs.xml.services.states;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.domain.enums.Options;
import com.uns.ac.rs.xml.repository.xml.UserXMLRepository;
import com.uns.ac.rs.xml.repository.xml.ExamXMLRepository;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.Mapper;

@Service
public class AcceptAppointment extends State {

    @Autowired
    private AcceptAppointment acceptAppointment;

    @Autowired
    private ExamXMLRepository examXMLRepository;

    @Autowired
    private UserXMLRepository userXMLRepository;

    @Autowired
    private Operations operations;

    @Autowired
    private Mapper mapper;

    @Override
    public String processRequest(com.uns.ac.rs.xml.util.actions.Action action) {
        if (this.getOptions() == Options.EDIT_EXAM) {
            return acceptAppointment.editAppointment(action);
        } else if (this.getOptions() == Options.DECLINE_EXAM) {
            return acceptAppointment.processAppointment(action, "Appointment declined.");
        } else {
            return acceptAppointment.processAppointment(action, "Appointment accepted.");
        }
    }

    public String editAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        examXMLRepository.edit(action);
        this.processAppointment(action, "Change appointment.");
        return "Successfully changed appointment!";
    }
	
    public String processAppointment(com.uns.ac.rs.xml.util.actions.Action action, String message) {
        String patient = operations.findById(mapper.getPatientFromExam(action),
                "charts", "findUserByChart");
        userXMLRepository.addNotification(patient, message);
        return message;
    }
}
