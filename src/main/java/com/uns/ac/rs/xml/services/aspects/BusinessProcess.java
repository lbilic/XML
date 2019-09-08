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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Aspect
@Configuration
public class BusinessProcess {

    @Autowired
    private ExamStateService service;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Process process;

    @Before("execution(* com.uns.ac.rs.xml.services.states.SchedulingExam.processRequest(..)) && args(action,..)")
    public void beforeCreatingAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        if (!action.getFunction().equals(ActionType.ADD.toString())) {
            throw new com.uns.ac.rs.xml.util.ValidationException("Invalid action passed!");
        }

    }

    @Before("execution(* com.uns.ac.rs.xml.services.states.AcceptAppointment.processRequest(..)) && args(action,..)")
    public void beforeAcceptingAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        if (action.getFunction().equals(ActionType.DELETE.toString())) {
            process.getAcceptAppointment().setOption(Options.DECLINE_EXAM);
        } else if (action.getFunction().equals(ActionType.EDIT.toString()) &&
                action.getContext().equals(Context.EDIT.toString())) {
            process.getAcceptAppointment().setOption(Options.EDIT_EXAM);
        } else if (action.getFunction().equals(ActionType.EDIT.toString()) &&
                action.getContext().equals(Context.ACCEPT.toString())) {
            process.getAcceptAppointment().setOption(Options.ACCEPT_EXAM);
        } else {
            throw new com.uns.ac.rs.xml.util.ValidationException("Invalid action passed!");
        }

    }

    @Before("execution(* com.uns.ac.rs.xml.services.states.ChangedAppointment.processRequest(..)) && args(action,..)")
    public void beforeAcceptingChangedAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        if (action.getFunction().equals(ActionType.DELETE.toString())) {
            process.getChangedAppointment().setOption(Options.DECLINE_EXAM);
        } else if (action.getFunction().equals(ActionType.EDIT.toString()) &&
                action.getContext().equals(Context.ACCEPT.toString())) {
            process.getChangedAppointment().setOption(Options.ACCEPT_EXAM);
        } else {
            throw new com.uns.ac.rs.xml.util.ValidationException("Invalid action passed!");
        }
    }

	
    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.SchedulingExam.createExam(..)) && args(action,..)")
    public void afterCreatingExam(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromExam(action);
        if (process.getProcesses().containsKey(patient)) {
            service.editProcess(States.WAITING.toString(), patient);
        } else {
            service.addNewProcess(action);
        }
        process.getProcesses().put(patient, process.getAcceptAppointment());
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.AcceptAppointment.editAppointment(..)) && args(action,..)")
    public void afterChangingAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromExam(action);
        service.editProcess(States.EXAM_CHANGED.toString(), patient);
        process.getProcesses().put(patient, process.getChangedAppointment());
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.AcceptAppointment.declineAppointment(..)) && args(action,..)")
    public void afterDecliningAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromExam(action);
        service.editProcess(States.END.toString(), patient);
        process.getProcesses().remove(patient);
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.AcceptAppointment.processAppointment(..)) && args(action,..)")
    public void afterAcceptingAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String doctorType = mapper.getDoctorTypeFromExam(action);
        String patient = mapper.getPatientFromExam(action);
        if (doctorType.equals(DoctorType.GENERAL_PRACTICE.toString())) {
            service.editProcess(States.GENERALIST_EXAM.toString(), patient);
            process.getProcesses().put(patient, process.getGeneralistExam());
        } else {
            service.editProcess(States.SPECIALIST_EXAM.toString(), patient);
            process.getProcesses().put(patient, process.getSpecialistExam());
        }
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.ChangedAppointment.declineAppointment(..)) && args(action,..)")
    public void afterDecliningChangedAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromExam(action);
        service.editProcess(States.END.toString(), patient);
        process.getProcesses().remove(patient);
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.ChangedAppointment.processAppointment(..)) && args(action,..)")
    public void afterAcceptingChangedAppointment(com.uns.ac.rs.xml.util.actions.Action action) {
        String doctorType = mapper.getDoctorTypeFromExam(action);
        String patient = mapper.getPatientFromExam(action);
        if (doctorType.equals(DoctorType.GENERAL_PRACTICE.toString())) {
            service.editProcess(States.GENERALIST_EXAM.toString(), patient);
            process.getProcesses().put(patient, process.getGeneralistExam());
        } else {
            service.editProcess(States.SPECIALIST_EXAM.toString(), patient);
            process.getProcesses().put(patient, process.getSpecialistExam());
        }
    }

    @AfterReturning(pointcut = "execution(* com.uns.ac.rs.xml.services.states.SpecialistExam.createReport(..)) && args(action,..)")
    public void afterSpecialistExam(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientfromReport(action);
        service.editProcess(States.SCHEDULING.toString(), patient);
        process.getProcesses().put(patient, process.getSchedulingExam());
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
            service.editProcess(States.SCHEDULING.toString(),
                    mapper.getPatientFromDocumentation(action));
            process.getProcesses().put(mapper.getPatientFromDocumentation(action), process.getSchedulingExam());
        } else {
            service.editProcess(States.END.toString(),
                    mapper.getPatientFromDocumentation(action));
            process.getProcesses().remove(mapper.getPatientFromDocumentation(action));
        }
    }

    @Before("execution(* com.uns.ac.rs.xml.services.nonProcessService.ChoiceService.save(..)) && args(action,..)")
    public void beforeChangingDoctor(com.uns.ac.rs.xml.util.actions.Action action) {
        String patient = mapper.getPatientFromChoice(action);
        if (process.getProcesses().containsKey(patient)) {
            throw new com.uns.ac.rs.xml.util.ValidationException("Not possible to changed doctors mid exam.");
        }
    }

    String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
