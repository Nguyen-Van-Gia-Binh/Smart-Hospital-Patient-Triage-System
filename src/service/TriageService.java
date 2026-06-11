package service;

import datastructure.heap.IMaxHeap;
import datastructure.heap.MaxHeap;
import model.Patient;

/**
 * Service xu ly nghiep vu phan luong cap cuu.
 * Su dung IMaxHeap de uu tien benh nhan theo muc do nghiem trong.
 */
public class TriageService {

    private IMaxHeap maxHeap;

    public TriageService() {
        this.maxHeap = new MaxHeap();
    }

    /**
     * Them benh nhan vao hang doi cap cuu.
     */
    public void addToEmergencyQueue(Patient patient) {
        maxHeap.insert(patient);
        System.out.println("[+] Da them benh nhan vao hang doi cap cuu: " + patient.getName()
                + " (Muc do: " + patient.getSeverityScore() + ")");
    }

    /**
     * Goi benh nhan nguy kich nhat vao phong kham.
     */
    public Patient callNextPatient() {
        if (maxHeap.isEmpty()) {
            System.out.println("[!] Hang doi cap cuu dang trong.");
            return null;
        }
        Patient next = maxHeap.extractMax();
        System.out.println(">> GOI BENH NHAN: " + next.getName()
                + " (Muc do: " + next.getSeverityScore() + ")");
        return next;
    }

    /**
     * Kiem tra hang doi cap cuu co benh nhan nao dang cho khong.
     */
    public boolean hasWaitingPatients() {
        return !maxHeap.isEmpty();
    }

}
