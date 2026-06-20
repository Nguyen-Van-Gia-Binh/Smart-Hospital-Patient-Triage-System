package datastructure.avlTree;

import model.Patient;

/**
 * Giao diện (Interface) cho cây tìm kiếm nhị phân (AVL Tree).
 * Dùng để lưu trữ và tra cứu thông tin bệnh nhân theo mã bệnh nhân (patientId).
 */
public interface IPatientAVLTree {

    /**
     * Chèn một bệnh nhân mới vào cây AVL.
     * 
     * @param patient Đối tượng Patient cần chèn
     */
    void insert(Patient patient);

    /**
     * Tìm kiếm thông tin bệnh nhân dựa trên mã bệnh nhân.
     * 
     * @param patientId Mã bệnh nhân cần tìm kiếm
     * @return Đối tượng Patient nếu tìm thấy, trả về null nếu không tồn tại
     */
    Patient search(String patientId);

    /**
     * Xóa thông tin bệnh nhân khỏi cây AVL dựa trên mã bệnh nhân.
     * 
     * @param patientId Mã bệnh nhân cần xóa
     * @return true nếu xóa thành công, false nếu không tìm thấy bệnh nhân để xóa
     */
    boolean delete(String patientId);

}
