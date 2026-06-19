package model;

import datastructure.dll.IMedicalHistory;
import datastructure.dll.MedicalHistoryList;

/**
 * Lớp đại diện cho một bệnh nhân trong hệ thống.
 */
public class Patient {

    private String patientId;
    private String name;
    private int age;

    /** Lịch sử bệnh án của bệnh nhân */
    private IMedicalHistory medicalHistory; 

    /**
     * Khởi tạo một đối tượng bệnh nhân mới.
     *
     * @param patientId Mã số của bệnh nhân
     * @param name Tên của bệnh nhân
     * @param age Tuổi của bệnh nhân
     */
    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.medicalHistory = new MedicalHistoryList(); // Khởi tạo lịch sử bệnh án rỗng
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public IMedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", medicalHistory=" + medicalHistory +
                '}';
    }
}
