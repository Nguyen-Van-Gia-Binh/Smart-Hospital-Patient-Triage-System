package model;

public class MedicalRecord {

    private String diagnosis;
    private String prescription;
    private String note;

    // Constructor
    public MedicalRecord(String diagnosis, String prescription, String note) {
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.note = note;
    }

    // Getters
    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public String getNote() {
        return note;
    }

    // Setters
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
