package datastructure.heap;

import model.TriageRecord;
import java.util.List;

/**
 * Interface cho Max-Heap uu tien benh nhan theo muc do nghiem trong.
 * Benh nhan co severityScore cao nhat se duoc xu ly truoc.
 *
 * Member 2 se implement interface nay trong class MaxHeap.
 */
public interface IMaxHeap {

    /**
     * Them ban ghi triage vao heap.
     * @param record doi tuong TriageRecord can them
     */
    void insert(TriageRecord record);

    /**
     * Lay va xoa ban ghi co muc do uu tien cao nhat.
     * @return doi tuong TriageRecord co uu tien cao nhat, null neu heap rong
     */
    TriageRecord extractMax();

    /**
     * Kiem tra heap co rong hay khong.
     * @return true neu heap rong, false neu khong
     */
    boolean isEmpty();

    /**
     * Tra ve danh sach ban ghi sap xep theo thu tu uu tien (cao den thap)
     * ma khong xoa khoi heap.
     * @return List<TriageRecord> theo thu tu uu tien giam dan
     */
    List<TriageRecord> peekAll();

    /**
     * Tra ve so luong ban ghi dang trong hang doi.
     * @return so luong ban ghi
     */
    int size();

}
