package ATM;

import ATM.Banknotes.BanknoteRu;
import ATM.Banknotes.Nominal;
import ATM.Cassetes.Casset;
import ATM.Cassetes.Cassett;

public class ATM {

    private final Casset fifty;
    private final Casset oneHundred;

    public ATM() {
        fifty = new Cassett();
        oneHundred = new Cassett();
    }

   void setMoney (BanknoteRu banknoteRu) {

        if (banknoteRu.getNominal().equals(Nominal.FIFTY)) {
            fifty.getMoneyStack().push(banknoteRu);
        }else if (banknoteRu.getNominal().equals(Nominal.ONE_HUNDRED)) {
           oneHundred.getMoneyStack().push(banknoteRu);
       }
        else System.out.println("!!!!!!!!!!!!");

    }



}
