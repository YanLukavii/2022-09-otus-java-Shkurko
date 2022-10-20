package homework;

import java.util.ArrayDeque;
import java.util.Deque;


public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса

    Deque<Customer> deq = new ArrayDeque<>();

    public void add(Customer customer) {

        deq.push(customer);

    }

    public Customer take() {

        return deq.pop();
    }
}
