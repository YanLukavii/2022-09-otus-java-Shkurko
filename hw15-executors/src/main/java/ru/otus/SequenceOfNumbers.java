package ru.otus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SequenceOfNumbers {

    private String last = "last";

    private synchronized void action(String msg) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (last.equals(msg)) {
                    this.wait();
                }
                System.out.println(Thread.currentThread().getName() + " - " + list.get(index));
                last = msg;
                index++;
                if (index == list.size()) {
                    Collections.reverse(list);
                    index = 1;
                }
                sleep();
                this.notify();

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        SequenceOfNumbers sequenceOfNumbers = new SequenceOfNumbers();
        new Thread(() -> sequenceOfNumbers.action("second")).start();
        new Thread(() -> sequenceOfNumbers.action("last")).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
