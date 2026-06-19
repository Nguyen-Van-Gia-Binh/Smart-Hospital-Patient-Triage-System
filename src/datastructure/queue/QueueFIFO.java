package datastructure.queue;

import model.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 * Hàng đợi thường (FIFO Queue) cho các bệnh nhân có mức độ không khẩn cấp (Severity = 1).
 * Sử dụng cấu trúc danh sách liên kết đơn (Singly Linked List) với head và tail.
 */
public class QueueFIFO {

    private static class Node {
        Patient patient;
        Node next;

        Node(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public QueueFIFO() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Thêm bệnh nhân vào cuối hàng đợi.
     */
    public void enqueue(Patient patient) {
        if (patient == null) {
            return;
        }
        Node newNode = new Node(patient);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Lấy bệnh nhân ra khỏi đầu hàng đợi (FIFO).
     */
    public Patient dequeue() {
        if (head == null) {
            return null;
        }
        Patient patient = head.patient;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return patient;
    }

    /**
     * Xem bệnh nhân ở đầu hàng đợi mà không xóa.
     */
    public Patient peek() {
        if (head == null) {
            return null;
        }
        return head.patient;
    }

    /**
     * Kiểm tra hàng đợi có trống không.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Trả về kích thước hàng đợi.
     */
    public int size() {
        return size;
    }

    /**
     * Trả về danh sách bệnh nhân trong hàng đợi theo thứ tự từ trước ra sau.
     */
    public List<Patient> peekAll() {
        List<Patient> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.patient);
            current = current.next;
        }
        return list;
    }
}
