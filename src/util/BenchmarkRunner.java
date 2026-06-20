package util;

import datastructure.heap.MaxHeap;
import datastructure.queue.PriorityQueue;
import model.Patient;
import model.TriageRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tiện ích đo đạc hiệu năng (Benchmark) so sánh giữa Sorted Linked List (PriorityQueue)
 * và Binary Max-Heap (MaxHeap) với các kích thước đầu vào khác nhau (N = 100 đến 10,000).
 */
public class BenchmarkRunner {

    private static final Random random = new Random(42); // Seed cố định để kết quả tái lập được

    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("   SMART HOSPITAL TRIAGE SYSTEM - PERFORMANCE BENCHMARK RUNNER   ");
        System.out.println("================================================================\n");

        int[] inputSizes = {100, 500, 1000, 5000, 10000};

        System.out.printf("%-10s | %-30s | %-30s%n", "Size (N)", "Sorted Linked List (SLL)", "Binary Max-Heap");
        System.out.printf("%-10s | %-14s %-15s | %-14s %-15s%n", "", "Enqueue (ms)", "Dequeue (ms)", "Enqueue (ms)", "Dequeue (ms)");
        System.out.println("--------------------------------------------------------------------------------");

        for (int n : inputSizes) {
            // Chuẩn bị dữ liệu mẫu bệnh nhân
            List<TriageRecord> records = generateMockRecords(n);

            // 1. Đo Sorted Linked List (PriorityQueue)
            PriorityQueue priorityQueue = new PriorityQueue();
            
            // Đo Enqueue SLL
            long startTime = System.nanoTime();
            for (TriageRecord r : records) {
                priorityQueue.enqueue(r);
            }
            long endTime = System.nanoTime();
            double sllEnqueueTime = (endTime - startTime) / 1e6; // Đổi sang ms

            // Đo Dequeue SLL
            startTime = System.nanoTime();
            while (!priorityQueue.isEmpty()) {
                priorityQueue.dequeue();
            }
            endTime = System.nanoTime();
            double sllDequeueTime = (endTime - startTime) / 1e6;

            // 2. Đo Binary Max-Heap (MaxHeap)
            MaxHeap maxHeap = new MaxHeap();

            // Đo Enqueue Max-Heap
            startTime = System.nanoTime();
            for (TriageRecord r : records) {
                maxHeap.enqueue(r);
            }
            endTime = System.nanoTime();
            double heapEnqueueTime = (endTime - startTime) / 1e6;

            // Đo Dequeue Max-Heap
            startTime = System.nanoTime();
            while (!maxHeap.isEmpty()) {
                maxHeap.dequeue();
            }
            endTime = System.nanoTime();
            double heapDequeueTime = (endTime - startTime) / 1e6;

            // In kết quả
            System.out.printf("%-10d | %-14.4f %-15.4f | %-14.4f %-15.4f%n",
                    n, sllEnqueueTime, sllDequeueTime, heapEnqueueTime, heapDequeueTime);
        }

        System.out.println("\n================================================================");
        System.out.println("Benchmark completed. Copy the execution times above to Report 4.");
        System.out.println("================================================================");
    }

    private static List<TriageRecord> generateMockRecords(int size) {
        List<TriageRecord> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Patient p = new Patient("BN" + String.format("%04d", i), "Patient Mock " + i, 20 + random.nextInt(60));
            int severity = 2 + random.nextInt(4); // Điểm nguy kịch từ 2 đến 5
            list.add(new TriageRecord(p, severity));
        }
        return list;
    }
}
