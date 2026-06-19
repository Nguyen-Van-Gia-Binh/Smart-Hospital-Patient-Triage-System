package datastructure.queue;

import model.TriageRecord;
import java.util.List;

/**
 * Interface cho hang doi uu tien (Priority Queue / Sorted Queue).
 *
 * Nguyen tac hoat dong:
 *   - Khi them ban ghi moi, tu dong chen vao dung vi tri dua tren priority.
 *   - dequeue() luon lay ra ban ghi o dau hang doi (uu tien cao nhat).
 *
 * Cau truc ben trong: Danh sach lien ket don (Singly Linked List) da duoc sap xep.
 */
public interface ITriageQueue {

    /**
     * Them ban ghi triage vao hang doi.
     * Tu dong chen vao dung vi tri theo thu tu so sanh compareTo.
     *
     * @param record doi tuong TriageRecord can them vao hang doi
     */
    void enqueue(TriageRecord record);

    /**
     * Lay va xoa ban ghi o dau hang doi (uu tien cao nhat).
     *
     * @return doi tuong TriageRecord co uu tien cao nhat, null neu hang doi trong
     */
    TriageRecord dequeue();

    /**
     * Xem ban ghi dau hang doi ma khong xoa.
     *
     * @return doi tuong TriageRecord co uu tien cao nhat, null neu hang doi trong
     */
    TriageRecord peek();

    /**
     * Kiem tra hang doi co rong hay khong.
     *
     * @return true neu hang doi rong, false neu co it nhat 1 ban ghi
     */
    boolean isEmpty();

    /**
     * Tra ve so luong ban ghi dang trong hang doi.
     *
     * @return so luong ban ghi
     */
    int size();

    /**
     * Tra ve danh sach ban ghi theo thu tu uu tien (tu cao den thap)
     * ma khong thay doi hang doi.
     *
     * @return List<TriageRecord> theo thu tu uu tien hien tai
     */
    List<TriageRecord> peekAll();

}
