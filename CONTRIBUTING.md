# 📋 Hướng Dẫn Phát Triển & Đóng Góp

> **Smart Hospital Patient Triage System**
> Tài liệu dành cho tất cả thành viên trong nhóm.

---

## 📑 Mục Lục

1. [Tổng Quan Dự Án](#1-tổng-quan-dự-án)
2. [Cài Đặt Môi Trường](#2-cài-đặt-môi-trường)
3. [Cấu Trúc Dự Án](#3-cấu-trúc-dự-án)
4. [Kiến Trúc & Luồng Hoạt Động](#4-kiến-trúc--luồng-hoạt-động)
5. [Phân Công Nhiệm Vụ](#5-phân-công-nhiệm-vụ)
6. [Hướng Dẫn Git Workflow](#6-hướng-dẫn-git-workflow)
7. [Quy Tắc Viết Code](#7-quy-tắc-viết-code)
8. [Hướng Dẫn Implement Chi Tiết](#8-hướng-dẫn-implement-chi-tiết)
9. [Cách Test Riêng Phần Của Mình](#9-cách-test-riêng-phần-của-mình)
10. [Ghép Code & Tích Hợp](#10-ghép-code--tích-hợp)
11. [Xử Lý Lỗi Thường Gặp](#11-xử-lý-lỗi-thường-gặp)
12. [Liên Hệ & Hỗ Trợ](#12-liên-hệ--hỗ-trợ)

---

## 1. Tổng Quan Dự Án

**Smart Hospital Patient Triage System** là hệ thống phân luồng bệnh nhân thông minh tại bệnh viện, sử dụng các cấu trúc dữ liệu cơ bản:

| Cấu trúc dữ liệu | Mục đích |
|---|---|
| **BST** (Binary Search Tree) | Lưu trữ & tra cứu bệnh nhân theo ID |
| **Max-Heap** | Hàng đợi ưu tiên cấp cứu (bệnh nhân nặng nhất được gọi trước) |
| **Queue** (FIFO) | Hàng đợi bác sĩ sẵn sàng tiếp nhận |
| **Doubly Linked List** | Lưu trữ lịch sử khám bệnh (duyệt tiến/lùi) |

### Nguyên tắc thiết kế

- **Interface-first**: Mọi cấu trúc dữ liệu đều có interface định nghĩa sẵn
- **Loose coupling**: Service layer chỉ phụ thuộc vào interface, không phụ thuộc implementation
- **Parallel development**: 4 người code song song mà không đụng nhau

---

## 2. Cài Đặt Môi Trường

### Yêu cầu

- **Java**: JDK 8 trở lên
- **Git**: Phiên bản mới nhất
- **IDE**: IntelliJ IDEA, Eclipse, hoặc VS Code (với Extension Pack for Java)

### Clone dự án

```bash
git clone https://github.com/Nguyen-Van-Gia-Binh/Smart-Hospital-Patient-Triage-System.git
cd Smart-Hospital-Patient-Triage-System
```

### Biên dịch & chạy

```bash
# Di chuyển vào thư mục src
cd src

# Biên dịch tất cả file Java
javac -d ../out Main.java model/*.java datastructure/**/*.java service/*.java ui/*.java

# Chạy chương trình
java -cp ../out Main
```

> **Lưu ý**: Khi các data structure chưa được implement, chương trình sẽ throw `UnsupportedOperationException` ở các chức năng liên quan. Đây là hành vi bình thường.

---

## 3. Cấu Trúc Dự Án

```
src/
├── Main.java                          # Entry point + Mock Data
│
├── model/                             # Các class mô hình dữ liệu
│   ├── Patient.java                   #   Bệnh nhân
│   ├── Doctor.java                    #   Bác sĩ
│   └── MedicalRecord.java            #   Bệnh án
│
├── datastructure/                     # Các cấu trúc dữ liệu
│   ├── bst/                           #   Cây tìm kiếm nhị phân
│   │   ├── IPatientBST.java          #     Interface (KHÔNG SỬA)
│   │   ├── PatientNode.java          #     Node - Member 1
│   │   └── PatientBST.java           #     Logic - Member 1
│   │
│   ├── heap/                          #   Heap ưu tiên
│   │   ├── IMaxHeap.java             #     Interface (KHÔNG SỬA)
│   │   ├── HeapNode.java             #     Node - Member 2
│   │   └── MaxHeap.java              #     Logic - Member 2
│   │
│   ├── queue/                         #   Hàng đợi bác sĩ
│   │   ├── IDoctorQueue.java         #     Interface (KHÔNG SỬA)
│   │   ├── DoctorNode.java           #     Node - Member 3
│   │   └── DoctorQueue.java          #     Logic - Member 3
│   │
│   └── dll/                           #   Danh sách liên kết đôi
│       ├── IMedicalHistory.java      #     Interface (KHÔNG SỬA)
│       ├── HistoryNode.java          #     Node - Member 4
│       └── MedicalHistoryList.java   #     Logic - Member 4
│
├── service/                           # Tầng nghiệp vụ (ĐÃ CODE SẴN)
│   ├── PatientService.java           #   Quản lý bệnh nhân → IPatientBST
│   ├── TriageService.java            #   Phân luồng cấp cứu → IMaxHeap
│   ├── DoctorService.java            #   Quản lý bác sĩ → IDoctorQueue
│   └── ExaminationService.java       #   Khám bệnh → IMedicalHistory
│
└── ui/                                # Giao diện console (ĐÃ CODE SẴN)
    └── ConsoleUI.java                #   Menu chính
```

---

## 4. Kiến Trúc & Luồng Hoạt Động

```
┌──────────────┐
│   Main.java  │  ← Mock Data + Khởi chạy
└──────┬───────┘
       │
       ▼
┌──────────────┐
│  ConsoleUI   │  ← Nhận input từ người dùng
└──────┬───────┘
       │
       ▼
┌──────────────────────────────────────────────┐
│              SERVICE LAYER                    │
│                                              │
│  PatientService ──→ IPatientBST (interface)  │
│  TriageService  ──→ IMaxHeap   (interface)   │
│  DoctorService  ──→ IDoctorQueue (interface) │
│  ExaminationService → IMedicalHistory        │
└──────────────────────┬───────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────┐
│          DATA STRUCTURE LAYER                 │
│                                              │
│  PatientBST  implements IPatientBST          │  ← Member 1
│  MaxHeap     implements IMaxHeap             │  ← Member 2
│  DoctorQueue implements IDoctorQueue         │  ← Member 3
│  MedicalHistoryList implements IMedicalHistory│  ← Member 4
└──────────────────────────────────────────────┘
```

**Nguyên tắc**: Service layer chỉ gọi method qua **interface**. Khi member implement xong class, hệ thống tự động hoạt động mà không cần sửa Service hay UI.

---

## 5. Phân Công Nhiệm Vụ

### ⚠️ QUY TẮC VÀNG

> **KHÔNG được sửa các file Interface** (`IPatientBST`, `IMaxHeap`, `IDoctorQueue`, `IMedicalHistory`).
> Đây là "hợp đồng" chung giữa các thành viên. Nếu cần thay đổi, phải thông báo cả nhóm.

### Bảng phân công

| Member | Package | Files cần implement | Interface cần implement |
|--------|---------|---------------------|------------------------|
| **Member 1** | `datastructure.bst` | `PatientNode.java` + `PatientBST.java` | `IPatientBST` |
| **Member 2** | `datastructure.heap` | `HeapNode.java` + `MaxHeap.java` | `IMaxHeap` |
| **Member 3** | `datastructure.queue` | `DoctorNode.java` + `DoctorQueue.java` | `IDoctorQueue` |
| **Member 4** | `datastructure.dll` | `HistoryNode.java` + `MedicalHistoryList.java` | `IMedicalHistory` |

### Ai KHÔNG nên sửa file nào?

| File/Folder | Ai sửa được |
|---|---|
| `model/` | Chỉ Team Lead (khi cả nhóm đồng ý) |
| `datastructure/*/I*.java` (interfaces) | **KHÔNG AI** được sửa |
| `service/` | Chỉ Team Lead |
| `ui/ConsoleUI.java` | Chỉ Team Lead |
| `Main.java` | Chỉ Team Lead |
| `datastructure/bst/PatientNode.java` | Chỉ Member 1 |
| `datastructure/bst/PatientBST.java` | Chỉ Member 1 |
| `datastructure/heap/HeapNode.java` | Chỉ Member 2 |
| `datastructure/heap/MaxHeap.java` | Chỉ Member 2 |
| `datastructure/queue/DoctorNode.java` | Chỉ Member 3 |
| `datastructure/queue/DoctorQueue.java` | Chỉ Member 3 |
| `datastructure/dll/HistoryNode.java` | Chỉ Member 4 |
| `datastructure/dll/MedicalHistoryList.java` | Chỉ Member 4 |

---

## 6. Hướng Dẫn Git Workflow

### Bước 1: Clone và tạo branch riêng

```bash
# Clone dự án
git clone https://github.com/Nguyen-Van-Gia-Binh/Smart-Hospital-Patient-Triage-System.git
cd Smart-Hospital-Patient-Triage-System

# Tạo branch riêng theo tên feature
git checkout -b feature/bst       # Member 1
git checkout -b feature/heap      # Member 2
git checkout -b feature/queue     # Member 3
git checkout -b feature/dll       # Member 4
```

### Bước 2: Code và commit thường xuyên

```bash
# Xem trạng thái file đã thay đổi
git status

# Thêm file đã sửa
git add src/datastructure/bst/PatientNode.java
git add src/datastructure/bst/PatientBST.java

# Commit với message rõ ràng
git commit -m "feat(bst): implement insert and search methods"
```

### Bước 3: Push branch lên remote

```bash
git push -u origin feature/bst
```

### Bước 4: Tạo Pull Request (PR)

1. Vào GitHub → Repository → **Pull Requests** → **New Pull Request**
2. Chọn base: `main` ← compare: `feature/bst`
3. Viết mô tả những gì đã implement
4. Tag Team Lead để review

### Quy tắc đặt tên commit

```
<type>(<scope>): <description>

Ví dụ:
feat(bst): implement insert method for PatientBST
feat(heap): implement extractMax with heapify-down
fix(queue): fix null pointer in dequeue when empty
test(dll): add test cases for displayRecent
docs: update README with setup instructions
```

| Type | Mô tả |
|------|--------|
| `feat` | Thêm tính năng mới |
| `fix` | Sửa lỗi |
| `test` | Thêm hoặc sửa test |
| `docs` | Cập nhật tài liệu |
| `refactor` | Tái cấu trúc code (không thay đổi logic) |

---

## 7. Quy Tắc Viết Code

### Đặt tên

```java
// Class: PascalCase
public class PatientBST { }

// Method: camelCase
public void insert(Patient patient) { }

// Biến: camelCase
private PatientNode root;

// Hằng số: UPPER_SNAKE_CASE
private static final int MAX_CAPACITY = 100;
```

### Comment

```java
/**
 * Chen benh nhan vao cay BST theo patientId.
 * Neu patientId da ton tai, khong chen trung lap.
 *
 * @param patient doi tuong Patient can chen
 */
public void insert(Patient patient) {
    // Tim vi tri chen bang cach so sanh patientId
    // ...
}
```

### Quy tắc chung

1. **Dùng private fields** + getter/setter cho Model
2. **Không import thư viện ngoài** (java.util.ArrayList, HashMap, v.v.) cho data structure — mục đích là tự implement
3. **Xử lý null** cẩn thận — kiểm tra tham số đầu vào
4. **Không sửa interface** — nếu thấy thiếu method, báo Team Lead
5. **Viết comment tiếng Việt không dấu** hoặc tiếng Anh đơn giản

---

## 8. Hướng Dẫn Implement Chi Tiết

### Member 1: BST (Binary Search Tree)

**File cần sửa**: `PatientNode.java` và `PatientBST.java`

```java
// PatientNode.java - Gợi ý cấu trúc
public class PatientNode {
    Patient data;
    PatientNode left;
    PatientNode right;

    public PatientNode(Patient data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
```

**Interface cần implement**: `IPatientBST`
- `insert(Patient)` — So sánh theo `patient.getPatientId()`, dùng `String.compareTo()`
- `search(String patientId)` — Tìm kiếm O(log n)
- `delete(String patientId)` — Xử lý 3 trường hợp: node lá, 1 con, 2 con

---

### Member 2: Max-Heap

**File cần sửa**: `HeapNode.java` và `MaxHeap.java`

```java
// HeapNode.java - Gợi ý cấu trúc
public class HeapNode {
    Patient data;

    public HeapNode(Patient data) {
        this.data = data;
    }
}
```

**Interface cần implement**: `IMaxHeap`
- `insert(Patient)` — Thêm vào cuối, rồi **heapify-up** theo `severityScore`
- `extractMax()` — Lấy root (score cao nhất), đưa phần tử cuối lên root, rồi **heapify-down**
- `isEmpty()` — Kiểm tra heap rỗng

> **Lưu ý**: Có thể dùng mảng `HeapNode[]` hoặc `Patient[]` để lưu heap.

---

### Member 3: Queue (FIFO)

**File cần sửa**: `DoctorNode.java` và `DoctorQueue.java`

```java
// DoctorNode.java - Gợi ý cấu trúc
public class DoctorNode {
    Doctor data;
    DoctorNode next;

    public DoctorNode(Doctor data) {
        this.data = data;
        this.next = null;
    }
}
```

**Interface cần implement**: `IDoctorQueue`
- `enqueue(Doctor)` — Thêm vào cuối hàng đợi
- `dequeue()` — Lấy ra từ đầu hàng đợi (FIFO)
- `isEmpty()` — Kiểm tra hàng đợi rỗng

> **Gợi ý**: Dùng 2 pointer `front` và `rear` để tối ưu enqueue O(1).

---

### Member 4: Doubly Linked List

**File cần sửa**: `HistoryNode.java` và `MedicalHistoryList.java`

```java
// HistoryNode.java - Gợi ý cấu trúc
public class HistoryNode {
    MedicalRecord data;
    HistoryNode prev;
    HistoryNode next;

    public HistoryNode(MedicalRecord data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
```

**Interface cần implement**: `IMedicalHistory`
- `addRecord(MedicalRecord)` — Thêm node vào cuối danh sách (append)
- `displayRecent(int n)` — Duyệt ngược từ cuối, in ra n bệnh án gần nhất

> **Gợi ý**: Dùng 2 pointer `head` và `tail` để tối ưu append O(1).

---

## 9. Cách Test Riêng Phần Của Mình

Mỗi member nên viết file test riêng trong thư mục gốc. Ví dụ:

### Member 1 - Test BST

```java
// TestBST.java (đặt cùng cấp với Main.java)
import datastructure.bst.PatientBST;
import model.Patient;

public class TestBST {
    public static void main(String[] args) {
        PatientBST bst = new PatientBST();

        // Test insert
        bst.insert(new Patient("P003", "Nguyen Van C", 30, 3));
        bst.insert(new Patient("P001", "Tran Van A", 25, 1));
        bst.insert(new Patient("P005", "Le Thi E", 40, 5));

        // Test search
        Patient found = bst.search("P003");
        System.out.println("Tim thay: " + found);  // Expected: P003

        Patient notFound = bst.search("P999");
        System.out.println("Tim thay: " + notFound);  // Expected: null

        // Test delete
        boolean deleted = bst.delete("P001");
        System.out.println("Xoa P001: " + deleted);  // Expected: true

        boolean deletedAgain = bst.delete("P001");
        System.out.println("Xoa P001 lan 2: " + deletedAgain);  // Expected: false
    }
}
```

### Member 2 - Test Heap

```java
// TestHeap.java
import datastructure.heap.MaxHeap;
import model.Patient;

public class TestHeap {
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();

        heap.insert(new Patient("P001", "A", 30, 2));
        heap.insert(new Patient("P002", "B", 45, 5));
        heap.insert(new Patient("P003", "C", 22, 1));
        heap.insert(new Patient("P004", "D", 60, 4));

        // Expected order: B(5) → D(4) → A(2) → C(1)
        while (!heap.isEmpty()) {
            Patient p = heap.extractMax();
            System.out.println(p.getName() + " - Severity: " + p.getSeverityScore());
        }
    }
}
```

---

## 10. Ghép Code & Tích Hợp

### Timeline đề xuất

```
Tuần 1:
├── Team Lead: Models, Interfaces, Services, ConsoleUI, Main  ✅ (Đã xong)
├── Member 1: Implement PatientBST
├── Member 2: Implement MaxHeap
├── Member 3: Implement DoctorQueue
└── Member 4: Implement MedicalHistoryList

Tuần 2:
├── Merge tất cả branch vào main
├── Test tích hợp toàn bộ hệ thống
├── Fix bug nếu có
└── Hoàn thiện & nộp bài
```

### Quy trình ghép

1. Tất cả member **push branch** lên GitHub
2. Team Lead **review từng PR** và merge vào `main`
3. Thứ tự merge khuyến nghị:
   ```
   1. feature/bst    → PatientService hoạt động
   2. feature/heap   → TriageService hoạt động
   3. feature/queue  → DoctorService hoạt động
   4. feature/dll    → ExaminationService hoạt động
   ```
4. Sau mỗi lần merge, chạy `Main.java` để kiểm tra

### Tại sao ghép dễ?

Vì mỗi Service đã được code sẵn để gọi method qua **interface**:

```java
// PatientService.java đã có sẵn:
private IPatientBST patientBST = new PatientBST();
//                                     ^^^^^^^^^^^
// Member 1 chỉ cần implement PatientBST → Service tự hoạt động!
```

---

## 11. Xử Lý Lỗi Thường Gặp

### `UnsupportedOperationException`

```
Exception: Chua duoc implement - Member X lam phan nay
```
→ **Nguyên nhân**: Data structure chưa được implement
→ **Cách sửa**: Member tương ứng cần implement logic thay thế cho `throw`

### `NullPointerException`

→ **Nguyên nhân**: Chưa kiểm tra null trước khi truy cập
→ **Cách sửa**: Luôn kiểm tra `if (node != null)` trước khi dùng

### Merge conflict

```bash
# Nếu gặp conflict khi merge
git pull origin main
# Giải quyết conflict trong file
git add .
git commit -m "fix: resolve merge conflict"
git push
```

→ **Phòng tránh**: Mỗi người CHỈ sửa file của mình. Nếu làm đúng phân công thì gần như **không bao giờ conflict**.

### Không compile được

```bash
# Xóa thư mục output cũ và biên dịch lại
rm -rf out/
javac -d out src/Main.java src/model/*.java src/datastructure/**/*.java src/service/*.java src/ui/*.java
```

---

## 12. Liên Hệ & Hỗ Trợ

- **Repository**: https://github.com/Nguyen-Van-Gia-Binh/Smart-Hospital-Patient-Triage-System
- Nếu gặp vấn đề về **interface** hoặc **service**, liên hệ Team Lead
- Nếu gặp vấn đề về **Git**, tham khảo phần [Hướng Dẫn Git Workflow](#6-hướng-dẫn-git-workflow)
- Tạo **Issue** trên GitHub nếu phát hiện bug

---

> 💡 **Nhớ**: Mục tiêu của dự án là **học cấu trúc dữ liệu**, không phải copy-paste. Hãy tự implement và hiểu từng dòng code mình viết!
