package newATM;

public class Main {


    public static void main(String[] args) {


        ATM atm = new ATMImpl();
        atm.loadCellsWithMoneyByCollector();


        System.out.println(atm.getAmountOfMoneyInAtm());

        atm.getMoney(99150);
        System.out.println( atm.getAmountOfMoneyInAtm());


    }

}
