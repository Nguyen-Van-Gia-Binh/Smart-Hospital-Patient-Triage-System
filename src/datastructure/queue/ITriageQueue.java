package datastructure.queue;

import model.Patient;
import java.util.List;

/**
 * Interface cho hang doi uu tien (Priority Queue / Sorted Queue).
 *
 * Nguyen tac hoat dong:
 *   - Khi them benh nhan moi, tu dong chen vao dung vi tri dua tren severityScore.
 *   - Benh nhan co severityScore cao hon luon duoc xep truoc.
 *   - Neu severityScore bang nhau, benh nhan den truoc duoc xep truoc (FIFO).
 *   - dequeue() luon lay ra benh nhan o dau hang doi (uu tien cao nhat).
 *
 * Cau truc ben trong: Danh sach lien ket don (Singly Linked List) da duoc sap xep.
 */
public interface ITriageQueue {

    /**
     * Them benh nhan vao hang doi.
     * Tu dong chen vao dung vi tri theo thu tu severityScore giam dan.
     *
     * @param patient doi tuong Patient can them vao hang doi
     */
    void enqueue(Patient patient);

    /**
     * Lay va xoa benh nhan o dau hang doi (uu tien cao nhat).
     *
     * @return doi tuong Patient co uu tien cao nhat, null neu hang doi trong
     */
    Patient dequeue();

    /**
     * Xem benh nhan dau hang doi ma khong xoa.
     *
     * @return doi tuong Patient co uu tien cao nhat, null neu hang doi trong
     */
    Patient peek();

    /**
     * Kiem tra hang doi co rong hay khong.
     *
     * @return true neu hang doi rong, false neu co it nhat 1 benh nhan
     */
    boolean isEmpty();

    /**
     * Tra ve so luong benh nhan dang trong hang doi.
     *
     * @return so luong benh nhan
     */
    int size();

    /**
     * Tra ve danh sach benh nhan theo thu tu uu tien (tu cao den thap)
     * ma khong thay doi hang doi.
     *
     * @return List<Patient> theo thu tu uu tien hien tai
     */
    List<Patient> peekAll();

}
