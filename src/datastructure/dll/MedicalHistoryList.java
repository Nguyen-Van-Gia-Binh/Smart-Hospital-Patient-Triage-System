package datastructure.dll;

import model.MedicalRecord;

/**
 * Cấu trúc dữ liệu Danh sách liên kết đôi (Doubly Linked List) dùng để lưu trữ lịch sử khám bệnh.
 * Cho phép duyệt tiến hoặc lùi qua các hồ sơ bệnh án của một bệnh nhân.
 */
public class MedicalHistoryList implements IMedicalHistory {
    HistoryNode head;
    HistoryNode tail;
    int size;

    /**
     * Khởi tạo danh sách lịch sử bệnh án rỗng.
     */
    public MedicalHistoryList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void addRecord(MedicalRecord record) {
        HistoryNode newNode = new HistoryNode(record);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void displayRecent(int n) {
        if (tail == null) {
            System.out.println("No medical history available.");
            return;
        }

        if (n <= 0) {
            System.out.println("Number of records to display must be greater than 0.");
            return;
        }

        HistoryNode current = tail;
        int count = 0;

        System.out.println("\n=== Recent Medical Records ===");
        while (current != null && count < n) {
            System.out.println(current.getData());
            current = current.prev;
            count++;
        }
    }

    @Override
    public HistoryNode getHead() {
        return head;
    }

    @Override
    public HistoryNode getTail() {
        return tail;
    }

}
