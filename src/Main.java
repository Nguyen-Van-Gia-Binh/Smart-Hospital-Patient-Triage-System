import model.Patient;
import service.PatientService;
import service.TriageService;
import ui.ConsoleUI;

/**
 * Diem vao chinh cua he thong Smart Hospital Patient Triage.
 * Khoi tao mock data va chay ConsoleUI.
 */
public class Main {

    public static void main(String[] args) {
        // System.out.println("==============================================");
        // System.out.println(" KHOI DONG HE THONG SMART HOSPITAL TRIAGE");
        // System.out.println("==============================================\n");

        // // === KHOI TAO MOCK DATA ===

        // // --- Mock Patients ---
        // Patient patient1 = new Patient("P001", "Pham Minh Tuan", 30, 3);
        // Patient patient2 = new Patient("P002", "Vo Thi Hoa", 45, 5);
        // Patient patient3 = new Patient("P003", "Dang Quoc Viet", 22, 1);

        // System.out.println("\n[Mock Data] Da tao 3 benh nhan:");
        // System.out.println(" " + patient1);
        // System.out.println(" " + patient2);
        // System.out.println(" " + patient3);

        // PatientService patientService = new PatientService();

        // TriageService triageService = new TriageService();
        // triageService.addToEmergencyQueue(patient1);
        // triageService.addToEmergencyQueue(patient2);
        // triageService.addToEmergencyQueue(patient3);

        // System.out.println("\n[Mock Data] Da nap xong du lieu mau vao he thong.");
        // System.out.println("* Luu y: Cac data structure chua duoc implement.");
        // System.out.println("* Khi cac member hoan thanh, he thong se tu dong hoat
        // dong.\n");

        // === CHAY GIAO DIEN CONSOLE ===
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();
    }

}
