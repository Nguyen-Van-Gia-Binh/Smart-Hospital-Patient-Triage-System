package service;

import datastructure.dll.IMedicalHistory;
import datastructure.dll.MedicalHistoryList;
import model.MedicalRecord;

/**
 * Service xu ly nghiep vu kham benh va luu ho so benh an.
 * Su dung IMedicalHistory de luu tru lich su kham.
 */
public class ExaminationService {

    private IMedicalHistory medicalHistory;

    public ExaminationService() {
        this.medicalHistory = new MedicalHistoryList();
    }

    /**
     * Them benh an moi sau khi kham.
     */
    public void addMedicalRecord(String diagnosis, String prescription, String note) {
        MedicalRecord record = new MedicalRecord(diagnosis, prescription, note);
        medicalHistory.addRecord(record);
        System.out.println("[+] Da them benh an: " + diagnosis);
    }

    /**
     * Hien thi n benh an gan nhat.
     */
    public void showRecentRecords(int n) {
        System.out.println("\n--- " + n + " BENH AN GAN NHAT ---");
        medicalHistory.displayRecent(n);
    }

}
