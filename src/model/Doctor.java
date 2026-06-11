package model;

public class Doctor {

    private String doctorId;
    private String doctorName;
    private boolean available;

    // Constructor
    public Doctor(String doctorId, String doctorName) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.available = true; // Mac dinh khi tao la dang ranh
    }

    // Getters
    public String getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", available=" + available +
                '}';
    }
}
