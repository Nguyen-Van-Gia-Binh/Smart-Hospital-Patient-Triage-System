package datastructure.bst;

import model.Patient;

/**
 * Cay tim kiem nhi phan (BST) de luu tru va tra cuu benh nhan.
 *
 * Member 1 se implement class nay.
 * Class nay PHAI implement IPatientBST.
 */
public class PatientBST implements IPatientBST {
    private PatientNode root;

    public PatientBST() {
        this.root = null;
    }

    @Override
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private PatientNode insertRec(PatientNode root, Patient patient) {
        if (root == null) {
            root = new PatientNode(patient);
            return root;
        }
        if (patient.getPatientId().compareTo(root.getPatient().getPatientId()) < 0) {
            root.setLeft(insertRec(root.getLeft(), patient));
        } else if (patient.getPatientId().compareTo(root.getPatient().getPatientId()) > 0) {
            root.setRight(insertRec(root.getRight(), patient));
        }
        return root;
    }

    @Override
    public Patient search(String patientId) {
        return searchRec(root, patientId);
    }

    private Patient searchRec(PatientNode root, String patientId) {
        if (root == null || root.getPatient().getPatientId().equals(patientId)) {
            return root != null ? root.getPatient() : null;
        }
        if (patientId.compareTo(root.getPatient().getPatientId()) < 0) {
            return searchRec(root.getLeft(), patientId);
        }
        return searchRec(root.getRight(), patientId);
    }

    @Override
    public boolean delete(String patientId) {
        // TODO: Xoa benh nhan khoi cay BST
        throw new UnsupportedOperationException("Chua duoc implement - Member 1 lam phan nay");
    }

}
