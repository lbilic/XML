package com.uns.ac.rs.xml.services.aspects;

import com.uns.ac.rs.xml.services.nonProcessService.ExamStateService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import com.uns.ac.rs.xml.domain.enums.*;
import com.uns.ac.rs.xml.services.states.Process;
import com.uns.ac.rs.xml.util.Mapper;

@Aspect
@Configuration
public class BusinessProcess extends com.uns.ac.rs.xml.util.IOStreamer {

    @Autowired
    private ExamStateService service;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Process proces;

    @Before("execution(* com.uns.ac.rs.xml.services.states.SchedulingExam.processRequest(..)) && args(action,..)")
    public void beforeCreatingAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        if (!action.getFunction().equals(ActionType.ADD.toString())) {
            throw new com.uns.ac.rs.xml.util.ValidationException("Invalid action passed!");
        }

    }

    @Before("execution(* com.uns.ac.rs.xml.services.states.AcceptAppointment.processRequest(..)) && args(action,..)")
    public void beforeAcceptingAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        if (action.getFunction().equals(ActionType.DELETE.toString())) {
            proces.getAcceptAppointment().setOption(Options.DECLINE_EXAM);
        } else if (action.getFunction().equals(ActionType.EDIT.toString()) &&
                action.getContext().equals(Context.EDIT.toString())) {
            proces.getAcceptAppointment().setOption(Options.EDIT_EXAM);
        } else if (action.getFunction().equals(ActionType.EDIT.toString()) &&
                action.getContext().equals(Context.ACCEPT.toString())) {
            proces.getAcceptAppointment().setOption(Options.ACCEPT_EXAM);
        } else {
            throw new com.uns.ac.rs.xml.util.ValidationException("Invalid action passed!");
        }

    }

    @Before("execution(* com.uns.ac.rs.xml.services.states.ChangedAppointment.processRequest(..)) && args(action,..)")
    public void beforeAcceptingChangedAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        if (action.getFunction().equals(ActionType.DELETE.toString())) {
            proces.getChangedAppointment().setOption(Options.DECLINE_EXAM);
        } else if (action.getFunction().equals(ActionType.EDIT.toString()) &&
                action.getContext().equals(Context.ACCEPT.toString())) {
            proces.getChangedAppointment().setOption(Options.ACCEPT_EXAM);
        } else {
            throw new com.uns.ac.rs.xml.util.ValidationException("Invalid action passed!");
        }
    }

	
    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.SchedulingExam.createExam(..)) && args(action,..)")
    public void afterCreatingExam(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromExam(action);
        if (proces.getProcesses().containsKey(patient)) {
            service.editProces(States.WAITING.toString(), patient);
        } else {
            service.addNewProcess(action);
        }
        proces.getProcesses().put(patient, proces.getAcceptAppointment());
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.AcceptAppointment.editAppointment(..)) && args(action,..)")
    public void afterChangingAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromExam(action);
        service.editProces(States.EXAM_CHANGED.toString(), patient);
        proces.getProcesses().put(patient, proces.getChangedAppointment());
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.AcceptAppointment.declineAppointment(..)) && args(action,..)")
    public void afterDecliningAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromExam(action);
        service.editProces(States.END.toString(), patient);
        proces.getProcesses().remove(patient);
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.AcceptAppointment.processAppointment(..)) && args(action,..)")
    public void afterAcceptingAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String doctorType = mapper.getDoctorTypeFromExam(action);
        String patient = mapper.getPatientFromExam(action);
        if (doctorType.equals(DoctorType.GENERAL_PRACTICE.toString())) {
            service.editProces(States.GENERALIST_EXAM.toString(), patient);
            proces.getProcesses().put(patient, proces.getGeneralistExam());
        } else {
            service.editProces(States.SPECIALIST_EXAM.toString(), patient);
            proces.getProcesses().put(patient, proces.getSpecialistExam());
        }
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.ChangedAppointment.declineAppointment(..)) && args(action,..)")
    public void afterDecliningChangedAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromExam(action);
        service.editProces(States.END.toString(), patient);
        proces.getProcesses().remove(patient);
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.ChangedAppointment.processAppointment(..)) && args(action,..)")
    public void afterAcceptingChangedAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String doctorType = mapper.getDoctorTypeFromExam(action);
        String patient = mapper.getPatientFromExam(action);
        if (doctorType.equals(DoctorType.GENERAL_PRACTICE.toString())) {
            service.editProces(States.GENERALIST_EXAM.toString(), patient);
            proces.getProcesses().put(patient, proces.getGeneralistExam());
        } else {
            service.editProces(States.SPECIALIST_EXAM.toString(), patient);
            proces.getProcesses().put(patient, proces.getSpecialistExam());
        }
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.SpecialistExam.createReport(..)) && args(action,..)")
    public void afterSpecialistExam(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientfromReport(action);
        service.editProces(States.SCHEDULING.toString(), patient);
        proces.getProcesses().put(patient, proces.getSchedulingExam());
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.GeneralistExam.createDocumentation(..)) && args(action,..)")
    public void afterGeneralExam(com.uns.ac.rs.xml.util.actions.Action action) {
        Document doc = mapper.convertToDocument(action);
        boolean report = false, referral = false;
        NodeList list = doc.getFirstChild().getLastChild().getFirstChild().getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            try {
                switch (list.item(i).getLocalName()) {
                    case "report":
                        report = true;
                        break;
                    case "referral":
                        referral = true;
                        break;
                }
            } catch (NullPointerException e) {
                break;
            }
        }

        if (report && referral) {
            service.editProces(States.SCHEDULING.toString(),
                    mapper.getPatientFromDocumentation(action));
            proces.getProcesses().put(mapper.getPatientFromDocumentation(action), proces.getSchedulingExam());
        } else {
            service.editProces(States.END.toString(),
                    mapper.getPatientFromDocumentation(action));
            proces.getProcesses().remove(mapper.getPatientFromDocumentation(action));
        }
    }

    @Before("execution(* com.uns.ac.rs.xml.services.nonProcessService.ChoiceService.save(..)) && args(action,..)")
    public void beforeChangingDoctor(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromChoice(action);
        if (proces.getProcesses().containsKey(patient)) {
            throw new com.uns.ac.rs.xml.util.ValidationException("Not possible to changed doctors mid exam.");
        }
    }
}
