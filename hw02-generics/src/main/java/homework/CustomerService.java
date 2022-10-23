package homework;

import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса

    private final TreeMap<Customer, String> customerStringTreeMap;

    public CustomerService() {

        this.customerStringTreeMap = new TreeMap<>();

    }

    public Map.Entry<Customer, String> getSmallest() {

        return Map.entry(new Customer(
                        customerStringTreeMap.firstEntry()
                                .getKey()
                                .getId(),
                        customerStringTreeMap.firstEntry()
                                .getKey()
                                .getName(),
                        customerStringTreeMap.firstEntry()
                                .getKey()
                                .getScores()),
                customerStringTreeMap.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {

        if (customerStringTreeMap.higherKey(customer) == null) return null;

        Customer nextCustomer = customerStringTreeMap.higherKey(customer);

        return Map.entry(new Customer(nextCustomer.getId(), nextCustomer.getName(), nextCustomer.getScores()), customerStringTreeMap.get(nextCustomer));

    }

    public void add(Customer customer, String data) {

        customerStringTreeMap.put(customer, data);

    }

}
