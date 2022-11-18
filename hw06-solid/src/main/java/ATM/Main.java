package ATM;

import ATM.Banknotes.BanknoteRu;
import ATM.Banknotes.Nominal;

public class Main {


    public static void main(String[] args) {

        System.out.println("bankomat )))");

        BanknoteRu banknote = new BanknoteRu(Nominal.FIFTY);
        BanknoteRu banknote2 = new BanknoteRu(Nominal.ONE_HUNDRED);

        ATM atm = new ATM();
        System.out.println(banknote.getNominal().getNominal());
        atm.setMoney(banknote);
        System.out.println(banknote.getNominal().getNominal());
        atm.setMoney(banknote2);
        System.out.println(banknote.getNominal().getNominal());

    }


}
