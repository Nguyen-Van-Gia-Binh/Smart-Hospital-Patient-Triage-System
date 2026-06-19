package datastructure.dll;

import model.MedicalRecord;

/**
 * Interface cho danh sach lien ket doi (Doubly Linked List).
 * Luu tru lich su kham benh cua benh nhan.
 *
 * Member 4 se implement interface nay trong class MedicalHistoryList.
 */
public interface IMedicalHistory {

    /**
     * Them mot benh an moi vao cuoi danh sach.
     * @param record doi tuong MedicalRecord can them
     */
    void addRecord(MedicalRecord record);

    /**
     * Hien thi n benh an gan nhat (tu moi den cu).
     * @param n so luong benh an can hien thi
     */
    void displayRecent(int n);

    /**
     * Lay node dau cua danh sach (benh an cu nhat).
     * @return node dau, null neu danh sach trong
     */
    HistoryNode getHead();

    /**
     * Lay node cuoi cua danh sach (benh an moi nhat).
     * @return node cuoi, null neu danh sach trong
     */
    HistoryNode getTail();

}
