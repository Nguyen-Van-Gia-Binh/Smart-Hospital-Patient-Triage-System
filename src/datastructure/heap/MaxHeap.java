package datastructure.heap;

import model.Patient;

/**
 * Max-Heap de uu tien benh nhan theo muc do nghiem trong (severityScore).
 * Benh nhan nang nhat (score cao nhat) se duoc goi kham truoc.
 *
 * Member 2 se implement class nay.
 * Class nay PHAI implement IMaxHeap.
 */
public class MaxHeap implements IMaxHeap {

    // TODO: Member 2 implement tat ca cac method ben duoi

    @Override
    public void insert(Patient patient) {
        // TODO: Them benh nhan vao heap va duy tri tinh chat Max-Heap
        throw new UnsupportedOperationException("Chua duoc implement - Member 2 lam phan nay");
    }

    @Override
    public Patient extractMax() {
        // TODO: Lay va xoa benh nhan co severityScore cao nhat
        throw new UnsupportedOperationException("Chua duoc implement - Member 2 lam phan nay");
    }

    @Override
    public boolean isEmpty() {
        // TODO: Kiem tra heap co rong hay khong
        throw new UnsupportedOperationException("Chua duoc implement - Member 2 lam phan nay");
    }

}
