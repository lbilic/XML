package com.uns.ac.rs.xml.domain;

import com.uns.ac.rs.xml.domain.documents.*;
import com.uns.ac.rs.xml.domain.documents.collections.*;
import com.uns.ac.rs.xml.domain.entity.*;
import com.uns.ac.rs.xml.domain.entity.collections.*;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.example.myschema package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.myschema
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Choice }
     */
    public Choice createChoice() {
        return new Choice();
    }

    /**
     * Create an instance of {@link Report }
     */
    public Report createReport() {
        return new Report();
    }

    /**
     * Create an instance of {@link Doctor }
     */
    public Doctor createDoctor() {
        return new Doctor();
    }

    /**
     * Create an instance of {@link Prescription }
     */
    public Prescription createPrescription() {
        return new Prescription();
    }

    /**
     * Create an instance of {@link Nurse }
     */
    public Nurse createNurse() {
        return new Nurse();
    }

    /**
     * Create an instance of {@link Patient }
     */
    public Patient createPatient() { return new Patient(); }

    /**
     * Create an instance of {@link Exam }
     */
    public Exam createExam() { return new Exam(); }

    /**
     * Create an instance of {@link LabReferral }
     */
    public LabReferral createLabReferral() {
        return new LabReferral();
    }

    /**
     * Create an instance of {@link SpecialistReferral }
     */
    public SpecialistReferral createSpecialistReferral() {
        return new SpecialistReferral();
    }

    /**
     * Create an instance of {@link Chart }
     */
    public Chart createChart() {
        return new Chart();
    }

    /**
     * Create an instance of {@link Chart.Choices }
     */
    public Chart.Choices createChartChoices() {
        return new Chart.Choices();
    }

    /**
     * Create an instance of {@link Chart.Reports }
     */
    public Chart.Reports createChartReports() {
        return new Chart.Reports();
    }

    /**
     * Create an instance of {@link Chart.Prescriptions }
     */
    public Chart.Prescriptions createChartPrescriptions() {
        return new Chart.Prescriptions();
    }

    /**
     * Create an instance of {@link Chart.SpecialistReferrals }
     */
    public Chart.SpecialistReferrals createChartSpecialistReferral() {
        return new Chart.SpecialistReferrals();
    }

    /**
     * Create an instance of {@link Chart.LabReferrals }
     */
    public Chart.LabReferrals createChartLabReferral() {
        return new Chart.LabReferrals();
    }

    /**
     * Create an instance of {@link Chart.Address }
     */
    public Chart.Address createChartAddress() {
        return new Chart.Address();
    }

    /**
     * Create an instance of {@link SpecialistReferral.SpecialistReport }
     */
    public SpecialistReferral.SpecialistReport createSpecialistReferralSpecialistReport() {
        return new SpecialistReferral.SpecialistReport();
    }

    /**
     * Create an instance of {@link SpecialistReferral.Exam }
     */
    public SpecialistReferral.Exam createSpecialistReferralExam() {
        return new SpecialistReferral.Exam();
    }

    /**
     * Create an instance of {@link Choice.PreviousDoctor }
     */
    public Choice.PreviousDoctor createChoicePreviousDoctor() {
        return new Choice.PreviousDoctor();
    }

    /**
     * Create an instance of {@link Choice.Doctor }
     */
    public Choice.Doctor createChoiceDoctor() {
        return new Choice.Doctor();
    }

    /**
     * Create an instance of {@link Choice.Patient }
     */
    public Choice.Patient createChoicePatient() {
        return new Choice.Patient();
    }

    /**
     * Create an instance of {@link Choices }
     */
    public Choices createChoices() {
        return new Choices();
    }

    /**
     * Create an instance of {@link Report.Patient }
     */
    public Report.Patient createReportPatient() {
        return new Report.Patient();
    }

    /**
     * Create an instance of {@link Report.Doctor }
     */
    public Report.Doctor createReportDoctor() {
        return new Report.Doctor();
    }

    /**
     * Create an instance of {@link Reports }
     */
    public Reports createReports() {
        return new Reports();
    }

    /**
     * Create an instance of {@link Users }
     */
    public Users createUsers() {
        return new Users();
    }

    /**
     * Create an instance of {@link User }
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Drug }
     */
    public Drug createDrug() {
        return new Drug();
    }

    /**
     * Create an instance of {@link Doctor.User }
     */
    public Doctor.User createDoctorUser() {
        return new Doctor.User();
    }

    /**
     * Create an instance of {@link Doctors }
     */
    public Doctors createDoctors() {
        return new Doctors();
    }

    /**
     * Create an instance of {@link Prescription.InsuredPerson }
     */
    public Prescription.InsuredPerson createPrescriptionInsuredPerson() {
        return new Prescription.InsuredPerson();
    }

    /**
     * Create an instance of {@link Prescription.PrescribedDrug }
     */
    public Prescription.PrescribedDrug createPrescriptionPrescribedDrug() {
        return new Prescription.PrescribedDrug();
    }

    /**
     * Create an instance of {@link Prescription.IssuedDrug }
     */
    public Prescription.IssuedDrug createPrescriptionIssuedDrug() {
        return new Prescription.IssuedDrug();
    }

    /**
     * Create an instance of {@link Prescription.Doctor }
     */
    public Prescription.Doctor createPrescriptionDoctor() {
        return new Prescription.Doctor();
    }

    /**
     * Create an instance of {@link Prescriptions }
     */
    public Prescriptions createPrescriptions() {
        return new Prescriptions();
    }

    /**
     * Create an instance of {@link Drugs }
     */
    public Drugs createDrugs() {
        return new Drugs();
    }

    /**
     * Create an instance of {@link Nurse.User }
     */
    public Nurse.User createNurseUser() {
        return new Nurse.User();
    }

    /**
     * Create an instance of {@link Nurses }
     */
    public Nurses createNurses() {
        return new Nurses();
    }

    /**
     * Create an instance of {@link Patient.User }
     */
    public Patient.User createPatientUser() {
        return new Patient.User();
    }

    /**
     * Create an instance of {@link Patient.Chart }
     */
    public Patient.Chart createPatientChart() {
        return new Patient.Chart();
    }

    /**
     * Create an instance of {@link Patients }
     */
    public Patients createPatients() {
        return new Patients();
    }

    /**
     * Create an instance of {@link Exam.Doctor }
     */
    public Exam.Doctor createExamDoctor() {
        return new Exam.Doctor();
    }

    /**
     * Create an instance of {@link Exams }
     */
    public Exams createExams() {
        return new Exams();
    }

    /**
     * Create an instance of {@link LabReferral.HealthInstitution }
     */
    public LabReferral.HealthInstitution createLabReferralHealthInstitution() {
        return new LabReferral.HealthInstitution();
    }

    /**
     * Create an instance of {@link LabReferral.InsuredPerson }
     */
    public LabReferral.InsuredPerson createLabReferralInsuredPerson() {
        return new LabReferral.InsuredPerson();
    }

    /**
     * Create an instance of {@link LabReferral.Exam }
     */
    public LabReferral.Exam createLabReferralExam() {
        return new LabReferral.Exam();
    }

    /**
     * Create an instance of {@link LabReferral.Doctor }
     */
    public LabReferral.Doctor createLabReferralDoctor() {
        return new LabReferral.Doctor();
    }

    /**
     * Create an instance of {@link SpecialistReferral.HealthInstitution }
     */
    public SpecialistReferral.HealthInstitution createSpecialistReferralHealthInstitution() {
        return new SpecialistReferral.HealthInstitution();
    }

    /**
     * Create an instance of {@link SpecialistReferral.InsuredPerson }
     */
    public SpecialistReferral.InsuredPerson createSpecialistReferralInsuredPerson() {
        return new SpecialistReferral.InsuredPerson();
    }

    /**
     * Create an instance of {@link LabReferrals }
     */
    public LabReferrals createLabReferrals() {
        return new LabReferrals();
    }

    /**
     * Create an instance of {@link SpecialistReferrals }
     */
    public SpecialistReferrals createSpecialistReferrals() {
        return new SpecialistReferrals();
    }

    /**
     * Create an instance of {@link Chart.InsuranceCarrier }
     */
    public Chart.InsuranceCarrier createChartInsuranceCarrier() {
        return new Chart.InsuranceCarrier();
    }

    /**
     * Create an instance of {@link Charts }
     */
    public Charts createCharts() {
        return new Charts();
    }

    /**
     * Create an instance of {@link Chart.Choices.Choice }
     */
    public Chart.Choices.Choice createChartChoicesChoice() {
        return new Chart.Choices.Choice();
    }

    /**
     * Create an instance of {@link Chart.Reports.Report }
     */
    public Chart.Reports.Report createChartReportsReport() {
        return new Chart.Reports.Report();
    }

    /**
     * Create an instance of {@link Chart.Prescriptions.Prescription }
     */
    public Chart.Prescriptions.Prescription createChartPrescriptionsPrescription() {
        return new Chart.Prescriptions.Prescription();
    }

    /**
     * Create an instance of {@link Chart.SpecialistReferrals.SpecialistReferral }
     */
    public Chart.SpecialistReferrals.SpecialistReferral createChartSpecialistReferralsSpecialistReferral() {
        return new Chart.SpecialistReferrals.SpecialistReferral();
    }

    /**
     * Create an instance of {@link Chart.LabReferrals.LabReferral }
     */
    public Chart.LabReferrals.LabReferral createChartLabReferralsLabReferral() {
        return new Chart.LabReferrals.LabReferral();
    }

    /**
     * Create an instance of {@link Chart.Address.Municipality }
     */
    public Chart.Address.Municipality createChartAddressMunicipality() {
        return new Chart.Address.Municipality();
    }

    /**
     * Create an instance of {@link SpecialistReferral.SpecialistReport.Specialist }
     */
    public SpecialistReferral.SpecialistReport.Specialist createSpecialistReferralSpecialistReportSpecialist() {
        return new SpecialistReferral.SpecialistReport.Specialist();
    }

    /**
     * Create an instance of {@link SpecialistReferral.Exam.Doctor }
     */
    public SpecialistReferral.Exam.Doctor createSpecialistReferralExamDoctor() {
        return new SpecialistReferral.Exam.Doctor();
    }

}
