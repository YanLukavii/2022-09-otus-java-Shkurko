package homework;

import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса

    TreeMap<Customer, String> tree;

    public CustomerService() {

        this.tree = new TreeMap<Customer, String>(new Comparator<Customer>() {
            public int compare(Customer o1, Customer o2) {
                return o1.compareTo(o2);
            }
        });

    }

    public Map.Entry<Customer, String> getSmallest() {

        TreeMap<Customer, String> fakeTree = getFakeTree(tree);

        return fakeTree.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {

        TreeMap<Customer, String> fakeTree = getFakeTree(tree);

        if (fakeTree.higherKey(customer) == null) return null;

        Customer cus = fakeTree.higherKey(customer);

        Set sett = fakeTree.entrySet();
        Iterator i = sett.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            if (me.getKey().equals(cus)) {
                return me;
            }
        }
        return fakeTree.lastEntry();
    }

    public void add(Customer customer, String data) {

        tree.put(customer, data);
    }

    public TreeMap<Customer, String> getFakeTree(TreeMap<Customer, String> tree) {
        TreeMap<Customer, String> fakeTree = new TreeMap<>();

        for (Map.Entry<Customer, String> x : tree.entrySet()
        ) {
            fakeTree.put(new Customer(x.getKey().getId(), x.getKey().getName(), x.getKey().getScores()), x.getValue());
        }
        return fakeTree;
    }
}
