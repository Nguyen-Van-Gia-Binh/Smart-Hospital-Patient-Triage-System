package datastructure.heap;

import java.util.ArrayList;
import java.util.List;
import model.TriageRecord;
import datastructure.queue.ITriageQueue;

/**
 * Max-Heap de uu tien benh nhan theo muc do nghiem trong (TriageRecord).
 *
 * Class nay implement ca IMaxHeap va ITriageQueue.
 */
public class MaxHeap implements IMaxHeap, ITriageQueue {

    /** Mang dong luu tru cac ban ghi triage theo cau truc Max-Heap. */
    private List<TriageRecord> heap;

    /**
     * Khoi tao Max-Heap trong.
     */
    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    /**
     * Khoi tao Max-Heap tu mot danh sach ban ghi triage co san.
     * Su dung thuat toan build-heap (heapify tu duoi len) voi do phuc tap O(n).
     *
     * @param initialHeap danh sach ban ghi triage ban dau
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
     * Doi cho 2 phan tu trong heap.
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
     * So sanh 2 ban ghi triage theo muc do uu tien.
     * Dung compareTo.
     *
     * @param a ban ghi thu nhat
     * @param b ban ghi thu hai
     * @return true neu a co uu tien cao hon b
     */
    private boolean hasHigherPriority(TriageRecord a, TriageRecord b) {
        return a.compareTo(b) > 0;
    }

    /**
     * Sift-up: Di chuyen phan tu tai vi tri index len tren
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
     * Sift-down (Heapify): Di chuyen phan tu tai vi tri index xuong duoi
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
     * Cung cap cho build-heap tu ben ngoai.
     * Thuc hien sift-down tren danh sach chi dinh.
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

    // ==================== IMaxHeap Methods ====================

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

    // ==================== ITriageQueue Methods ====================

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

    // ==================== Common Methods ====================

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
        sorted.sort((a, b) -> b.compareTo(a)); // sort descending
        return sorted;
    }
}
