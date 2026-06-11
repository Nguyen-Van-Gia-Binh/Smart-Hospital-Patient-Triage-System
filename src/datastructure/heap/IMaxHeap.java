package datastructure.heap;

import model.Patient;

/**
 * Interface cho Max-Heap uu tien benh nhan theo muc do nghiem trong.
 * Benh nhan co severityScore cao nhat se duoc xu ly truoc.
 *
 * Member 2 se implement interface nay trong class MaxHeap.
 */
public interface IMaxHeap {

    /**
     * Them benh nhan vao heap.
     * @param patient doi tuong Patient can them
     */
    void insert(Patient patient);

    /**
     * Lay va xoa benh nhan co muc do uu tien cao nhat (severityScore lon nhat).
     * @return doi tuong Patient co uu tien cao nhat, null neu heap rong
     */
    Patient extractMax();

    /**
     * Kiem tra heap co rong hay khong.
     * @return true neu heap rong, false neu khong
     */
    boolean isEmpty();

}
