package newATM;

public class Main {


    public static void main(String[] args) {

        ATM atm = new ATMImpl();

        System.out.println(atm.getAmountOfCashBalance());

        atm.loadCellsWithMoneyByCollector();

        System.out.println(atm.getAmountOfCashBalance());

        System.out.println(atm.getMoney(1600));

        System.out.println(atm.getAmountOfCashBalance());



    }

}
