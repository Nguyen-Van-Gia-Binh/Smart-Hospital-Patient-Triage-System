package service;

import datastructure.queue.IDoctorQueue;
import datastructure.queue.DoctorQueue;
import model.Doctor;
import model.Patient;

/**
 * Service xu ly nghiep vu phan cong bac si.
 * Su dung IDoctorQueue de quan ly hang doi bac si san sang.
 */
public class DoctorService {

    private IDoctorQueue doctorQueue;

    public DoctorService() {
        this.doctorQueue = new DoctorQueue();
    }

    /**
     * Them bac si vao ca truc (san sang nhan benh nhan).
     */
    public void addDoctorToShift(Doctor doctor) {
        doctorQueue.enqueue(doctor);
        System.out.println("[+] Bac si san sang nhan ca: " + doctor.getDoctorName()
                + " (ID: " + doctor.getDoctorId() + ")");
    }

    /**
     * Phan cong bac si cho benh nhan.
     * Lay bac si dau hang doi (ranh lau nhat) de tiep nhan.
     */
    public Doctor assignDoctor(Patient patient) {
        if (doctorQueue.isEmpty()) {
            System.out.println("[!] Khong co bac si nao dang ranh. Benh nhan ["
                    + patient.getName() + "] vui long cho.");
            return null;
        }

        Doctor assigned = doctorQueue.dequeue();
        assigned.setAvailable(false);
        System.out.println(">> PHAN CONG: BS " + assigned.getDoctorName()
                + " tiep nhan benh nhan [" + patient.getName() + "]");
        return assigned;
    }

    /**
     * Bac si kham xong, quay lai hang doi de tiep nhan ca moi.
     */
    public void releaseDoctor(Doctor doctor) {
        if (doctor != null) {
            doctor.setAvailable(true);
            doctorQueue.enqueue(doctor);
            System.out.println("[v] BS " + doctor.getDoctorName()
                    + " da kham xong va quay lai hang doi.");
        }
    }

}
