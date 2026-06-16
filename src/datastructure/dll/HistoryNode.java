package datastructure.dll;

import model.MedicalRecord;

/**
 * Node cho danh sach lien ket doi, chua thong tin benh an.
 *
 * Member 4 se implement class nay.
 * Goi y: moi node chua 1 MedicalRecord, va tro den prev/next.
 */
public class HistoryNode {
    MedicalRecord data;
    HistoryNode prev;
    HistoryNode next;

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