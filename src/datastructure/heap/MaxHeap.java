package datastructure.heap;

import java.util.ArrayList;
import java.util.List;
import model.TriageRecord;
import datastructure.queue.ITriageQueue;

/**
 * Lớp cài đặt cấu trúc dữ liệu Max-Heap để ưu tiên bệnh nhân theo mức độ nghiêm trọng.
 * Bệnh nhân có mức độ nghiêm trọng cao hơn sẽ được phục vụ trước.
 * Lớp này thực thi (implement) cả hai interface là IMaxHeap và ITriageQueue.
 */
public class MaxHeap implements IMaxHeap, ITriageQueue {

    /** Mảng động lưu trữ các hồ sơ phân loại bệnh nhân theo cấu trúc Max-Heap. */
    private List<TriageRecord> heap;

    /**
     * Khởi tạo một Max-Heap rỗng.
     */
    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    /**
     * Khởi tạo Max-Heap từ một danh sách các hồ sơ phân loại có sẵn.
     * Sử dụng thuật toán build-heap (heapify từ dưới lên) với độ phức tạp O(n).
     *
     * @param initialHeap Danh sách hồ sơ phân loại ban đầu
     */
    public MaxHeap(List<TriageRecord> initialHeap) {
        this.heap = new ArrayList<>();
        if (initialHeap != null) {
            this.heap.addAll(initialHeap);
            for (int i = this.heap.size() / 2 - 1; i >= 0; i--) {
                siftDown(i);
            }
        }
    }

    /**
     * Hoán đổi vị trí hai phần tử trong heap.
     */
    private void swap(int i, int j) {
        if (i == j) {
            return;
        }
        TriageRecord temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * So sánh hai hồ sơ phân loại dựa trên mức độ ưu tiên.
     *
     * @param a Hồ sơ thứ nhất
     * @param b Hồ sơ thứ hai
     * @return true nếu hồ sơ 'a' có mức độ ưu tiên cao hơn hồ sơ 'b'
     */
    private boolean hasHigherPriority(TriageRecord a, TriageRecord b) {
        return a.compareTo(b) > 0;
    }

    /**
     * Thao tác Sift-up: Di chuyển phần tử tại vị trí index lên trên để duy trì thuộc tính Max-Heap.
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (!hasHigherPriority(heap.get(index), heap.get(parent))) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    /**
     * Thao tác Sift-down (Heapify): Di chuyển phần tử tại vị trí index xuống dưới để duy trì thuộc tính Max-Heap.
     */
    private void siftDown(int index) {
        int size = heap.size();
        while (true) {
            int largest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < size && hasHigherPriority(heap.get(left), heap.get(largest))) {
                largest = left;
            }
            if (right < size && hasHigherPriority(heap.get(right), heap.get(largest))) {
                largest = right;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    /**
     * Phương thức heapify công khai dành cho việc xây dựng heap từ bên ngoài.
     * Thực hiện thao tác sift-down trên một danh sách được chỉ định.
     * 
     * @param heapList Danh sách cần heapify
     * @param size Kích thước của heap
     * @param i Vị trí bắt đầu heapify
     */
    public void heapify(List<TriageRecord> heapList, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && hasHigherPriority(heapList.get(left), heapList.get(largest))) {
            largest = left;
        }
        if (right < size && hasHigherPriority(heapList.get(right), heapList.get(largest))) {
            largest = right;
        }

        if (largest != i) {
            TriageRecord temp = heapList.get(i);
            heapList.set(i, heapList.get(largest));
            heapList.set(largest, temp);
            heapify(heapList, size, largest);
        }
    }

    // ==================== Các phương thức của IMaxHeap ====================

    @Override
    public void insert(TriageRecord record) {
        if (record == null) {
            return;
        }
        heap.add(record);
        siftUp(heap.size() - 1);
    }

    @Override
    public TriageRecord extractMax() {
        if (heap.isEmpty()) {
            return null;
        }

        TriageRecord maxRecord = heap.get(0);
        int lastIndex = heap.size() - 1;

        if (lastIndex > 0) {
            swap(0, lastIndex);
            heap.remove(lastIndex);
            siftDown(0);
        } else {
            heap.remove(0);
        }

        return maxRecord;
    }

    // ==================== Các phương thức của ITriageQueue ====================

    @Override
    public void enqueue(TriageRecord record) {
        insert(record);
    }

    @Override
    public TriageRecord dequeue() {
        return extractMax();
    }

    @Override
    public TriageRecord peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    // ==================== Các phương thức chung ====================

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public List<TriageRecord> peekAll() {
        List<TriageRecord> sorted = new ArrayList<>(heap);
        sorted.sort((a, b) -> b.compareTo(a)); // Sắp xếp theo thứ tự giảm dần
        return sorted;
    }
}
