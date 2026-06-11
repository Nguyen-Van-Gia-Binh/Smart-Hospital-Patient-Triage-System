package datastructure.bst;

import model.Patient;

/**
 * Cay tim kiem nhi phan (BST) de luu tru va tra cuu benh nhan.
 *
 * Member 1 se implement class nay.
 * Class nay PHAI implement IPatientBST.
 */
public class PatientBST implements IPatientBST {

    // TODO: Member 1 implement tat ca cac method ben duoi

    @Override
    public void insert(Patient patient) {
        String patientId = patient.getPatientId();
    }

    @Override
    public Patient search(String patientId) {
        // TODO: Tim kiem benh nhan theo patientId (O(log n))
        throw new UnsupportedOperationException("Chua duoc implement - Member 1 lam phan nay");
    }

    @Override
    public boolean delete(String patientId) {
        // TODO: Xoa benh nhan khoi cay BST
        throw new UnsupportedOperationException("Chua duoc implement - Member 1 lam phan nay");
    }

}
