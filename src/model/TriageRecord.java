package model;

/**
 * Lớp đại diện cho một hồ sơ phân loại bệnh nhân (Triage), dùng để sắp xếp thứ tự ưu tiên khám bệnh.
 */
public class TriageRecord implements Comparable<TriageRecord> {
    private Patient patient;
    private int severityScore;
    private long arrivalOrder;
    
    /** Biến đếm dùng để cấp số thứ tự đến khám tự động */
    private static long sequenceCounter = 0;

    /**
     * Khởi tạo hồ sơ phân loại cho bệnh nhân dựa trên mức độ nghiêm trọng.
     *
     * @param patient Đối tượng bệnh nhân
     * @param severityScore Điểm mức độ nghiêm trọng (càng cao càng nguy hiểm)
     */
    public TriageRecord(Patient patient, int severityScore) {
        this.patient = patient;
        this.severityScore = severityScore;
        this.arrivalOrder = ++sequenceCounter;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getSeverityScore() {
        return severityScore;
    }

    public long getArrivalOrder() {
        return arrivalOrder;
    }

    /**
     * So sánh hai hồ sơ phân loại để xác định thứ tự ưu tiên.
     *
     * @param other Hồ sơ phân loại khác để so sánh
     * @return Giá trị dương nếu hồ sơ hiện tại có độ ưu tiên cao hơn, ngược lại trả về âm hoặc 0
     */
    @Override
    public int compareTo(TriageRecord other) {
        if (this.severityScore != other.severityScore) {
            return Integer.compare(this.severityScore, other.severityScore); // Ưu tiên điểm mức độ nghiêm trọng cao hơn
        }
        // Nếu cùng điểm, ai đến trước (arrivalOrder nhỏ hơn) có độ ưu tiên cao hơn
        return Long.compare(other.arrivalOrder, this.arrivalOrder);
    }
}
