package datastructure.bst;

import model.Patient;

/**
 * Node cho cay BST, chua thong tin benh nhan.
 *
 * Member 1 se implement class nay.
 * Goi y: moi node chua 1 Patient, va tro den left/right.
 */
public class PatientNode {

    private Patient patient;
    private PatientNode left;
    private PatientNode right;

    public PatientNode(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientNode getLeft() {
        return left;
    }

    public void setLeft(PatientNode left) {
        this.left = left;
    }

    public PatientNode getRight() {
        return right;
    }

    public void setRight(PatientNode right) {
        this.right = right;
    }

}
