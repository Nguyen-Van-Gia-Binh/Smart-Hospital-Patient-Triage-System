package datastructure.queue;

import model.Doctor;

/**
 * Node cho hang doi bac si, chua thong tin bac si.
 *
 * Member 3 se implement class nay.
 * Goi y: moi node chua 1 Doctor, va tro den node tiep theo.
 */
public class DoctorNode {

    private Doctor doctor;
    private DoctorNode next;

    public DoctorNode(Doctor doctor) {
        this.doctor = doctor;
        this.next = null;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DoctorNode getNext() {
        return next;
    }

    public void setNext(DoctorNode next) {
        this.next = next;
    }

}
