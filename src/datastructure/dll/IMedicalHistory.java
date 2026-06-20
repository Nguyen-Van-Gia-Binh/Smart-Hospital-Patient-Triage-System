package datastructure.dll;

import model.MedicalRecord;

/**
 * Giao diện (Interface) cho danh sách liên kết đôi (Doubly Linked List).
 * Dùng để lưu trữ và quản lý lịch sử khám bệnh của bệnh nhân.
 */
public interface IMedicalHistory {

    /**
     * Thêm một hồ sơ bệnh án mới vào cuối danh sách.
     * 
     * @param record Đối tượng MedicalRecord cần thêm
     */
    void addRecord(MedicalRecord record);

    /**
     * Hiển thị n hồ sơ bệnh án gần nhất (sắp xếp từ mới nhất đến cũ nhất).
     * 
     * @param n Số lượng bệnh án cần hiển thị
     */
    void displayRecent(int n);

    /**
     * Lấy node đầu tiên của danh sách (chứa bệnh án cũ nhất).
     * 
     * @return HistoryNode trỏ đến phần tử đầu, trả về null nếu danh sách rỗng
     */
    HistoryNode getHead();

    /**
     * Lấy node cuối cùng của danh sách (chứa bệnh án mới nhất).
     * 
     * @return HistoryNode trỏ đến phần tử cuối, trả về null nếu danh sách rỗng
     */
    HistoryNode getTail();

}
