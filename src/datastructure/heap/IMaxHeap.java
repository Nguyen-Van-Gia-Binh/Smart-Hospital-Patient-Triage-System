package datastructure.heap;

import model.TriageRecord;
import java.util.List;

/**
 * Giao diện (Interface) cho cấu trúc dữ liệu Max-Heap.
 * Dùng để ưu tiên bệnh nhân dựa trên mức độ nghiêm trọng (severityScore).
 * Bệnh nhân có điểm mức độ nghiêm trọng cao nhất sẽ được xử lý trước.
 */
public interface IMaxHeap {

    /**
     * Thêm một hồ sơ phân loại (triage record) vào heap.
     * 
     * @param record Đối tượng TriageRecord cần thêm
     */
    void insert(TriageRecord record);

    /**
     * Lấy ra và xóa hồ sơ có mức độ ưu tiên cao nhất khỏi heap.
     * 
     * @return Đối tượng TriageRecord có ưu tiên cao nhất, trả về null nếu heap rỗng
     */
    TriageRecord extractMax();

    /**
     * Kiểm tra xem heap có đang rỗng hay không.
     * 
     * @return true nếu heap rỗng, ngược lại trả về false
     */
    boolean isEmpty();

    /**
     * Trả về danh sách tất cả các hồ sơ đã được sắp xếp theo thứ tự ưu tiên giảm dần 
     * (từ cao xuống thấp) mà không làm thay đổi dữ liệu trong heap.
     * 
     * @return Danh sách List<TriageRecord> chứa các hồ sơ theo thứ tự ưu tiên
     */
    List<TriageRecord> peekAll();

    /**
     * Trả về số lượng hồ sơ hiện đang có trong heap.
     * 
     * @return Số lượng hồ sơ
     */
    int size();

}
