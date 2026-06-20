package service;

import datastructure.queue.ITriageQueue;
import datastructure.queue.PriorityQueue;
import datastructure.queue.QueueFIFO;
import model.Patient;
import model.TriageRecord;
import java.util.List;

/**
 * Lớp dịch vụ (Service) xử lý các nghiệp vụ phân luồng cấp cứu.
 *
 * Sử dụng ITriageQueue để quản lý hàng đợi bệnh nhân cấp cứu.
 * Bệnh nhân có điểm mức độ nghiêm trọng (severityScore) cao hơn sẽ được xếp lên đầu hàng đợi và được gọi khám trước.
 */
public class TriageService {

    private ITriageQueue triageQueue;
    private QueueFIFO normalQueue;

    /**
     * Khởi tạo dịch vụ phân luồng với hàng đợi ưu tiên mặc định (PriorityQueue) và hàng đợi thường (FIFO).
     */
    public TriageService() {
        this.triageQueue = new PriorityQueue();
        this.normalQueue = new QueueFIFO();
    }

    /**
     * Khởi tạo dịch vụ phân luồng với một cấu trúc hàng đợi cấp cứu tùy chỉnh.
     * 
     * @param customQueue Cấu trúc hàng đợi tùy chỉnh implements ITriageQueue
     */
    public TriageService(ITriageQueue customQueue) {
        this.triageQueue = customQueue;
        this.normalQueue = new QueueFIFO();
    }

    /**
     * Thay đổi cấu trúc hàng đợi cấp cứu hiện tại.
     * 
     * @param queue Cấu trúc hàng đợi mới implements ITriageQueue
     */
    public void setTriageQueue(ITriageQueue queue) {
        this.triageQueue = queue;
    }

    /**
     * Lấy danh sách hàng đợi thường (bệnh nhân không khẩn cấp).
     * 
     * @return Đối tượng QueueFIFO
     */
    public QueueFIFO getNormalQueue() {
        return normalQueue;
    }

    /**
     * Đưa bệnh nhân vào hàng đợi thích hợp dựa trên mức độ nghiêm trọng.
     * Nếu severityScore == 1 (Không khẩn cấp) -> Đưa vào hàng đợi thường (FIFO).
     * Nếu severityScore > 1 -> Đưa vào hàng đợi cấp cứu (Priority Queue / Heap).
     *
     * @param patient Bệnh nhân cần đưa vào hàng đợi
     * @param severityScore Điểm mức độ nghiêm trọng hiện tại của bệnh nhân
     */
    public void addToEmergencyQueue(Patient patient, int severityScore) {
        if (severityScore == 1) {
            normalQueue.enqueue(patient);
            System.out.println("[+] Added patient to the NORMAL queue: " + patient.getName()
                    + " (Severity: 1 - Non-Urgent)");
        } else {
            TriageRecord record = new TriageRecord(patient, severityScore);
            triageQueue.enqueue(record);
            System.out.println("[+] Added patient to the EMERGENCY queue: " + patient.getName()
                    + " (Severity: " + severityScore + ")");
        }
    }

    /**
     * Gọi bệnh nhân cấp cứu có mức độ nguy kịch cao nhất (đang ở đầu hàng đợi) vào khám.
     *
     * @return Bệnh nhân được gọi, trả về null nếu hàng đợi cấp cứu rỗng
     */
    public Patient callNextPatient() {
        if (triageQueue.isEmpty()) {
            System.out.println("[!] Emergency Queue is empty.");
            return null;
        }
        TriageRecord nextRecord = triageQueue.dequeue();
        Patient next = nextRecord.getPatient();
        System.out.println(">> CALLED PATIENT: " + next.getName()
                + " (Severity: " + nextRecord.getSeverityScore() + ")");
        return next;
    }

    /**
     * Kiểm tra xem có bệnh nhân cấp cứu nào đang chờ hay không.
     *
     * @return true nếu có ít nhất 1 bệnh nhân cấp cứu đang chờ
     */
    public boolean hasWaitingPatients() {
        return !triageQueue.isEmpty();
    }

    /**
     * Hiển thị danh sách hàng đợi cấp cứu hiện tại theo thứ tự ưu tiên.
     */
    public void displayQueue() {
        if (triageQueue.isEmpty()) {
            System.out.println("[!] Emergency Queue is empty. No patients waiting.");
            return;
        }
        List<TriageRecord> queue = triageQueue.peekAll();
        System.out.println("\n=========================================");
        System.out.println("   EMERGENCY QUEUE (Priority Order)");
        System.out.println("=========================================");
        System.out.printf("%-5s %-10s %-25s %-6s %-12s%n",
                "No.", "ID", "Full Name", "Age", "Urgency(1-5)");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < queue.size(); i++) {
            TriageRecord r = queue.get(i);
            Patient p = r.getPatient();
            System.out.printf("%-5d %-10s %-25s %-6d %-12d%n",
                    (i + 1), p.getPatientId(), p.getName(), p.getAge(), r.getSeverityScore());
        }
        System.out.println("=========================================");
        System.out.println("Total waiting patients: " + queue.size());
        System.out.println("=========================================\n");
    }

    /**
     * Gọi bệnh nhân tiếp theo trong hàng đợi thường (không khẩn cấp) vào khám theo thứ tự FIFO.
     *
     * @return Bệnh nhân được gọi, trả về null nếu hàng đợi thường rỗng
     */
    public Patient callNextNormalPatient() {
        if (normalQueue.isEmpty()) {
            System.out.println("[!] Normal Queue is empty.");
            return null;
        }
        Patient next = normalQueue.dequeue();
        System.out.println(">> CALLED NORMAL PATIENT: " + next.getName()
                + " (ID: " + next.getPatientId() + ")");
        return next;
    }

    /**
     * Kiểm tra xem có bệnh nhân nào trong hàng đợi thường đang chờ hay không.
     *
     * @return true nếu có ít nhất 1 bệnh nhân đang chờ
     */
    public boolean hasWaitingNormalPatients() {
        return !normalQueue.isEmpty();
    }

    /**
     * Hiển thị danh sách hàng đợi thường hiện tại theo thứ tự FIFO.
     */
    public void displayNormalQueue() {
        if (normalQueue.isEmpty()) {
            System.out.println("[!] Normal Queue is empty. No patients waiting.");
            return;
        }
        List<Patient> queue = normalQueue.peekAll();
        System.out.println("\n=========================================");
        System.out.println("   NORMAL QUEUE (FIFO Order)");
        System.out.println("=========================================");
        System.out.printf("%-5s %-10s %-25s %-6s%n",
                "No.", "ID", "Full Name", "Age");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < queue.size(); i++) {
            Patient p = queue.get(i);
            System.out.printf("%-5d %-10s %-25s %-6d%n",
                    (i + 1), p.getPatientId(), p.getName(), p.getAge());
        }
        System.out.println("=========================================");
        System.out.println("Total waiting patients: " + queue.size());
        System.out.println("=========================================\n");
    }

}
