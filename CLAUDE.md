# Smart Hospital Patient Triage System — Project Summary (CLAUDE.md)

This file serves as a comprehensive project hand-off summary for future AI developers. It contains the current architecture, build commands, implemented logic, and upcoming tasks for the CSD201 (Data Structures and Algorithms) hospital project.

---

## 🛠️ Build & Run Commands

To compile and launch the application, navigate to the project root directory and run:

### Compile All Files
```powershell
# Set console encoding to UTF-8 to prevent character corruption on Windows
[Console]::InputEncoding = [System.Text.Encoding]::UTF8
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
chcp 65001

# Compile with UTF-8 encoding flag
javac -encoding UTF-8 -d bin -sourcepath src src/Main.java
```

### Launch Console UI
```powershell
# Run with UTF-8 encoding flag
java -Dfile.encoding=UTF-8 -cp bin Main
```

---

## 📂 Project Architecture

```
Smart Hospital Patient Triage System/
├── docs/
│   ├── REPORT1_REVISED.md             # Approved Report 1 (Case Study & Design)
│   ├── CSD201_SE2039_GROUP1_REPOR2.md # Approved Report 2 (Algorithms & Pseudocode)
│   ├── CSD201_SE2039_GROUP1_REPOR3.md # Approved Report 3 (UML & Experiment Design)
│   ├── CSD201_SE2039_GROUP1_REPOR4.md # Approved Report 4 (Implementation & IEEE Paper)
│   └── CSD201_SE2039_GROUP1_REPOR5.md # Approved Report 5 (Final Slides & Reflections)
├── src/
│   ├── Main.java                # Application entry point
│   ├── model/
│   │   ├── Patient.java         # Core long-term Patient demographics
│   │   ├── MedicalRecord.java   # Represents a single exam entry (Diagnosis, Prescription, Note)
│   │   └── TriageRecord.java    # Wrapper containing Patient + severityScore + arrivalOrder (Stable Tie-breaker)
│   ├── datastructure/
│   │   ├── avlTree/
│   │   │   ├── IPatientAVLTree.java
│   │   │   ├── PatientAVLTree.java # Self-balancing tree to search Patients by ID in O(log n)
│   │   │   └── PatientNode.java
│   │   ├── queue/
│   │   │   ├── ITriageQueue.java
│   │   │   ├── PriorityQueue.java # Singly Linked List sorted queue (main emergency queue)
│   │   │   ├── QueueFIFO.java     # Singly Linked List FIFO queue (for normal patients)
│   │   │   └── QueueNode.java
│   │   ├── heap/
│   │   │   ├── IMaxHeap.java
│   │   │   └── MaxHeap.java       # Binary Max Heap (experimental emergency implementation)
│   │   └── dll/
│   │       ├── IMedicalHistory.java
│   │       ├── MedicalHistoryList.java # Doubly Linked List for chronological exam history
│   │       └── HistoryNode.java
│   ├── service/
│   │   ├── PatientService.java  # Register/search patients in AVL Tree
│   │   ├── TriageService.java   # Admit patients into PriorityQueue (Emergency) or QueueFIFO (Normal)
│   │   └── ExaminationService.java # Medical examinations adding records to individual Patient DLLs
│   ├── ui/
│   │   └── ConsoleUI.java       # Role-based English User Interface (Receptionist, Doctor, Patient)
│   └── util/
│       ├── Acceptable.java      # Validation patterns
│       ├── Inputter.java        # Helper for console input handling
│       └── BenchmarkRunner.java # Performance benchmarking utility
```

---

## 💡 Key Architectural Details

1. **Decoupled Triage Priority via `TriageRecord`:**
   * `TriageRecord` implements `Comparable<TriageRecord>`.
   * Severity scores from 2 to 5 are processed in the Emergency Priority Queue.
   * If severity scores are different, the higher score is prioritized.
   * If severity scores are equal, the smaller `arrivalOrder` (earlier arrival) is prioritized, ensuring **stable FIFO tie-breaking**.

2. **Divergent Patient Routing (Normal vs. Emergency):**
   * **Severity = 1 (Green - Non-Urgent):** Patients bypass the emergency queue and enter the **Normal Queue (QueueFIFO)**.
   * **Severity = 2-5 (Blue, Yellow, Orange, Red):** Patients enter the **Emergency Priority Queue (PriorityQueue or MaxHeap)**.

3. **Patient-Specific Doubly Linked List (Medical History):**
   * Each patient owns their own `IMedicalHistory` instance (`MedicalHistoryList`).
   * Examination data is appended to the patient's individual history in $O(1)$.
   * `IMedicalHistory` exposes `getHead()` and `getTail()` to allow traversal.
   * Interactive **Back/Next** navigation is implemented in the Patient UI, traversing `prev` and `next` pointers.

4. **Queue Interface Injectability:**
   * `TriageService` can be initialized with any `ITriageQueue` (either `PriorityQueue` or `MaxHeap`), allowing easy performance benchmarking.

---

## 🧠 Key Design & Architectural Decisions Made

1. **Role-Based Console UI:**
   * Redesigned `ConsoleUI` to present a top-level selection for three roles: **Receptionist**, **Doctor**, and **Patient**.
   * Cleanly encapsulates submenu loops for each role.

2. **Triage Severity Color-Coding:**
   * Integrates a visual Triage scale in the console when inputting severity scores (using emojis: 🔴, 🟠, 🟡, 🔵, 🟢), representing Levels 1 to 5 respectively.

3. **Interactive DLL Navigation:**
   * Replaced the simple static list printing with an interactive traversal loop for patients to go forward/backward through their chronological medical history.

4. **English Translation for Windows Encoding Fix:**
   * Translated all user-facing prompts, logs, menus, and table headers into **English** to prevent corrupted characters on standard Windows Command Prompt / PowerShell terminals.

---

## 🚀 Roadmap / Upcoming Tasks

### Step 1: Benchmark and Code verification
* [x] Create `BenchmarkRunner.java` under `src/util/` or `src/` to automate enqueuing/dequeuing for $N = 100 \dots 10,000$ to compare `PriorityQueue` (Sorted LL) vs `MaxHeap`. Collect execution times and print comparative statistics.

### Step 2: Write Report 2 (Algorithms & Code documentation)
* [x] Document pseudocode and actual Java implementations for both `PriorityQueue` and `MaxHeap`.
* [x] Document AVL Tree and Doubly Linked List implementation details.

### Step 3: Write Report 3 (Experiment Design)
* [x] Create Use Case and Sequence diagrams.
* [x] Establish the Experiment Framework (Hypotheses, Independent/Dependent variables) to answer: *"At what scale does Max Heap out-perform Sorted Linked List for emergency triage?"*

### Step 4: Write Report 4 (Experiments & IEEE Mini-Paper)
* [x] Run `BenchmarkRunner`, plot data (using Excel/Python/Matplotlib), and write a formal IEEE-style analysis describing the performance trade-offs (Sorted LL: $O(n)$ enqueue / $O(1)$ dequeue vs Max Heap: $O(\log n)$ enqueue / $O(\log n)$ dequeue).

### Step 5: Write Report 5 (Final Slides & Presentation)
* [x] Prepare final project slide deck and demo.
