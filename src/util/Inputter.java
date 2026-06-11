/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author as
 */
public class Inputter {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Phương thức nhập chuỗi có kiểm tra theo regex
     *
     * @param msg   Chuỗi hiển thị khi yêu cầu nhập dữ liệu
     * @param regex Biểu thức chính quy để kiểm tra đầu vào
     * @return Chuỗi hợp lệ sau khi kiểm tra
     */
    public static String getString(String msg, String regex) {
        while (true) { // Vòng lặp chạy cho đến khi nhập đúng định dạng
            System.out.print(msg); // Hiển thị thông báo nhập
            String input = sc.nextLine().trim(); // Nhập dữ liệu và loại bỏ khoảng trắng đầu/cuối

            if (input.isEmpty()) { // Kiểm tra nếu chuỗi rỗng
                System.err.println("Input cannot be empty. Please try again:");
                continue; // Bắt nhập lại
            }

            if (Acceptable.isValid(input, regex)) { // Kiểm tra chuỗi có đúng định dạng không
                return input; // Trả về chuỗi hợp lệ
            } else {
                System.err.println("Invalid input. Please try again:"); // Báo lỗi và nhập lại
            }
        }
    }

    /**
     * Phương thức nhập số nguyên trong khoảng min - max
     *
     * @param msg Chuỗi hiển thị khi nhập dữ liệu
     * @param min Giá trị nhỏ nhất có thể nhập
     * @param max Giá trị lớn nhất có thể nhập
     * @return Số nguyên hợp lệ
     */
    public static int getInt(String msg, int min, int max) {
        while (true) { // Vòng lặp kiểm tra điều kiện nhập
            System.out.print(msg); // Hiển thị thông báo nhập
            String input = sc.nextLine().trim(); // Nhập dữ liệu và loại bỏ khoảng trắng

            try {
                int result = Integer.parseInt(input); // Chuyển đổi chuỗi thành số nguyên
                if (result >= min && result <= max) { // Kiểm tra xem số có trong khoảng không
                    return result; // Trả về số hợp lệ
                } else {
                    System.err.println("Please enter a number in range [" + min + " - " + max + "]:");
                }
            } catch (NumberFormatException e) { // Bắt lỗi nếu nhập không phải số nguyên
                System.err.println("Invalid number. Please enter an integer:");
            }
        }
    }

    /**
     * Phương thức nhập số thực (double) trong khoảng min - max
     *
     * @param msg Chuỗi hiển thị khi nhập dữ liệu
     * @param min Giá trị nhỏ nhất có thể nhập
     * @param max Giá trị lớn nhất có thể nhập
     * @return Số thực hợp lệ
     */
    public static double getDouble(String msg, double min, double max) {
        while (true) { // Vòng lặp kiểm tra điều kiện nhập
            System.out.print(msg); // Hiển thị thông báo nhập
            String input = sc.nextLine().trim(); // Nhập dữ liệu và loại bỏ khoảng trắng

            try {
                double result = Double.parseDouble(input); // Chuyển đổi chuỗi thành số thực
                if (result >= min && result <= max) { // Kiểm tra xem số có trong khoảng không
                    return result; // Trả về số hợp lệ
                } else {
                    System.err.println("Please enter a number in range [" + min + " - " + max + "]:");
                }
            } catch (NumberFormatException e) { // Bắt lỗi nếu nhập không phải số thực
                System.err.println("Invalid number. Please enter a valid number:");
            }
        }
    }

    /**
     * Phương thức nhập vào Y/N (Yes/No)
     *
     * @param msg Chuỗi hiển thị khi nhập dữ liệu
     * @return true nếu nhập "Y", false nếu nhập "N"
     */
    public static boolean getYesNo(String msg) {
        while (true) { // Vòng lặp kiểm tra điều kiện nhập
            System.out.print(msg); // Hiển thị thông báo nhập
            String result = sc.nextLine().trim(); // Nhập dữ liệu và loại bỏ khoảng trắng

            if (result.equalsIgnoreCase("Y")) { // Nếu nhập 'Y' hoặc 'y'
                return true; // Trả về true
            } else if (result.equalsIgnoreCase("N")) { // Nếu nhập 'N' hoặc 'n'
                return false; // Trả về false
            } else {
                System.err.println("Invalid input! Please enter 'Y' or 'N'."); // Thông báo lỗi
            }
        }
    }

    /**
     * Phương thức nhập chuỗi có thể rỗng và kiểm tra theo regex
     *
     * @param msg   Chuỗi hiển thị khi nhập dữ liệu
     * @param regex Biểu thức chính quy để kiểm tra đầu vào
     * @return Chuỗi hợp lệ hoặc null nếu nhập rỗng
     */
    public static String getStringEmpty(String msg, String regex) {
        while (true) { // Vòng lặp kiểm tra điều kiện nhập
            System.out.print(msg); // Hiển thị thông báo nhập
            String input = sc.nextLine().trim(); // Nhập dữ liệu và loại bỏ khoảng trắng

            if (input.isEmpty()) { // Nếu nhập rỗng, trả về null
                return null;
            }

            if (Acceptable.isValid(input, regex)) { // Kiểm tra chuỗi theo regex
                return input; // Trả về chuỗi hợp lệ
            } else {
                System.err.print("Input not valid. Please re-input: "); // Thông báo lỗi
            }
        }
    }

    /**
     * Phương thức nhập ngày tháng theo định dạng dd/MM/yyyy, có thể nhập rỗng
     *
     * @param msg        Chuỗi hiển thị khi nhập dữ liệu
     * @param allowBlank Cho phép giá trị rỗng hay không
     * @return Date hợp lệ hoặc rỗng nếu cho phép
     */
    public static Date getDate(String msg, boolean allowBlank) {
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        s.setLenient(false);
        // Date today = new Date(); // Ngày hiện tại (lúc chạy chương trình)
        System.out.print(msg + "must be in the future): ");
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
                // if (!date.after(today)) {
                // System.out.print("Date must be in the future! Please enter again
                // (dd/MM/yyyy): ");
                // continue;
                // }
                return date;

            } catch (ParseException e) {
                System.out.print("Invalid date! Please enter again (dd/MM/yyyy): ");
            }

        }
    }

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

                // Nếu muốn bắt buộc phải là ngày tương lai:
                // if (!date.isAfter(LocalDate.now())) {
                // System.out.print("Date must be in the future! Enter again: ");
                // continue;
                // }

                return date;

            } catch (DateTimeParseException e) {
                System.out.print("Invalid date! Please enter again (dd/MM/yyyy): ");
            }
        }
    }
}
