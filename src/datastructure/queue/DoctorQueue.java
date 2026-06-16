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

    // TODO: Member 3 implement tat ca cac method ben duoi

    @Override
    public void enqueue(Doctor doctor) {
       // TODO: Them bac si vao cuoi hang doi
        DoctorNode newNode = new DoctorNode(doctor);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }
        rear = newNode;
    }

    @Override
    public Doctor dequeue() {
        // TODO: Lay va xoa bac si o dau hang doi
        if(isEmpty()) {
            return null; //co the nem exception neu hang doi rong
        }
        Doctor data = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        return data;
    }

    @Override
    public boolean isEmpty() {
       // TODO: Kiem tra hang doi co rong hay khong
        return front == null;
    }

}
