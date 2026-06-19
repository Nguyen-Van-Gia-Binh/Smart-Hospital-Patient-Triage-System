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
     * Dang ky benh nhan moi vao he thong (tu nhap ID moi).
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

        Patient patient = collectPatientInfo(id);
        patientAVLTree.insert(patient);
        System.out.println("[+] Da dang ky benh nhan vao he thong (AVL Tree): " + patient.getName()
                + " (ID: " + patient.getPatientId() + ")");
    }

    /**
     * Dang ky benh nhan moi vao he thong voi ID da biet truoc (ID chua ton tai).
     * Dung trong luong Emergency Admission khi ID chua co trong he thong.
     *
     * @param id Ma benh nhan da duoc nhap va xac nhan la chua ton tai
     * @return doi tuong Patient vua duoc tao va luu vao he thong
     */
    public Patient registerNewPatient(String id) {
        Patient patient = collectPatientInfo(id);
        patientAVLTree.insert(patient);
        System.out.println("[+] Da dang ky benh nhan vao he thong (AVL Tree): " + patient.getName()
                + " (ID: " + patient.getPatientId() + ")");
        return patient;
    }

    /**
     * Thu thap thong tin benh nhan (ho ten, tuoi, muc do) tu console.
     *
     * @param id Ma benh nhan da xac nhan
     * @return doi tuong Patient voi thong tin day du
     */
    private Patient collectPatientInfo(String id) {
        String name = Inputter.getString("Nhap ho ten: ", Acceptable.FULL_NAME_VALID);
        int age = Integer.parseInt(Inputter.getString("Nhap tuoi: ", "\\d+"));
        Patient patient = new Patient(id, name, age);
        return patient;
    }

    /**
     * Tim kiem benh nhan theo ma benh nhan.
     * Yeu cau nguoi dung nhap ID va tra ve doi tuong Patient neu tim thay.
     *
     * @return doi tuong Patient neu tim thay, null neu khong
     */
    public Patient findPatient() {
        String patientId = Inputter.getString("Nhap Ma benh nhan: ", Acceptable.PATIENT_ID_VALID);
        Patient found = patientAVLTree.search(patientId);
        if (found == null) {
            return null;
        }
        return found;
    }

    /**
     * Tim kiem benh nhan theo ma benh nhan da biet truoc (khong yeu cau nhap lai).
     *
     * @param patientId ma benh nhan can tim
     * @return doi tuong Patient neu tim thay, null neu khong
     */
    public Patient findPatientById(String patientId) {
        return patientAVLTree.search(patientId);
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
