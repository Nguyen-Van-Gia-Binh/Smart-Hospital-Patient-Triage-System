package util;

public interface Acceptable {

    String PATIENT_ID_VALID = "^[A-Za-z]{2}\\d{3,10}$";
    String FULL_NAME_VALID = "^[A-Za-z\\s]{2,100}$";
    public static boolean isValid(String data, String regex) {

        return (data != null && data.matches(regex));
    }

    
}
