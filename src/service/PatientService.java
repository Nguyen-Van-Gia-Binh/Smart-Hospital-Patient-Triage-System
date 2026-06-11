package service;

import datastructure.bst.IPatientBST;
import datastructure.bst.PatientBST;
import model.Patient;

/**
 * Service xu ly nghiep vu lien quan den benh nhan.
 * Su dung IPatientBST de luu tru va tra cuu.
 */
public class PatientService {

    private IPatientBST patientBST;

    public PatientService() {
        this.patientBST = new PatientBST();
    }

    /**
     * Dang ky benh nhan moi vao he thong.
     */
    public void registerPatient(Patient patient) {
        patientBST.insert(patient);
        System.out.println("[+] Da dang ky benh nhan: " + patient.getName()
                + " (ID: " + patient.getPatientId() + ")");
    }

    /**
     * Tim kiem benh nhan theo ma benh nhan.
     */
    public Patient findPatient(String patientId) {
        Patient found = patientBST.search(patientId);
        if (found == null) {
            System.out.println("[!] Khong tim thay benh nhan voi ID: " + patientId);
        }
        return found;
    }

    /**
     * Xoa benh nhan khoi he thong.
     */
    public boolean removePatient(String patientId) {
        boolean result = patientBST.delete(patientId);
        if (result) {
            System.out.println("[x] Da xoa benh nhan ID: " + patientId);
        } else {
            System.out.println("[!] Khong tim thay benh nhan de xoa, ID: " + patientId);
        }
        return result;
    }

}
