package datastructure.queue;

import model.TriageRecord;

/**
 * Node cua Sorted Queue (danh sach lien ket don da sap xep).
 * Moi node chua mot doi tuong TriageRecord va con tro toi node tiep theo.
 */
public class QueueNode {

    /** Du lieu triage cua node nay. */
    private TriageRecord data;

    /** Con tro toi node tiep theo trong danh sach. */
    QueueNode next;

    /**
     * Khoi tao mot QueueNode moi voi du lieu triage.
     *
     * @param data doi tuong TriageRecord can luu tru
     */
    public QueueNode(TriageRecord data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Lay du lieu triage cua node.
     *
     * @return doi tuong TriageRecord
     */
    public TriageRecord getData() {
        return data;
    }

    /**
     * Cap nhat du lieu triage cua node.
     *
     * @param data doi tuong TriageRecord moi
     */
    public void setData(TriageRecord data) {
        this.data = data;
    }

}
