package model;

public class Patient {

    private String patientId;
    private String name;
    private int age;
    private int severityScore; // Diem muc do nghiem trong (1-5, 5 la nang nhat)

    
    // Constructor
    public Patient(String patientId, String name, int age, int severityScore) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.severityScore = severityScore;
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

    public int getSeverityScore() {
        return severityScore;
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

    public void setSeverityScore(int severityScore) {
        this.severityScore = severityScore;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", severityScore=" + severityScore +
                '}';
    }
}
