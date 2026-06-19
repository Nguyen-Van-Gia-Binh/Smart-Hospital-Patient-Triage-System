package datastructure.heap;

import java.util.ArrayList;
import java.util.List;
import model.Patient;

/**
 * Max-Heap de uu tien benh nhan theo muc do nghiem trong (severityScore).
 * Benh nhan nang nhat (score cao nhat) se duoc goi kham truoc.
 *
 * Member 2 se implement class nay.
 * Class nay PHAI implement IMaxHeap.
 */
public class MaxHeap implements IMaxHeap {

    private List<Patient> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    public MaxHeap(List<Patient> initialHeap) {
        this.heap = new ArrayList<>();
        if (initialHeap != null) {
            this.heap.addAll(initialHeap);
            for (int i = this.heap.size() / 2 - 1; i >= 0; i--) {
                heapify(this.heap, this.heap.size(), i);
            }
        }
    }

    private void swap(int i, int j) {
        if (i == j) {
            return;
        }
        Patient temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void heapify(List<Patient> heapList, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && heapList.get(left).getSeverityScore() > heapList.get(largest).getSeverityScore()) {
            largest = left;
        }
        if (right < size && heapList.get(right).getSeverityScore() > heapList.get(largest).getSeverityScore()) {
            largest = right;
        }

        if (largest != i) {
            Patient temp = heapList.get(i);
            heapList.set(i, heapList.get(largest));
            heapList.set(largest, temp);
            heapify(heapList, size, largest);
        }
    }

    @Override
    public void insert(Patient patient) {
        if (patient == null) {
            return;
        }

        heap.add(patient);
        int index = heap.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).getSeverityScore() <= heap.get(parent).getSeverityScore()) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    @Override
    public Patient extractMax() {
        if (heap.isEmpty()) {
            return null;
        }

        Patient maxPatient = heap.get(0);
        int lastIndex = heap.size() - 1;

        if (lastIndex > 0) {
            swap(0, lastIndex);
            heap.remove(lastIndex);
            heapify(heap, heap.size(), 0);
        } else {
            heap.remove(0);
        }

        return maxPatient;
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public List<Patient> peekAll() {
        // Tao ban sao va sap xep theo thu tu uu tien giam dan
        List<Patient> sorted = new ArrayList<>(heap);
        sorted.sort((a, b) -> b.getSeverityScore() - a.getSeverityScore());
        return sorted;
    }
}