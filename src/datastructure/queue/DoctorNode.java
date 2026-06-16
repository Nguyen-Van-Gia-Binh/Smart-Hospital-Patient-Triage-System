package datastructure.queue;

/**
 * Node cho hang doi bac si, chua thong tin bac si.
 *
 * Member 3 se implement class nay.
 * Goi y: moi node chua 1 Doctor, va tro den node tiep theo.
 */
public class DoctorNode {

    // TODO: Member 3 implement
    // Goi y cac field:
    // - Doctor data
    // - DoctorNode next

    public DoctorNode() {
        private Doctor data;
        private DoctorNode next;
    }

    public DoctorNode(Doctor data) {
        this.data = data;
        this.next = null;
    }

    public Doctor getData() {
        return data;
    }

    public void setData(Doctor data) {
        this.data = data;
    }
    public DoctorNode getNext() {
        return next;
    }
    public void setNext(DoctorNode next) {
        this.next = next;
    }
}
