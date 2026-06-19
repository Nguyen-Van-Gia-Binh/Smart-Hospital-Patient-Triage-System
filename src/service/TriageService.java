package service;

import datastructure.queue.ITriageQueue;
import datastructure.queue.PriorityQueue;
import model.Patient;
import model.TriageRecord;
import java.util.List;

/**
 * Service xu ly nghiep vu phan luong cap cuu.
 *
 * Su dung ITriageQueue de quan ly benh nhan.
 * Benh nhan co severityScore cao hon se duoc xep dau hang doi va goi kham truoc.
 */
public class TriageService {

    private ITriageQueue triageQueue;

    public TriageService() {
        this.triageQueue = new PriorityQueue();
    }

    public TriageService(ITriageQueue customQueue) {
        this.triageQueue = customQueue;
    }

    public void setTriageQueue(ITriageQueue queue) {
        this.triageQueue = queue;
    }

    /**
     * Them benh nhan vao hang doi cap cuu.
     * Benh nhan duoc chen vao dung vi tri theo thu tu uu tien cua TriageRecord.
     *
     * @param patient doi tuong Patient can them vao hang doi
     * @param severityScore muc do nghiem trong hien tai cua benh nhan
     */
    public void addToEmergencyQueue(Patient patient, int severityScore) {
        TriageRecord record = new TriageRecord(patient, severityScore);
        triageQueue.enqueue(record);
        System.out.println("[+] Da them benh nhan vao hang doi cap cuu: " + patient.getName()
                + " (Muc do: " + severityScore + ")");
    }

    /**
     * Goi benh nhan nguy kich nhat vao phong kham.
     * Lay benh nhan o dau hang doi (uu tien cao nhat).
     *
     * @return doi tuong Patient duoc goi kham, null neu hang doi trong
     */
    public Patient callNextPatient() {
        if (triageQueue.isEmpty()) {
            System.out.println("[!] Hang doi cap cuu dang trong.");
            return null;
        }
        TriageRecord nextRecord = triageQueue.dequeue();
        Patient next = nextRecord.getPatient();
        System.out.println(">> GOI BENH NHAN: " + next.getName()
                + " (Muc do: " + nextRecord.getSeverityScore() + ")");
        return next;
    }

    /**
     * Kiem tra hang doi cap cuu co benh nhan nao dang cho khong.
     *
     * @return true neu co it nhat 1 benh nhan dang cho
     */
    public boolean hasWaitingPatients() {
        return !triageQueue.isEmpty();
    }

    /**
     * Hien thi danh sach hang doi cap cuu theo thu tu uu tien hien tai.
     */
    public void displayQueue() {
        if (triageQueue.isEmpty()) {
            System.out.println("[!] Hang doi cap cuu dang trong. Khong co benh nhan nao dang cho.");
            return;
        }
        List<TriageRecord> queue = triageQueue.peekAll();
        System.out.println("\n=========================================");
        System.out.println("   HANG DOI CAP CUU (Theo thu tu uu tien)");
        System.out.println("=========================================");
        System.out.printf("%-5s %-10s %-25s %-6s %-12s%n",
                "STT", "Ma BN", "Ho Ten", "Tuoi", "Muc Do (1-5)");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < queue.size(); i++) {
            TriageRecord r = queue.get(i);
            Patient p = r.getPatient();
            System.out.printf("%-5d %-10s %-25s %-6d %-12d%n",
                    (i + 1), p.getPatientId(), p.getName(), p.getAge(), r.getSeverityScore());
        }
        System.out.println("=========================================");
        System.out.println("Tong so benh nhan dang cho: " + queue.size());
        System.out.println("=========================================\n");
    }

}
