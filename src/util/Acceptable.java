package util;

/**
 * Interface chứa các hằng số Regex và phương thức kiểm tra tính hợp lệ của chuỗi.
 */
public interface Acceptable {

    /** Regex kiểm tra mã bệnh nhân hợp lệ (bắt đầu bằng B hoặc N, theo sau là 3 đến 10 chữ số) */
    String PATIENT_ID_VALID = "^[BN]{2}\\d{3,10}$";
    
    /** Regex kiểm tra họ tên hợp lệ (chỉ chứa chữ cái và khoảng trắng, độ dài từ 2 đến 100) */
    String FULL_NAME_VALID = "^[A-Za-z\\s]{2,100}$";

    /**
     * Kiểm tra một chuỗi có khớp với biểu thức chính quy (regex) hay không.
     *
     * @param data Chuỗi cần kiểm tra
     * @param regex Biểu thức chính quy dùng để đối chiếu
     * @return true nếu chuỗi hợp lệ và khớp với regex, ngược lại trả về false
     */
    public static boolean isValid(String data, String regex) {
        return (data != null && data.matches(regex));
    }
}
