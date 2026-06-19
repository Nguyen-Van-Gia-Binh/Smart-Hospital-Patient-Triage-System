package model;

import datastructure.dll.IMedicalHistory;
import datastructure.dll.MedicalHistoryList;

public class Patient {

    private String patientId;
    private String name;
    private int age;

    private IMedicalHistory medicalHistory; // Lich su benh an cua benh nhan
    // Constructor

    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.medicalHistory = new MedicalHistoryList(); // Khoi tao lich su benh an rong
    }

    // Getters
    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setters
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
