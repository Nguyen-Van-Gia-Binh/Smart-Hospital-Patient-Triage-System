package datastructure.queue;

import model.Doctor;

/**
 * Interface cho hang doi bac si (FIFO Queue).
 * Quan ly thu tu bac si san sang tiep nhan benh nhan.
 *
 * Member 3 se implement interface nay trong class DoctorQueue.
 */
public interface IDoctorQueue {

    /**
     * Them bac si vao cuoi hang doi.
     * @param doctor doi tuong Doctor can them
     */
    void enqueue(Doctor doctor);

    /**
     * Lay va xoa bac si o dau hang doi (bac si ranh lau nhat).
     * @return doi tuong Doctor o dau hang doi, null neu hang doi rong
     */
    Doctor dequeue();

    /**
     * Kiem tra hang doi co rong hay khong.
     * @return true neu hang doi rong, false neu khong
     */
    boolean isEmpty();

}
