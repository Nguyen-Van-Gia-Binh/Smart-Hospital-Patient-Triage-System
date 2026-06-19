package datastructure.queue;

import model.Patient;

/**
 * Node cua Sorted Queue (danh sach lien ket don da sap xep).
 * Moi node chua mot doi tuong Patient va con tro toi node tiep theo.
 */
public class QueueNode {

    /** Du lieu benh nhan cua node nay. */
    private Patient data;

    /** Con tro toi node tiep theo trong danh sach. */
    QueueNode next;

    /**
     * Khoi tao mot QueueNode moi voi du lieu benh nhan.
     *
     * @param data doi tuong Patient can luu tru
     */
    public QueueNode(Patient data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Lay du lieu benh nhan cua node.
     *
     * @return doi tuong Patient
     */
    public Patient getData() {
        return data;
    }

    /**
     * Cap nhat du lieu benh nhan cua node.
     *
     * @param data doi tuong Patient moi
     */
    public void setData(Patient data) {
        this.data = data;
    }

}
