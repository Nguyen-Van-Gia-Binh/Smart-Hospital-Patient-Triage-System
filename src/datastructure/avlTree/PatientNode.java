package datastructure.avlTree;

import model.Patient;

/**
 * Node cho cay AVL, chua thong tin benh nhan.
 *
 * Member 1 se implement class nay.
 * Moi node chua 1 Patient, tro den left/right va luu height de can bang.
 */
public class PatientNode {

    private Patient patient;
    private PatientNode left;
    private PatientNode right;
    private int height;

    public PatientNode(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
        this.height = 1;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
