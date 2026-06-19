package service;

import model.Patient;
import model.MedicalRecord;

/**
 * Service handling patient examinations and medical records.
 * Uses the individual patient's IMedicalHistory list.
 */
public class ExaminationService {

    public ExaminationService() {
        // Constructor
    }

    /**
     * Add a new medical record for a patient.
     */
    public void addMedicalRecord(Patient patient, String diagnosis, String prescription, String note) {
        if (patient == null) {
            System.out.println("[!] No patient to record results.");
            return;
        }
        MedicalRecord record = new MedicalRecord(diagnosis, prescription, note);
        patient.getMedicalHistory().addRecord(record);
        System.out.println("[+] Added medical record for patient " + patient.getName() + ": " + diagnosis);
    }

    /**
     * Display the n most recent medical records of a patient.
     */
    public void showRecentRecords(Patient patient, int n) {
        if (patient == null) {
            System.out.println("[!] Patient does not exist.");
            return;
        }
        System.out.println("\n--- " + n + " RECENT MEDICAL RECORD(S) OF: " + patient.getName() + " (" + patient.getPatientId() + ") ---");
        patient.getMedicalHistory().displayRecent(n);
    }

}
