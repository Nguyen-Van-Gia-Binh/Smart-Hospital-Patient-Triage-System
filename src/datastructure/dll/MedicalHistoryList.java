package datastructure.dll;

import model.MedicalRecord;

/**
 * Danh sach lien ket doi (Doubly Linked List) de luu tru lich su kham benh.
 * Cho phep duyet tien/lui qua cac benh an cua benh nhan.
 *
 * Member 4 se implement class nay.
 * Class nay PHAI implement IMedicalHistory.
 */
public class MedicalHistoryList implements IMedicalHistory {
    HistoryNode head;
    HistoryNode tail;
    int size;

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
            System.out.println("Khong co lich su kham benh.");
            return;
        }

        if (n <= 0) {
            System.out.println("So luong benh an phai lon hon 0.");
            return;
        }

        HistoryNode current = tail;
        int count = 0;

        System.out.println("\n=== 5 Benh An Gan Nhat ===");
        while (current != null && count < n) {
            System.out.println(current.getData());
            current = current.prev;
            count++;
        }
    }

}
