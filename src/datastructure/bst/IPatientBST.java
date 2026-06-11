package datastructure.bst;

import model.Patient;

/**
 * Interface cho cay tim kiem nhi phan (Binary Search Tree).
 * Luu tru va tra cuu benh nhan theo patientId.
 *
 * Member 1 se implement interface nay trong class PatientBST.
 */
public interface IPatientBST {

    /**
     * Chen mot benh nhan vao cay BST.
     * @param patient doi tuong Patient can chen
     */
    void insert(Patient patient);

    /**
     * Tim kiem benh nhan theo ma benh nhan.
     * @param patientId ma benh nhan can tim
     * @return doi tuong Patient neu tim thay, null neu khong
     */
    Patient search(String patientId);

    /**
     * Xoa benh nhan khoi cay BST theo ma benh nhan.
     * @param patientId ma benh nhan can xoa
     * @return true neu xoa thanh cong, false neu khong tim thay
     */
    boolean delete(String patientId);

}
