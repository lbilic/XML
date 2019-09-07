package com.uns.ac.rs.xml.services.states;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uns.ac.rs.xml.domain.enums.Options;
import com.uns.ac.rs.xml.repository.xml.UserXMLRepository;
import com.uns.ac.rs.xml.util.CRUD.Operations;
import com.uns.ac.rs.xml.util.Mapper;

@Service
public class ChangedAppointment extends State {

    @Autowired
    private ChangedAppointment changedAppointment;

    @Autowired
    private UserXMLRepository userXMLRepository;

    @Autowired
    private Operations operations;

    @Autowired
    private Mapper mapper;

    @Override
    public String processRequest(com.uns.ac.rs.xml.util.actions.Action action) {
        if (this.getOptions() == Options.DECLINE_EXAM) {
            return changedAppointment.processAppointment(action, "Appointment declined.");
        } else {
            return changedAppointment.processAppointment(action, "Appointment accepted.");
        }
    }


    public String processAppointment(com.uns.ac.rs.xml.util.actions.Action action, String message) {
        String patient = operations.findById(mapper.getPatientFromExam(action),
                "charts", "findUserByChart");
        userXMLRepository.addNotification(patient, message);
        return message;
    }
}
