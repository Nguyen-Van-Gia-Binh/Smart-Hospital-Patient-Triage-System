package datastructure.avlTree;

import model.Patient;

/**
 * Node dành cho cây AVL, chứa thông tin của một bệnh nhân.
 * Mỗi node tham chiếu đến node con trái (left) và node con phải (right), 
 * đồng thời lưu trữ chiều cao (height) để hỗ trợ thao tác tự cân bằng.
 */
public class PatientNode {

    private Patient patient;
    private PatientNode left;
    private PatientNode right;
    private int height;

    /**
     * Khởi tạo node mới chứa thông tin bệnh nhân.
     * 
     * @param patient Đối tượng bệnh nhân được lưu trong node
     */
    public PatientNode(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientNode getLeft() {
        return left;
    }

    public void setLeft(PatientNode left) {
        this.left = left;
    }

    public PatientNode getRight() {
        return right;
    }

    public void setRight(PatientNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
