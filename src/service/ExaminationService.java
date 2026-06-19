package service;

import model.Patient;
import model.MedicalRecord;

/**
 * Service xu ly nghiep vu kham benh va luu ho so benh an.
 * Su dung IMedicalHistory tuong ung cua tung benh nhan.
 */
public class ExaminationService {

    public ExaminationService() {
        // Constructor khong can khoi tao list chung
    }

    /**
     * Them benh an moi sau khi kham cho mot benh nhan.
     */
    public void addMedicalRecord(Patient patient, String diagnosis, String prescription, String note) {
        if (patient == null) {
            System.out.println("[!] Khong co benh nhan de ghi nhan ket qua.");
            return;
        }
        MedicalRecord record = new MedicalRecord(diagnosis, prescription, note);
        patient.getMedicalHistory().addRecord(record);
        System.out.println("[+] Da them benh an cho benh nhan " + patient.getName() + ": " + diagnosis);
    }

    /**
     * Hien thi n benh an gan nhat cua mot benh nhan.
     */
    public void showRecentRecords(Patient patient, int n) {
        if (patient == null) {
            System.out.println("[!] Benh nhan khong ton tai.");
            return;
        }
        System.out.println("\n--- " + n + " BENH AN GAN NHAT CUA: " + patient.getName() + " (" + patient.getPatientId() + ") ---");
        patient.getMedicalHistory().displayRecent(n);
    }

}
