package datastructure.dll;

import model.MedicalRecord;

/**
 * Node dành cho danh sách liên kết đôi (Doubly Linked List), 
 * chứa thông tin về một hồ sơ bệnh án (MedicalRecord).
 */
public class HistoryNode {
    MedicalRecord data;
    HistoryNode prev;
    HistoryNode next;

    /**
     * Khởi tạo node mới chứa hồ sơ bệnh án.
     * 
     * @param data Đối tượng MedicalRecord chứa dữ liệu bệnh án
     */
    public HistoryNode(MedicalRecord data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public MedicalRecord getData() {
        return data;
    }

    public HistoryNode getPrev() {
        return prev;
    }

    public HistoryNode getNext() {
        return next;
    }

    public void setData(MedicalRecord data) {
        this.data = data;
    }

    public void setPrev(HistoryNode prev) {
        this.prev = prev;
    }

    public void setNext(HistoryNode next) {
        this.next = next;
    }
}