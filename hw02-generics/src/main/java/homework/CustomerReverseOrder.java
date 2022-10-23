package homework;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса

   private final Deque<Customer> customerDeque = new ArrayDeque<>();

    public void add(Customer customer) {

        customerDeque.push(customer);

    }

    public Customer take() {

        return customerDeque.pop();
    }
}
