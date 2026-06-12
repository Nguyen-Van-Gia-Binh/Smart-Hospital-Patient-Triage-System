package datastructure.queue;

import model.Doctor;

/**
 * Hang doi FIFO de quan ly bac si san sang tiep nhan benh nhan.
 * Bac si ranh lau nhat se duoc phan cong truoc (FIFO).
 *
 * Member 3 se implement class nay.
 * Class nay PHAI implement IDoctorQueue.
 */
public class DoctorQueue implements IDoctorQueue {

    private DoctorNode front;
    private DoctorNode rear;

    public DoctorQueue() {
        this.front = null;
        this.rear = null;
    }

    @Override
    public void enqueue(Doctor doctor) {
        if (isEmpty()) {
            front = rear = new DoctorNode(doctor);
        } else {
            rear.setNext(new DoctorNode(doctor));
            rear = rear.getNext();
        }
    }

    @Override
    public Doctor dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Hang doi rong");
        }
        Doctor doctor = front.getDoctor();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        return doctor;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

}
