package datastructure.queue;

import model.TriageRecord;
import java.util.ArrayList;
import java.util.List;

/**
 * Sorted Queue (Hang doi co thu tu) de quan ly phan luong cap cuu.
 *
 * CAU TRUC DU LIEU:
 *   Su dung danh sach lien ket don (Singly Linked List) da duoc sap xep.
 *   - head: Ban ghi co muc do uu tien CAO NHAT luon o dau.
 *   - tail: Ban ghi co muc do uu tien thap nhat o cuoi.
 *
 * THUAT TOAN CHEN (enqueue):
 *   Khi them ban ghi moi:
 *     1. Duyet tu dau hang doi.
 *     2. Tim vi tri thich hop dua tren compareTo (so sanh do uu tien va thoi gian den).
 *     3. Chen vao dung vi tri.
 *   Do phuc tap: O(n) moi lan chen.
 *
 * THUAT TOAN LAY RA (dequeue):
 *   Lay va xoa node o dau hang doi.
 *   Do phuc tap: O(1).
 */
public class PriorityQueue implements ITriageQueue {

    /** Dau hang doi - uu tien cao nhat. */
    private QueueNode head;

    /** Cuoi hang doi - uu tien thap nhat. */
    private QueueNode tail;

    /** So luong ban ghi dang trong hang doi. */
    private int size;

    /**
     * Khoi tao hang doi trong.
     */
    public PriorityQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Them ban ghi triage vao hang doi theo thu tu uu tien.
     * Neu bang do uu tien, nguoi den truoc dung truoc (FIFO).
     *
     * @param record doi tuong TriageRecord can them
     */
    @Override
    public void enqueue(TriageRecord record) {
        if (record == null) {
            return;
        }

        QueueNode newNode = new QueueNode(record);

        // TH1: Hang doi trong -> them vao lam dau va cuoi
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        // TH2: Ban ghi moi co uu tien cao hon tat ca hien tai -> chen vao truoc dau hang doi
        if (record.compareTo(head.getData()) > 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        // TH3: Tim vi tri chen thich hop trong hang doi
        //      Dung con tro current de duyet, chen sau current
        //      Chay den khi node tiep theo co priority thap hon record
        QueueNode current = head;
        while (current.next != null
                && current.next.getData().compareTo(record) >= 0) {
            current = current.next;
        }

        // Chen newNode sau current
        newNode.next = current.next;
        current.next = newNode;

        // Neu chen vao cuoi, cap nhat tail
        if (newNode.next == null) {
            tail = newNode;
        }

        size++;
    }

    /**
     * Lay va xoa ban ghi o dau hang doi (uu tien cao nhat).
     *
     * @return doi tuong TriageRecord co uu tien cao nhat, null neu hang doi trong
     */
    @Override
    public TriageRecord dequeue() {
        if (head == null) {
            return null;
        }

        TriageRecord data = head.getData();
        head = head.next;

        // Neu hang doi thanh trong, cap nhat tail
        if (head == null) {
            tail = null;
        }

        size--;
        return data;
    }

    /**
     * Xem ban ghi dau hang doi ma khong xoa.
     *
     * @return doi tuong TriageRecord co uu tien cao nhat, null neu hang doi trong
     */
    @Override
    public TriageRecord peek() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }

    /**
     * Kiem tra hang doi co rong hay khong.
     *
     * @return true neu hang doi rong
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Tra ve so luong ban ghi dang trong hang doi.
     *
     * @return so luong ban ghi
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tra ve danh sach ban ghi theo thu tu uu tien hien tai (tu cao den thap)
     * ma khong lam thay doi hang doi.
     *
     * @return List<TriageRecord> theo thu tu uu tien hien tai
     */
    @Override
    public List<TriageRecord> peekAll() {
        List<TriageRecord> result = new ArrayList<>();
        QueueNode current = head;
        while (current != null) {
            result.add(current.getData());
            current = current.next;
        }
        return result;
    }

}
