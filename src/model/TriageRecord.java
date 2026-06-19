package model;

public class TriageRecord implements Comparable<TriageRecord> {
    private Patient patient;
    private int severityScore;
    private long arrivalOrder;
    private static long sequenceCounter = 0;

    public TriageRecord(Patient patient, int severityScore) {
        this.patient = patient;
        this.severityScore = severityScore;
        this.arrivalOrder = ++sequenceCounter;
    }

    // Getters / Setters
    public Patient getPatient() {
        return patient;
    }

    public int getSeverityScore() {
        return severityScore;
    }

    public long getArrivalOrder() {
        return arrivalOrder;
    }

    @Override
    public int compareTo(TriageRecord other) {
        if (this.severityScore != other.severityScore) {
            return Integer.compare(this.severityScore, other.severityScore); // Ưu tiên score cao hơn
        }
        // Nếu cùng score, ai đến trước (arrivalOrder nhỏ hơn) có độ ưu tiên cao hơn
        return Long.compare(other.arrivalOrder, this.arrivalOrder);
    }
}
