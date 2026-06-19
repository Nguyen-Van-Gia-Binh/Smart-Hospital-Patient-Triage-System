package service;

import datastructure.avlTree.IPatientAVLTree;
import datastructure.avlTree.PatientAVLTree;
import model.Patient;
import util.Acceptable;
import util.Inputter;

/**
 * Service xu ly nghiep vu lien quan den benh nhan.
 * Su dung IPatientAVLTree de luu tru va tra cuu.
 */
public class PatientService {

    private IPatientAVLTree patientAVLTree;

    public PatientService() {
        this.patientAVLTree = new PatientAVLTree();
    }

    /**
     * Registers a new patient into the system (with ID input).
     */
    public void registerPatient() {
        String id;
        do {
            id = Inputter.getString("Enter Patient ID (e.g., BNxxx): ", Acceptable.PATIENT_ID_VALID);
            Patient existingPatient = patientAVLTree.search(id);
            if (existingPatient != null) {
                System.out.println("[!] Patient ID already exists. Please try again.");
            }
        } while (patientAVLTree.search(id) != null);

        Patient patient = collectPatientInfo(id);
        patientAVLTree.insert(patient);
        System.out.println("[+] Registered patient in the system (AVL Tree): " + patient.getName()
                + " (ID: " + patient.getPatientId() + ")");
    }

    /**
     * Registers a new patient with a pre-validated ID.
     * Used in Emergency Admission when the ID doesn't exist yet.
     */
    public Patient registerNewPatient(String id) {
        Patient patient = collectPatientInfo(id);
        patientAVLTree.insert(patient);
        System.out.println("[+] Registered patient in the system (AVL Tree): " + patient.getName()
                + " (ID: " + patient.getPatientId() + ")");
        return patient;
    }

    /**
     * Collect patient demographic details from console.
     */
    private Patient collectPatientInfo(String id) {
        String name = Inputter.getString("Enter Full Name: ", Acceptable.FULL_NAME_VALID);
        int age = Integer.parseInt(Inputter.getString("Enter Age: ", "\\d+"));
        Patient patient = new Patient(id, name, age);
        return patient;
    }

    /**
     * Search patient in the system by prompting for ID.
     */
    public Patient findPatient() {
        String patientId = Inputter.getString("Enter Patient ID: ", Acceptable.PATIENT_ID_VALID);
        Patient found = patientAVLTree.search(patientId);
        if (found == null) {
            return null;
        }
        return found;
    }

    /**
     * Search patient by pre-validated ID.
     */
    public Patient findPatientById(String patientId) {
        return patientAVLTree.search(patientId);
    }

    /**
     * Delete patient from system.
     */
    public boolean removePatient(String patientId) {
        boolean result = patientAVLTree.delete(patientId);
        if (result) {
            System.out.println("[x] Deleted Patient ID: " + patientId);
        } else {
            System.out.println("[!] Patient ID not found for deletion: " + patientId);
        }
        return result;
    }

}
