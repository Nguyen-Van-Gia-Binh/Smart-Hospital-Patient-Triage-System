package ui;

import java.util.Scanner;

import model.Patient;
import service.PatientService;
import service.TriageService;
import util.Acceptable;
import util.Inputter;
import service.ExaminationService;

/**
 * Giao dien console cho he thong Smart Hospital.
 * Xu ly menu chinh va tuong tac voi nguoi dung.
 */
public class ConsoleUI {

    private Scanner scanner;
    private PatientService patientService;
    private TriageService triageService;
    private ExaminationService examinationService;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.patientService = new PatientService();
        this.triageService = new TriageService();
        this.examinationService = new ExaminationService();
    }

    /**
     * Hien thi menu chinh va xu ly lua chon cua nguoi dung.
     */
    public void start() {
        System.out.println("\n========================================");
        System.out.println("   SMART HOSPITAL PATIENT TRIAGE SYSTEM");
        System.out.println("========================================\n");

        while (true) {
            printMenu();
            System.out.print("Nhap lua chon: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[!] Vui long nhap so tu 0-4.\n");
                continue;
            }

            System.out.println(); // dong trong cho de doc

            switch (choice) {
                case 1:
                    handleRegisterPatient();
                    break;
                case 2:
                    handleEmergencyAdmission();
                    break;
                case 3:
                    handleMedicalExamination();
                    break;
                case 4:
                    handleViewPatientHistory();
                    break;
                case 0:
                    System.out.println("==> He thong da dong. Tam biet!");
                    scanner.close();
                    return;
                default:
                    System.out.println("[!] Lua chon khong hop le. Vui long chon 0-4.\n");
            }
        }
    }

    /**
     * In menu chinh.
     */
    private void printMenu() {
        System.out.println("===== SMART HOSPITAL =====");
        System.out.println("1. Register Patient");
        System.out.println("2. Emergency Admission");
        System.out.println("3. Medical Examination");
        System.out.println("4. View Patient History");
        System.out.println("0. Exit");
        System.out.println("==========================");
    }

    /**
     * Chuc nang 1: Dang ky benh nhan moi.
     */
    private void handleRegisterPatient() {
        System.out.println("--- DANG KY BENH NHAN MOI ---");
        patientService.registerPatient();
        System.out.println();
    }

    /**
     * Chuc nang 2: Tiep nhan cap cuu.
     *
     * Logic:
     *   - Yeu cau nhap Ma benh nhan.
     *   - Neu ID da ton tai: them ngay vao hang doi, roi hien danh sach thu tu uu tien.
     *   - Neu ID chua ton tai: hien thi form dang ky (ho ten, tuoi, muc do),
     *     sau do them vao hang doi, roi hien danh sach thu tu uu tien.
     */
    private void handleEmergencyAdmission() {
        System.out.println("--- TIEP NHAN CAP CUU ---");

        // Buoc 1: Nhap ma benh nhan
        String patientId = Inputter.getString("Nhap Ma benh nhan (VD: BNxxx): ", Acceptable.PATIENT_ID_VALID);

        // Buoc 2: Tim kiem trong he thong
        Patient patient = patientService.findPatientById(patientId);

        if (patient != null) {
            System.out.println("[✓] Tim thay benh nhan: " + patient.getName()
                    + " (Tuoi: " + patient.getAge() + ")");
        } else {
            // ID chua ton tai -> yeu cau dang ky them thong tin
            System.out.println("[!] Benh nhan chua co trong he thong. Vui long dang ky them thong tin:");
            patient = patientService.registerNewPatient(patientId);
        }

        // Nhap muc do nghiem trong cho lan nhap vien cap cuu nay
        int severity = Integer.parseInt(Inputter.getString("Nhap Diem muc do nghiem trong (1-5): ", "[1-5]"));
        triageService.addToEmergencyQueue(patient, severity);

        // Buoc 3: Hien thi danh sach hang doi theo thu tu uu tien
        triageService.displayQueue();
        System.out.println();
    }

    /**
     * Chuc nang 3: Kham benh - goi benh nhan uu tien cao nhat va luu benh an.
     */
    private void handleMedicalExamination() {
        System.out.println("--- KHAM BENH ---");
        Patient called = triageService.callNextPatient();
        if (called == null) {
            System.out.println("[!] Khong co benh nhan nao trong hang doi can kham.\n");
            return;
        }
        System.out.println("Dang kham benh nhan: " + called.getName()
                + " (ID: " + called.getPatientId() + ")");
        System.out.print("Nhap Chan doan: ");
        String diagnosis = scanner.nextLine().trim();
        System.out.print("Nhap Don thuoc: ");
        String prescription = scanner.nextLine().trim();
        System.out.print("Nhap Ghi chu: ");
        String note = scanner.nextLine().trim();

        examinationService.addMedicalRecord(called, diagnosis, prescription, note);
        System.out.println();
    }

    /**
     * Chuc nang 4: Xem lich su kham benh.
     */
    private void handleViewPatientHistory() {
        System.out.println("--- XEM LICH SU KHAM BENH ---");
        System.out.print("Nhap Ma benh nhan can xem lich su (VD: BNxxx): ");
        String patientId = scanner.nextLine().trim();
        Patient patient = patientService.findPatientById(patientId);
        if (patient == null) {
            System.out.println("[!] Khong tim thay benh nhan trong he thong.\n");
            return;
        }

        System.out.print("Nhap so luong benh an muon xem: ");
        int n;
        try {
            n = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[!] Vui long nhap so nguyen hop le.\n");
            return;
        }
        examinationService.showRecentRecords(patient, n);
        System.out.println();
    }

}
