package util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Lớp tiện ích hỗ trợ người dùng nhập liệu từ bàn phím với các kiểm tra hợp lệ.
 */
public class Inputter {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Nhập một chuỗi ký tự và kiểm tra tính hợp lệ dựa trên biểu thức chính quy
     * (regex).
     *
     * @param msg   Thông điệp yêu cầu nhập dữ liệu hiển thị cho người dùng
     * @param regex Biểu thức chính quy dùng để kiểm tra tính hợp lệ
     * @return Chuỗi dữ liệu hợp lệ được nhập từ bàn phím
     */
    public static String getString(String msg, String regex) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.err.println("Input cannot be empty. Please try again:");
                continue;
            }

            if (Acceptable.isValid(input, regex)) {
                return input;
            } else {
                System.err.println("Invalid input. Please try again:");
            }
        }
    }

    /**
     * Nhập một số nguyên nằm trong một khoảng cho trước (từ min đến max).
     *
     * @param msg Thông điệp yêu cầu nhập dữ liệu hiển thị cho người dùng
     * @param min Giá trị nhỏ nhất được phép nhập (bao gồm cả min)
     * @param max Giá trị lớn nhất được phép nhập (bao gồm cả max)
     * @return Số nguyên hợp lệ nằm trong khoảng [min, max]
     */
    public static int getInt(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            try {
                int result = Integer.parseInt(input);
                if (result >= min && result <= max) {
                    return result;
                } else {
                    System.err.println("Please enter a number in range [" + min + " - " + max + "]:");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid number. Please enter an integer:");
            }
        }
    }

    /**
     * Nhập một số thực nằm trong một khoảng cho trước (từ min đến max).
     *
     * @param msg Thông điệp yêu cầu nhập dữ liệu hiển thị cho người dùng
     * @param min Giá trị nhỏ nhất được phép nhập (bao gồm cả min)
     * @param max Giá trị lớn nhất được phép nhập (bao gồm cả max)
     * @return Số thực hợp lệ nằm trong khoảng [min, max]
     */
    public static double getDouble(String msg, double min, double max) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            try {
                double result = Double.parseDouble(input);
                if (result >= min && result <= max) {
                    return result;
                } else {
                    System.err.println("Please enter a number in range [" + min + " - " + max + "]:");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid number. Please enter a valid number:");
            }
        }
    }

    /**
     * Nhập giá trị xác nhận Yes/No (Y/N).
     *
     * @param msg Thông điệp yêu cầu xác nhận hiển thị cho người dùng
     * @return true nếu người dùng nhập 'Y' hoặc 'y', false nếu nhập 'N' hoặc 'n'
     */
    public static boolean getYesNo(String msg) {
        while (true) {
            System.out.print(msg);
            String result = sc.nextLine().trim();

            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Invalid input! Please enter 'Y' or 'N'.");
            }
        }
    }

    /**
     * Nhập một chuỗi ký tự, cho phép để trống, nếu nhập sẽ được kiểm tra theo biểu
     * thức chính quy.
     *
     * @param msg   Thông điệp yêu cầu nhập dữ liệu hiển thị cho người dùng
     * @param regex Biểu thức chính quy dùng để kiểm tra tính hợp lệ nếu người dùng
     *              có nhập
     * @return Chuỗi dữ liệu hợp lệ hoặc null nếu người dùng để trống
     */
    public static String getStringEmpty(String msg, String regex) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                return null;
            }

            if (Acceptable.isValid(input, regex)) {
                return input;
            } else {
                System.err.print("Input not valid. Please re-input: ");
            }
        }
    }

    /**
     * Nhập vào ngày tháng theo định dạng dd/MM/yyyy.
     *
     * @param msg        Thông điệp yêu cầu nhập dữ liệu hiển thị cho người dùng
     * @param allowBlank Xác định có cho phép người dùng bỏ trống (trả về null) hay
     *                   không
     * @return Đối tượng Date hợp lệ hoặc null nếu cho phép bỏ trống
     */
    public static Date getDate(String msg, boolean allowBlank) {
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        s.setLenient(false);
        System.out.print(msg + " (dd/MM/yyyy): ");
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (allowBlank && input.isEmpty()) {
                    return null;
                }
                if (input.length() != 10) {
                    System.out.print("Invalid format! Please enter again (dd/MM/yyyy): ");
                    continue;
                }
                Date date = s.parse(input);
                return date;
            } catch (ParseException e) {
                System.out.print("Invalid date! Please enter again (dd/MM/yyyy): ");
            }
        }
    }

    /**
     * Nhập vào ngày tháng (LocalDate) theo định dạng dd/MM/yyyy.
     *
     * @param msg        Thông điệp yêu cầu nhập dữ liệu hiển thị cho người dùng
     * @param allowBlank Xác định có cho phép người dùng bỏ trống (trả về null) hay
     *                   không
     * @return Đối tượng LocalDate hợp lệ hoặc null nếu cho phép bỏ trống
     */
    public static LocalDate getLocalDate(String msg, boolean allowBlank) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.print(msg + " (dd/MM/yyyy): ");
        while (true) {
            try {
                String input = sc.nextLine().trim();

                if (allowBlank && input.isEmpty()) {
                    return null;
                }

                if (input.length() != 10) {
                    System.out.print("Invalid format! Please enter again (dd/MM/yyyy): ");
                    continue;
                }

                LocalDate date = LocalDate.parse(input, formatter);

                return date;
            } catch (DateTimeParseException e) {
                System.out.print("Invalid date! Please enter again (dd/MM/yyyy): ");
            }
        }
    }
}
