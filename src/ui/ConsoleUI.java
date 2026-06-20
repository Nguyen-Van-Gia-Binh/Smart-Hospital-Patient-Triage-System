package ui;

import java.util.Scanner;
import model.Patient;
import datastructure.dll.HistoryNode;
import service.PatientService;
import service.TriageService;
import service.ExaminationService;
import util.Acceptable;
import util.Inputter;

/**
 * Giao diện người dùng dạng console cho hệ thống Smart Hospital.
 * Hỗ trợ quyền truy cập theo vai trò: Lễ tân (Receptionist), Bác sĩ (Doctor), và Bệnh nhân (Patient).
 */
public class ConsoleUI {

    private Scanner scanner;
    private PatientService patientService;
    private TriageService triageService;
    private ExaminationService examinationService;

    /**
     * Khởi tạo giao diện console và các dịch vụ đi kèm.
     */
    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.patientService = new PatientService();
        this.triageService = new TriageService();
        this.examinationService = new ExaminationService();
    }

    /**
     * Hiển thị menu chính để người dùng chọn vai trò.
     */
    public void start() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   SMART HOSPITAL PATIENT TRIAGE SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Receptionist Interface");
            System.out.println("2. Doctor Interface");
            System.out.println("3. Patient Interface");
            System.out.println("0. Exit Program");
            System.out.println("========================================");
            System.out.print("Enter role choice: ");

            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                handleReceptionistMenu();
            } else if (input.equals("2")) {
                handleDoctorMenu();
            } else if (input.equals("3")) {
                handlePatientMenu();
            } else if (input.equals("0")) {
                System.out.println("==> System closed. Goodbye!");
                scanner.close();
                return;
            } else {
                System.out.println("[!] Invalid choice. Please choose from 0-3.\n");
            }
        }
    }

    /**
     * 1. GIAO DIỆN LỄ TÂN (RECEPTIONIST INTERFACE)
     */
    private void handleReceptionistMenu() {
        while (true) {
            System.out.println("\n--- RECEPTIONIST INTERFACE ---");
            System.out.println("1. Register new patient (Add to AVL Tree)");
            System.out.println("2. Register admission / triage");
            System.out.println("0. Back to main menu");
            System.out.println("------------------------------------");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                System.out.println("\n--- REGISTER NEW PATIENT ---");
                patientService.registerPatient();
            } else if (choice.equals("2")) {
                handleAdmission();
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("[!] Invalid choice. Please choose again.");
            }
        }
    }

    /**
     * Xử lý luồng đăng ký nhập viện và phân luồng (triage) cho bệnh nhân.
     */
    private void handleAdmission() {
        System.out.println("\n--- PATIENT ADMISSION / TRIAGE ---");
        String patientId = Inputter.getString("Enter Patient ID (e.g., BNxxx): ", Acceptable.PATIENT_ID_VALID);
        Patient patient = patientService.findPatientById(patientId);

        if (patient != null) {
            System.out.println("[✓] Patient found: " + patient.getName()
                    + " (Age: " + patient.getAge() + ")");
        } else {
            System.out.println("[!] Patient not registered in the system. Registering details first:");
            patient = patientService.registerNewPatient(patientId);
        }

        // Hiển thị thang điểm phân luồng cấp cứu
        printTriageScale();
        int severity = Integer.parseInt(Inputter.getString("Enter urgency score (1-5): ", "[1-5]"));
        triageService.addToEmergencyQueue(patient, severity);

        // Hiển thị hàng đợi tương ứng sau khi thêm bệnh nhân
        if (severity == 1) {
            triageService.displayNormalQueue();
        } else {
            triageService.displayQueue();
        }
    }

    /**
     * 2. GIAO DIỆN BÁC SĨ (DOCTOR INTERFACE)
     */
    private void handleDoctorMenu() {
        while (true) {
            System.out.println("\n--- DOCTOR INTERFACE ---");
            System.out.println("1. Perform emergency triage (Call most critical patient)");
            System.out.println("2. Perform normal examination (Call patient via FIFO)");
            System.out.println("0. Back to main menu");
            System.out.println("------------------------");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                handleEmergencyExam();
            } else if (choice.equals("2")) {
                handleNormalExam();
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("[!] Invalid choice. Please choose again.");
            }
        }
    }

    /**
     * Khám bệnh cấp cứu (sử dụng Priority Queue).
     */
    private void handleEmergencyExam() {
        System.out.println("\n--- EMERGENCY ADMISSION IN PROGRESS ---");
        Patient called = triageService.callNextPatient();
        if (called == null) {
            System.out.println("[!] No patients waiting in the Emergency Queue.\n");
            return;
        }
        examinePatient(called);
    }

    /**
     * Khám bệnh thông thường (sử dụng Queue FIFO).
     */
    private void handleNormalExam() {
        System.out.println("\n--- NORMAL EXAMINATION IN PROGRESS ---");
        Patient called = triageService.callNextNormalPatient();
        if (called == null) {
            System.out.println("[!] No patients waiting in the Normal Queue.\n");
            return;
        }
        examinePatient(called);
    }

    /**
     * Luồng thực hiện khám bệnh cho bệnh nhân vừa được gọi.
     * 
     * @param patient Bệnh nhân đang được khám
     */
    private void examinePatient(Patient patient) {
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println(" Examining Patient: " + patient.getName().toUpperCase()
                    + " (ID: " + patient.getPatientId() + ", Age: " + patient.getAge() + ")");
            System.out.println("----------------------------------------");
            System.out.println("1. View medical history");
            System.out.println("2. Perform examination & input results");
            System.out.println("0. Finish examination (Back to doctor menu)");
            System.out.println("----------------------------------------");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                System.out.print("Enter number of recent records to view: ");
                int n;
                try {
                    n = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("[!] Please enter a valid integer.\n");
                    continue;
                }
                examinationService.showRecentRecords(patient, n);
            } else if (choice.equals("2")) {
                System.out.print("Enter Diagnosis: ");
                String diagnosis = scanner.nextLine().trim();
                System.out.print("Enter Prescription: ");
                String prescription = scanner.nextLine().trim();
                System.out.print("Enter Note: ");
                String note = scanner.nextLine().trim();

                examinationService.addMedicalRecord(patient, diagnosis, prescription, note);

                // Hỏi ý kiến bác sĩ xem có cần phân luồng lại hay không
                System.out.print("\nDoes the patient need emergency triage or to queue again? (y/n): ");
                String reTriage = scanner.nextLine().trim().toLowerCase();
                if (reTriage.equals("y") || reTriage.equals("yes")) {
                    printTriageScale();
                    String inputSev = Inputter.getString("Enter new urgency score (1-5): ", "[1-5]");
                    int severity = Integer.parseInt(inputSev);
                    triageService.addToEmergencyQueue(patient, severity);
                }
                System.out.println("[✓] Examination completed and record saved.");
                return;
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("[!] Invalid choice.");
            }
        }
    }

    /**
     * 3. GIAO DIỆN BỆNH NHÂN (PATIENT INTERFACE)
     */
    private void handlePatientMenu() {
        System.out.println("\n--- PATIENT INTERFACE ---");
        String patientId = Inputter.getString("Enter your Patient ID (e.g., BNxxx): ", Acceptable.PATIENT_ID_VALID);
        Patient patient = patientService.findPatientById(patientId);

        if (patient == null) {
            System.out.println("[!] No patient found with ID: " + patientId);
            return;
        }

        while (true) {
            System.out.println("\n--- PATIENT MENU: " + patient.getName().toUpperCase() + " ---");
            System.out.println("1. View personal info");
            System.out.println("2. View medical history (Interactive traversal)");
            System.out.println("0. Back to main menu");
            System.out.println("----------------------------------------------");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                System.out.println("\n=== PERSONAL INFORMATION ===");
                System.out.println("Patient ID: " + patient.getPatientId());
                System.out.println("Full Name : " + patient.getName());
                System.out.println("Age       : " + patient.getAge());
                System.out.println("=========================");
            } else if (choice.equals("2")) {
                navigateMedicalHistory(patient);
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("[!] Invalid choice.");
            }
        }
    }

    /**
     * Duyệt tương tác qua lịch sử khám bệnh (danh sách liên kết đôi) của bệnh nhân.
     * 
     * @param patient Bệnh nhân cần xem lịch sử
     */
    private void navigateMedicalHistory(Patient patient) {
        HistoryNode current = patient.getMedicalHistory().getTail(); // Mặc định bắt đầu từ bệnh án mới nhất (tail)
        if (current == null) {
            System.out.println("\n[!] You do not have any medical records yet.");
            return;
        }

        while (true) {
            System.out.println("\n=============== MEDICAL RECORD DETAILS ===============");
            System.out.println(current.getData());
            System.out.println("======================================================");
            System.out.println("[1] View older record (Back) | [2] View newer record (Next) | [0] Back");
            System.out.print("Enter navigation choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                if (current.getPrev() != null) {
                    current = current.getPrev();
                } else {
                    System.out.println("\n[!] This is already your first record (Oldest).");
                }
            } else if (choice.equals("2")) {
                if (current.getNext() != null) {
                    current = current.getNext();
                } else {
                    System.out.println("\n[!] This is already your newest record.");
                }
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("[!] Invalid choice. Please enter 1, 2 or 0.");
            }
        }
    }

    /**
     * Hiển thị bảng thang điểm phân luồng cấp cứu theo chuẩn y tế.
     */
    public static void printTriageScale() {
        System.out.println("\n=== TRIAGE EMERGENCY SCALE ===");
        System.out.println(" [5] Level 1  Critical (Immediate action required)");
        System.out
                .println("    - Cardiac/respiratory arrest, airway obstruction, severe respiratory distress, shock...");
        System.out.println(" [4] Level 2  Very Urgent (Assessment & treatment <= 10 mins)");
        System.out.println("    - Airway risk, SpO2 < 90%, circulatory shock, chest pain, acute stroke...");
        System.out.println(" [3] Level 3  Urgent (Assessment & treatment <= 30 mins)");
        System.out.println(
                "    - Severe hypertension, moderate breathing difficulty (SpO2 90-95%), fever in immunocompromised...");
        System.out.println(" [2] Level 4  Less Urgent (Assessment & treatment <= 45 mins)");
        System.out.println("    - Mild bleeding, stable foreign body, minor trauma, vomiting/diarrhea...");
        System.out.println(" [1] Level 5  Non-Urgent (Normal - Assessment & treatment <= 60 mins)");
        System.out.println("    - Mild pain with no risk signs, minor abrasions, stable chronic illness...");
        System.out.println("================================================");
    }
}
