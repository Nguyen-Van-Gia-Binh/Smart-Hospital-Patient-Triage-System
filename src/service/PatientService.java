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
    public void registerPatient() {

        String id;
        do {
            id = Inputter.getString("Nhap Ma benh nhan (VD: BNxxx): ", Acceptable.PATIENT_ID_VALID);
            Patient existingPatient = patientAVLTree.search(id);
            if (existingPatient != null) {
                System.out.println("[!] Ma benh nhan da ton tai. Vui long nhap lai.");
            }
        } while (patientAVLTree.search(id) != null);
        String name = Inputter.getString("Nhap ho ten: ", Acceptable.FULL_NAME_VALID);
        int age = Integer.parseInt(Inputter.getString("Nhap tuoi: ", "\\d+"));
        int severity = Integer.parseInt(Inputter.getString("Nhap Diem muc do nghiem trong (1-5): ", "[1-5]"));
        Patient patient = new Patient(id, name, age, severity);
        patient.setPatientId(id);
        patientAVLTree.insert(patient);
        System.out.println("[+] Da dang ky benh nhan vao he thong (AVL Tree): " + patient.getName()
                + " (ID: " + patient.getPatientId() + ")");
    }

    /**
     * Tim kiem benh nhan theo ma benh nhan.
     */
    public Patient findPatient() {
        System.out.print("Nhap Ma benh nhan: ");
        String patientId = Inputter.getString("Nhap ma benh nhan: ", Acceptable.PATIENT_ID_VALID);
        Patient found = patientAVLTree.search(patientId);
        if (found == null) {
            return null;
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
