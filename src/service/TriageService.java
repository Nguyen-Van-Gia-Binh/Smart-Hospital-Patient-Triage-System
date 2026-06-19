package service;

import datastructure.queue.ITriageQueue;
import datastructure.queue.PriorityQueue;
import datastructure.queue.QueueFIFO;
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
    private QueueFIFO normalQueue;

    public TriageService() {
        this.triageQueue = new PriorityQueue();
        this.normalQueue = new QueueFIFO();
    }

    public TriageService(ITriageQueue customQueue) {
        this.triageQueue = customQueue;
        this.normalQueue = new QueueFIFO();
    }

    public void setTriageQueue(ITriageQueue queue) {
        this.triageQueue = queue;
    }

    public QueueFIFO getNormalQueue() {
        return normalQueue;
    }

    /**
     * Enqueues a patient into the appropriate queue.
     * If severityScore == 1 (Non-Urgent) -> Normal Queue (FIFO).
     * If severityScore > 1 -> Emergency Queue (Priority Queue / Heap).
     *
     * @param patient Patient to be enqueued
     * @param severityScore current severity score of the patient
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
     * Call the most critical emergency patient (highest priority).
     *
     * @return called Patient, null if queue is empty
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
     * Check if there are any emergency patients waiting.
     *
     * @return true if at least 1 patient is waiting
     */
    public boolean hasWaitingPatients() {
        return !triageQueue.isEmpty();
    }

    /**
     * Display the current Emergency Queue in priority order.
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
     * Call the next normal patient (FIFO).
     *
     * @return called Patient, null if queue is empty
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
     * Check if there are any normal patients waiting.
     *
     * @return true if at least 1 patient is waiting
     */
    public boolean hasWaitingNormalPatients() {
        return !normalQueue.isEmpty();
    }

    /**
     * Display the current Normal Queue (FIFO).
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
