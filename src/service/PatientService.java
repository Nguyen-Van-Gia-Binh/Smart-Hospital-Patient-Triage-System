package service;

import datastructure.avlTree.IPatientAVLTree;
import datastructure.avlTree.PatientAVLTree;
import model.Patient;
import util.Acceptable;
import util.Inputter;

/**
 * Lớp dịch vụ (Service) xử lý các nghiệp vụ liên quan đến quản lý bệnh nhân.
 * Sử dụng cấu trúc cây IPatientAVLTree để lưu trữ và tra cứu thông tin nhanh chóng.
 */
public class PatientService {

    private IPatientAVLTree patientAVLTree;

    /**
     * Khởi tạo dịch vụ quản lý bệnh nhân với một cây AVL rỗng.
     */
    public PatientService() {
        this.patientAVLTree = new PatientAVLTree();
    }

    /**
     * Đăng ký một bệnh nhân mới vào hệ thống (yêu cầu nhập ID từ bàn phím).
     */
    public void registerPatient() {
        String id;
        do {
            id = Inputter.getString("Enter Patient ID (e.g., BNxxx): ", Acceptable.PATIENT_ID_VALID);
            Patient existingPatient = patientAVLTree.search(id);
            if (existingPatient != null) {
                System.out.println("[!] Patient ID already exists. Please try again.");
            }
        } while (patientAVLTree.search(id) != null);

        Patient patient = collectPatientInfo(id);
        patientAVLTree.insert(patient);
        System.out.println("[+] Registered patient in the system (AVL Tree): " + patient.getName()
                + " (ID: " + patient.getPatientId() + ")");
    }

    /**
     * Đăng ký một bệnh nhân mới với mã ID đã được xác thực trước.
     * Thường được sử dụng trong luồng Cấp cứu khi ID chưa tồn tại trên hệ thống.
     * 
     * @param id Mã bệnh nhân đã được xác thực
     * @return Đối tượng Patient vừa được đăng ký
     */
    public Patient registerNewPatient(String id) {
        Patient patient = collectPatientInfo(id);
        patientAVLTree.insert(patient);
        System.out.println("[+] Registered patient in the system (AVL Tree): " + patient.getName()
                + " (ID: " + patient.getPatientId() + ")");
        return patient;
    }

    /**
     * Thu thập thông tin nhân khẩu học của bệnh nhân từ giao diện console.
     * 
     * @param id Mã bệnh nhân
     * @return Đối tượng Patient với các thông tin vừa nhập
     */
    private Patient collectPatientInfo(String id) {
        String name = Inputter.getString("Enter Full Name: ", Acceptable.FULL_NAME_VALID);
        int age = Integer.parseInt(Inputter.getString("Enter Age: ", "\\d+"));
        Patient patient = new Patient(id, name, age);
        return patient;
    }

    /**
     * Tìm kiếm thông tin bệnh nhân trong hệ thống bằng cách yêu cầu người dùng nhập mã ID.
     * 
     * @return Đối tượng Patient nếu tìm thấy, ngược lại trả về null
     */
    public Patient findPatient() {
        String patientId = Inputter.getString("Enter Patient ID: ", Acceptable.PATIENT_ID_VALID);
        Patient found = patientAVLTree.search(patientId);
        if (found == null) {
            return null;
        }
        return found;
    }

    /**
     * Tìm kiếm thông tin bệnh nhân thông qua mã ID cung cấp sẵn.
     * 
     * @param patientId Mã bệnh nhân cần tìm
     * @return Đối tượng Patient nếu tìm thấy, ngược lại trả về null
     */
    public Patient findPatientById(String patientId) {
        return patientAVLTree.search(patientId);
    }

    /**
     * Xóa thông tin bệnh nhân khỏi hệ thống.
     * 
     * @param patientId Mã bệnh nhân cần xóa
     * @return true nếu xóa thành công, false nếu không tìm thấy bệnh nhân
     */
    public boolean removePatient(String patientId) {
        boolean result = patientAVLTree.delete(patientId);
        if (result) {
            System.out.println("[x] Deleted Patient ID: " + patientId);
        } else {
            System.out.println("[!] Patient ID not found for deletion: " + patientId);
        }
        return result;
    }

}
