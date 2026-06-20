package model;

/**
 * Lớp đại diện cho một hồ sơ bệnh án hoặc một lần khám bệnh của bệnh nhân.
 */
public class MedicalRecord {

    private String diagnosis;
    private String prescription;
    private String note;

    /**
     * Khởi tạo một hồ sơ khám bệnh mới.
     *
     * @param diagnosis Chẩn đoán bệnh
     * @param prescription Đơn thuốc được kê
     * @param note Ghi chú thêm từ bác sĩ
     */
    public MedicalRecord(String diagnosis, String prescription, String note) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.note = note;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public String getNote() {
        return note;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "diagnosis='" + diagnosis + '\'' +
                ", prescription='" + prescription + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
