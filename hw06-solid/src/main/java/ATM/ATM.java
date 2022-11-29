package ATM;

import ATM.Banknotes.Banknote;
import ATM.Cassetes.Casset;
import ATM.Cassetes.Cassett;
import bankomat.Nominal;

import java.util.Map;
import java.util.TreeMap;

public class ATM {

    Map<Nominal,Casset> cassettMap = new TreeMap<>();


    public ATM() {

        cassettMap.put(Nominal.FIFTY, new Cassett());
        cassettMap.put(Nominal.ONE_HUNDRED, new Cassett());

    }

    private boolean isCorrectBanknoteNominal(Banknote banknote, Nominal nominal) {

        return banknote.getNominal().equals(nominal);
    }

    private void setMoneyIntoCasset(Casset casset, Banknote banknote) {

        casset.getMoneyStack().push(banknote);

    }

/*    void setMoney(Banknote banknote) {

        if (isCorrectBanknoteNominal(banknote, Nominal.FIFTY)) {
            setMoneyIntoCasset(cassettMap.get(Nominal.FIFTY)), banknote);
        } else if (isCorrectBanknoteNominal(banknote, Nominal.ONE_HUNDRED)) {
            setMoneyIntoCasset(cassettMap.get(banknote.getNominal()), banknote);
        } else System.out.println("НАМ СУНУЛИ ЧТО-ТО НЕ ТО!");

    }*/


    Casset getCasset (Nominal nominal) {
        return cassettMap.get(nominal);
    }

    int getAllMoney() {

        return (getCassetMoney(cassettMap.get(Nominal.FIFTY)) + getCassetMoney(cassettMap.get(Nominal.ONE_HUNDRED)));
    }

    private int getCassetMoney(Casset casset) {

        int collectionSize = casset.getMoneyStack().size();

        if (collectionSize == 0) {
            return 0;
        }

        return collectionSize * casset
                .getMoneyStack()
                .get(0)
                .getNominal()
                .getNominal();
    }

/*    void withdrawalOfFunds (int founds) {

        int c[] = {100, 50};

            int res = 0;

            for (int i = 0; i < 2; i++) {

                res += founds / c[i];
                if (founds / c[i] >= 1) {
                    System.out.println("vidat` " + founds / c[i] + " kupyur " + c[i]);
                    if (ONE_HUNDRED_CASSET.getMoneyStack().size()>=founds / c[i]) {
                        for (int j = 0; j < founds / c[i]; j++) {
                            ONE_HUNDRED_CASSET.getMoneyStack().pop();
                        }

                    }
                    else continue;

                }
                founds = founds % c[i];

            }

            if (founds > 0)

                System.out.println("ne vidat");

            else

                System.out.println(res + " kupyur");

    }*/


}
