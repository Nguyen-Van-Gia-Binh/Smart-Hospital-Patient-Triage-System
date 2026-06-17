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
                System.out.println("[!] Vui long nhap so tu 0-5.\n");
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
                    System.out.println("[!] Lua chon khong hop le. Vui long chon 0-5.\n");
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

    // sub-menu sau khi bấm 2 tìm thấy bệnh nhân thì hiện ra submenu này bao gồm xem
    // lịch sử khám bệnh và nút khám bệnh
    private void printPatientSubMenu() {
        System.out.println("=== PATIENT OPTIONS ===");
        System.out.println("1. View Medical History");
        System.out.println("2. Proceed to Medical Examination");
        System.out.println("=======================");
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
     * Chuc nang 2: Them benh nhan vao hang doi cap cuu.
     */
    private void handleEmergencyAdmission() {
        System.out.println("--- TIEP NHAN CAP CUU ---");
        Patient patient = patientService.findPatient();
        if (patient != null) {
            triageService.addToEmergencyQueue(patient);
            printPatientSubMenu();
            while (true) {
                int subChoice = Inputter.getInt("Nhap so 1-2 de chon: ", 1, 2);
                switch (subChoice) {
                    case 1:
                        handleViewPatientHistory();
                        break;
                    case 2:
                        System.out.println("--- KHAM BENH ---");
                        triageService.callNextPatient();
                        handleMedicalExamination();
                        break;
                    default:
                        System.out.println("[!] Lua chon khong hop le. Vui long chon 1-2.\n");
                }
            }
        } else {
            System.out.println(
                    "[!] Khong tim thay benh nhan trong he thong. Vui long dang ky truoc khi tiep nhan cap cuu.");
        }
        System.out.println();
    }

    /**
     * Chuc nang 4: Kham benh va luu benh an.
     */
    private void handleMedicalExamination() {
        System.out.println("--- KHAM BENH ---");
        System.out.print("Nhap Chan doan: ");
        String diagnosis = scanner.nextLine().trim();
        System.out.print("Nhap Don thuoc: ");
        String prescription = scanner.nextLine().trim();
        System.out.print("Nhap Ghi chu: ");
        String note = scanner.nextLine().trim();

        examinationService.addMedicalRecord(diagnosis, prescription, note);
        System.out.println();
    }

    /**
     * Chuc nang 4: Xem lich su kham benh.
     */
    private void handleViewPatientHistory() {
        System.out.println("--- XEM LICH SU KHAM BENH ---");
        System.out.print("Nhap so luong benh an muon xem: ");
        int n = Integer.parseInt(scanner.nextLine().trim());
        examinationService.showRecentRecords(n);
        System.out.println();
    }

}
