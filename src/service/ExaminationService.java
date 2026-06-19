package service;

import model.Patient;
import model.MedicalRecord;

/**
 * Lớp dịch vụ (Service) xử lý các nghiệp vụ liên quan đến khám bệnh và hồ sơ bệnh án.
 * Tương tác trực tiếp với danh sách IMedicalHistory của từng bệnh nhân cá nhân.
 */
public class ExaminationService {

    /**
     * Khởi tạo dịch vụ khám bệnh.
     */
    public ExaminationService() {
        // Constructor
    }

    /**
     * Thêm một hồ sơ khám bệnh mới cho bệnh nhân.
     * 
     * @param patient Bệnh nhân được khám
     * @param diagnosis Chẩn đoán bệnh
     * @param prescription Đơn thuốc được kê
     * @param note Ghi chú của bác sĩ
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
     * Hiển thị n hồ sơ khám bệnh gần nhất của một bệnh nhân.
     * 
     * @param patient Bệnh nhân cần xem hồ sơ
     * @param n Số lượng hồ sơ cần hiển thị
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
