package datastructure.queue;

import model.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 * Hàng đợi thường (FIFO Queue) dành cho các bệnh nhân có mức độ không khẩn cấp (Severity = 1).
 * Sử dụng cấu trúc danh sách liên kết đơn (Singly Linked List) với con trỏ đầu (head) và cuối (tail).
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

    /**
     * Khởi tạo hàng đợi FIFO rỗng.
     */
    public QueueFIFO() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Thêm bệnh nhân vào cuối hàng đợi.
     * 
     * @param patient Đối tượng Patient cần thêm
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
     * Lấy bệnh nhân ra khỏi đầu hàng đợi theo nguyên tắc vào trước ra trước (FIFO).
     * 
     * @return Đối tượng Patient được lấy ra, trả về null nếu hàng đợi rỗng
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
     * Xem thông tin bệnh nhân ở đầu hàng đợi mà không xóa khỏi hàng đợi.
     * 
     * @return Đối tượng Patient đầu hàng đợi, trả về null nếu hàng đợi rỗng
     */
    public Patient peek() {
        if (head == null) {
            return null;
        }
        return head.patient;
    }

    /**
     * Kiểm tra xem hàng đợi có đang trống không.
     * 
     * @return true nếu hàng đợi rỗng, ngược lại trả về false
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Trả về kích thước (số lượng bệnh nhân) của hàng đợi.
     * 
     * @return Kích thước của hàng đợi
     */
    public int size() {
        return size;
    }

    /**
     * Trả về danh sách bệnh nhân trong hàng đợi theo thứ tự từ người vào trước đến người vào sau.
     * 
     * @return Danh sách List<Patient> chứa thông tin các bệnh nhân
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
