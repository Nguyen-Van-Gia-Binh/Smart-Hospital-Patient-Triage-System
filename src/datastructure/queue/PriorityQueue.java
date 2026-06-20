package datastructure.queue;

import model.TriageRecord;
import java.util.ArrayList;
import java.util.List;

/**
 * Hàng đợi có thứ tự (Sorted Queue) để quản lý luồng cấp cứu.
 *
 * CẤU TRÚC DỮ LIỆU:
 *   Sử dụng danh sách liên kết đơn (Singly Linked List) duy trì thứ tự đã sắp xếp.
 *   - head: Hồ sơ có mức độ ưu tiên CAO NHẤT luôn nằm ở đầu.
 *   - tail: Hồ sơ có mức độ ưu tiên THẤP NHẤT nằm ở cuối.
 *
 * THUẬT TOÁN CHÈN (enqueue):
 *   Khi thêm hồ sơ mới:
 *     1. Duyệt từ đầu hàng đợi.
 *     2. Tìm vị trí thích hợp dựa trên compareTo (so sánh độ ưu tiên và thời gian đến).
 *     3. Chèn vào vị trí tìm được.
 *   Độ phức tạp: O(n) cho mỗi lần chèn.
 *
 * THUẬT TOÁN LẤY RA (dequeue):
 *   Lấy và xóa node ở đầu hàng đợi.
 *   Độ phức tạp: O(1).
 */
public class PriorityQueue implements ITriageQueue {

    /** Node đầu hàng đợi - mức ưu tiên cao nhất. */
    private QueueNode head;

    /** Node cuối hàng đợi - mức ưu tiên thấp nhất. */
    private QueueNode tail;

    /** Số lượng hồ sơ đang có trong hàng đợi. */
    private int size;

    /**
     * Khởi tạo hàng đợi rỗng.
     */
    public PriorityQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void enqueue(TriageRecord record) {
        if (record == null) {
            return;
        }

        QueueNode newNode = new QueueNode(record);

        // TH1: Hàng đợi rỗng -> thêm vào làm cả đầu và cuối
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        // TH2: Hồ sơ mới có ưu tiên cao hơn tất cả hiện tại -> chèn lên trước node đầu
        if (record.compareTo(head.getData()) > 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        // TH3: Tìm vị trí chèn thích hợp trong hàng đợi
        // Dùng biến current để duyệt, chèn vào ngay sau current
        // Chạy tiếp tục khi node tiếp theo có độ ưu tiên cao hơn hoặc bằng record
        QueueNode current = head;
        while (current.next != null
                && current.next.getData().compareTo(record) >= 0) {
            current = current.next;
        }

        // Chèn newNode vào sau current
        newNode.next = current.next;
        current.next = newNode;

        // Nếu chèn vào cuối danh sách, cần cập nhật lại tail
        if (newNode.next == null) {
            tail = newNode;
        }

        size++;
    }

    @Override
    public TriageRecord dequeue() {
        if (head == null) {
            return null;
        }

        TriageRecord data = head.getData();
        head = head.next;

        // Nếu lấy xong mà hàng đợi trống thì xóa luôn tail
        if (head == null) {
            tail = null;
        }

        size--;
        return data;
    }

    @Override
    public TriageRecord peek() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<TriageRecord> peekAll() {
        List<TriageRecord> result = new ArrayList<>();
        QueueNode current = head;
        while (current != null) {
            result.add(current.getData());
            current = current.next;
        }
        return result;
    }

}
