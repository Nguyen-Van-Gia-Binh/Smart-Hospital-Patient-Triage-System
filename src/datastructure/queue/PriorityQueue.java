package datastructure.queue;

import model.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 * Sorted Queue (Hang doi co thu tu) de quan ly benh nhan cap cuu.
 *
 * CAU TRUC DU LIEU:
 *   Su dung danh sach lien ket don (Singly Linked List) da duoc sap xep.
 *   - head: Benh nhan co muc do uu tien CAO NHAT (severityScore lon nhat) luon o dau.
 *   - tail: Benh nhan co muc do uu tien thap nhat o cuoi.
 *
 * THUAT TOAN CHEN (enqueue):
 *   Khi them benh nhan moi:
 *     1. Duyet tu dau hang doi.
 *     2. Tim vi tri dau tien ma node tiep theo co severityScore < severityScore cua benh nhan moi.
 *     3. Chen benh nhan moi vao truoc vi tri do.
 *   Ket qua: Hang doi luon duoc sap xep giam dan theo severityScore.
 *   Do phuc tap: O(n) moi lan chen.
 *
 * THUAT TOAN LAY RA (dequeue):
 *   Lay va xoa node o dau hang doi (benh nhan co uu tien cao nhat).
 *   Do phuc tap: O(1).
 *
 * Vi du minh hoa:
 *   Them BN001 (score=3) -> [BN001:3]
 *   Them BN002 (score=5) -> [BN002:5] -> [BN001:3]
 *   Them BN003 (score=4) -> [BN002:5] -> [BN003:4] -> [BN001:3]
 *   dequeue()            -> tra ve BN002, con lai: [BN003:4] -> [BN001:3]
 */
public class PriorityQueue implements ITriageQueue {

    /** Dau hang doi - benh nhan uu tien cao nhat. */
    private QueueNode head;

    /** Cuoi hang doi - benh nhan uu tien thap nhat. */
    private QueueNode tail;

    /** So luong benh nhan dang trong hang doi. */
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
     * Them benh nhan vao hang doi theo thu tu uu tien.
     * Chen vao dung vi tri sao cho hang doi luon sap xep giam dan theo severityScore.
     * Neu severityScore bang nhau, benh nhan den truoc duoc xep truoc (giu nguyen FIFO).
     *
     * @param patient doi tuong Patient can them vao hang doi
     */
    @Override
    public void enqueue(Patient patient) {
        if (patient == null) {
            return;
        }

        QueueNode newNode = new QueueNode(patient);

        // TH1: Hang doi trong -> them vao lam dau va cuoi
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        // TH2: Benh nhan moi co uu tien cao hon tat ca hien tai
        //      -> chen vao truoc dau hang doi
        if (patient.getSeverityScore() > head.getData().getSeverityScore()) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        // TH3: Tim vi tri chen thich hop trong hang doi
        //      Dung con tro current de duyet, chen sau current
        //      (khi node tiep theo co score < score cua benh nhan moi)
        QueueNode current = head;
        while (current.next != null
                && current.next.getData().getSeverityScore() >= patient.getSeverityScore()) {
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
     * Lay va xoa benh nhan o dau hang doi (uu tien cao nhat).
     *
     * @return doi tuong Patient co uu tien cao nhat, null neu hang doi trong
     */
    @Override
    public Patient dequeue() {
        if (head == null) {
            return null;
        }

        Patient data = head.getData();
        head = head.next;

        // Neu hang doi thanh trong, cap nhat tail
        if (head == null) {
            tail = null;
        }

        size--;
        return data;
    }

    /**
     * Xem benh nhan dau hang doi ma khong xoa.
     *
     * @return doi tuong Patient co uu tien cao nhat, null neu hang doi trong
     */
    @Override
    public Patient peek() {
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
     * Tra ve so luong benh nhan dang trong hang doi.
     *
     * @return so luong benh nhan
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tra ve danh sach benh nhan theo thu tu uu tien hien tai (tu cao den thap)
     * ma khong lam thay doi hang doi.
     * Vi hang doi da duoc sap xep san, chi can duyet tuyen tinh.
     *
     * @return List<Patient> theo thu tu uu tien hien tai
     */
    @Override
    public List<Patient> peekAll() {
        List<Patient> result = new ArrayList<>();
        QueueNode current = head;
        while (current != null) {
            result.add(current.getData());
            current = current.next;
        }
        return result;
    }

}
