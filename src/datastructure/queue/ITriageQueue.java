package datastructure.queue;

import model.TriageRecord;
import java.util.List;

/**
 * Giao diện (Interface) cho hàng đợi ưu tiên (Priority Queue / Sorted Queue).
 *
 * Nguyên tắc hoạt động:
 *   - Khi thêm hồ sơ phân loại mới, tự động chèn vào đúng vị trí dựa trên mức độ ưu tiên.
 *   - dequeue() luôn lấy ra hồ sơ ở đầu hàng đợi (có mức độ ưu tiên cao nhất).
 *
 * Cấu trúc bên trong: Danh sách liên kết đơn (Singly Linked List) đã được sắp xếp.
 */
public interface ITriageQueue {

    /**
     * Thêm một hồ sơ phân loại (TriageRecord) vào hàng đợi.
     * Tự động chèn vào vị trí thích hợp theo thứ tự so sánh (compareTo).
     *
     * @param record Đối tượng TriageRecord cần thêm vào hàng đợi
     */
    void enqueue(TriageRecord record);

    /**
     * Lấy và xóa hồ sơ ở đầu hàng đợi (có ưu tiên cao nhất).
     *
     * @return Đối tượng TriageRecord có ưu tiên cao nhất, trả về null nếu hàng đợi rỗng
     */
    TriageRecord dequeue();

    /**
     * Xem thông tin hồ sơ ở đầu hàng đợi mà không xóa nó.
     *
     * @return Đối tượng TriageRecord có ưu tiên cao nhất, trả về null nếu hàng đợi rỗng
     */
    TriageRecord peek();

    /**
     * Kiểm tra xem hàng đợi có rỗng hay không.
     *
     * @return true nếu hàng đợi rỗng, ngược lại trả về false (có ít nhất 1 hồ sơ)
     */
    boolean isEmpty();

    /**
     * Trả về số lượng hồ sơ hiện đang có trong hàng đợi.
     *
     * @return Số lượng hồ sơ
     */
    int size();

    /**
     * Trả về danh sách các hồ sơ theo thứ tự ưu tiên (từ cao đến thấp) 
     * mà không làm thay đổi nội dung hàng đợi.
     *
     * @return Danh sách List<TriageRecord> theo thứ tự ưu tiên hiện tại
     */
    List<TriageRecord> peekAll();

}
