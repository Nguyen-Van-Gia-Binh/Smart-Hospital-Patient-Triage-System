package ui;

import java.util.Scanner;

import model.Patient;
import model.Doctor;
import service.PatientService;
import service.TriageService;
import service.DoctorService;
import service.ExaminationService;

/**
 * Giao dien console cho he thong Smart Hospital.
 * Xu ly menu chinh va tuong tac voi nguoi dung.
 */
public class ConsoleUI {

    private Scanner scanner;
    private PatientService patientService;
    private TriageService triageService;
    private DoctorService doctorService;
    private ExaminationService examinationService;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.patientService = new PatientService();
        this.triageService = new TriageService();
        this.doctorService = new DoctorService();
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
                    handleDoctorAssignment();
                    break;
                case 4:
                    handleMedicalExamination();
                    break;
                case 5:
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
        System.out.println("3. Doctor Assignment");
        System.out.println("4. Medical Examination");
        System.out.println("5. View Patient History");
        System.out.println("0. Exit");
        System.out.println("==========================");
    }

    /**
     * Chuc nang 1: Dang ky benh nhan moi.
     */
    private void handleRegisterPatient() {
        System.out.println("--- DANG KY BENH NHAN MOI ---");
        System.out.print("Nhap Ma benh nhan (VD: P001): ");
        String id = scanner.nextLine().trim();
        System.out.print("Nhap Ho ten: ");
        String name = scanner.nextLine().trim();
        System.out.print("Nhap Tuoi: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhap Diem muc do nghiem trong (1-5): ");
        int severity = Integer.parseInt(scanner.nextLine().trim());

        Patient patient = new Patient(id, name, age, severity);
        patientService.registerPatient(patient);
        System.out.println();
    }

    /**
     * Chuc nang 2: Them benh nhan vao hang doi cap cuu.
     */
    private void handleEmergencyAdmission() {
        System.out.println("--- TIEP NHAN CAP CUU ---");
        System.out.print("Nhap Ma benh nhan (VD: P001): ");
        String id = scanner.nextLine().trim();
        System.out.print("Nhap Ho ten: ");
        String name = scanner.nextLine().trim();
        System.out.print("Nhap Tuoi: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhap Diem muc do nghiem trong (1-5): ");
        int severity = Integer.parseInt(scanner.nextLine().trim());

        Patient patient = new Patient(id, name, age, severity);
        patientService.registerPatient(patient);
        triageService.addToEmergencyQueue(patient);
        System.out.println();
    }

    /**
     * Chuc nang 3: Phan cong bac si cho benh nhan.
     */
    private void handleDoctorAssignment() {
        System.out.println("--- PHAN CONG BAC SI ---");
        if (!triageService.hasWaitingPatients()) {
            System.out.println("[!] Khong co benh nhan nao dang cho trong hang doi cap cuu.\n");
            return;
        }
        Patient nextPatient = triageService.callNextPatient();
        if (nextPatient != null) {
            doctorService.assignDoctor(nextPatient);
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
     * Chuc nang 5: Xem lich su kham benh.
     */
    private void handleViewPatientHistory() {
        System.out.println("--- XEM LICH SU KHAM BENH ---");
        System.out.print("Nhap so luong benh an muon xem: ");
        int n = Integer.parseInt(scanner.nextLine().trim());
        examinationService.showRecentRecords(n);
        System.out.println();
    }

}
