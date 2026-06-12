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
     * Dang ky benh nhan moi vao he thong.
     */
    public void registerPatient(Patient patient) {
        String patientId;
        do {
            patientId = Inputter.getString("Nhap id benh nhan: ", Acceptable.PATIENT_ID_VALID);
            if (patientAVLTree.search(patientId) != null) {
                System.out.println("[!] ID da ton tai, vui long nhap ID khac.");
            }
        } while (patientAVLTree.search(patientId) != null);

        patient.setPatientId(patientId);
        patientAVLTree.insert(patient);
        System.out.println("[+] Da dang ky benh nhan: " + patient.getName()
                + " (ID: " + patient.getPatientId() + ")");
    }

    /**
     * Tim kiem benh nhan theo ma benh nhan.
     */
    public Patient findPatient(String patientId) {
        Patient found = patientAVLTree.search(patientId);
        if (found == null) {
            System.out.println("[!] Khong tim thay benh nhan voi ID: " + patientId);
        }
        return found;
    }

    /**
     * Xoa benh nhan khoi he thong.
     */
    public boolean removePatient(String patientId) {
        boolean result = patientAVLTree.delete(patientId);
        if (result) {
            System.out.println("[x] Da xoa benh nhan ID: " + patientId);
        } else {
            System.out.println("[!] Khong tim thay benh nhan de xoa, ID: " + patientId);
        }
        return result;
    }

}
