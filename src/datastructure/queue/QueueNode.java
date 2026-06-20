package datastructure.queue;

import model.TriageRecord;

/**
 * Node dành cho Hàng đợi có thứ tự (Sorted Queue) - danh sách liên kết đơn đã sắp xếp.
 * Mỗi node chứa một đối tượng TriageRecord (hồ sơ phân loại) và một con trỏ tới node tiếp theo.
 */
public class QueueNode {

    /** Dữ liệu hồ sơ phân loại của node này. */
    private TriageRecord data;

    /** Con trỏ trỏ tới node tiếp theo trong danh sách. */
    QueueNode next;

    /**
     * Khởi tạo một QueueNode mới với dữ liệu hồ sơ phân loại.
     *
     * @param data Đối tượng TriageRecord cần lưu trữ trong node
     */
    public QueueNode(TriageRecord data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Lấy dữ liệu hồ sơ phân loại đang lưu trong node.
     *
     * @return Đối tượng TriageRecord
     */
    public TriageRecord getData() {
        return data;
    }

    /**
     * Cập nhật dữ liệu hồ sơ phân loại cho node.
     *
     * @param data Đối tượng TriageRecord mới
     */
    public void setData(TriageRecord data) {
        this.data = data;
    }

}
