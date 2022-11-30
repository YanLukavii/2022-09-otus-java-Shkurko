package newATM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ATMImpl implements ATM {

    private CellStorage cellStorage;

    public void loadCellsWithMoneyByCollector()  {

        this.cellStorage = new CellStorageImpl();

        for (Banknote banknote : Banknote.values()) {
            for (int i = 0; i < 100; i++) {
                cellStorage.getCellByFace(banknote).addBanknoteToCell(banknote);
            }
        }
    }

    public int getAmountOfCashBalance() {
        int amount = 0;

        if(cellStorage == null) {return 0;}

        for (Banknote banknote : Banknote.values()) {
            amount = amount + cellStorage
                    .getCellByFace(banknote)
                    .getBanknoteList().size() * banknote
                    .faceValue();
        }
        return amount;
    }



    public List<Banknote> getMoney(int money)  {

        List<Banknote> banknotesForClient = generateBanknoteList(money);

        if (checkedMoneyInCell(banknotesForClient)) {

            removeBanknotes(banknotesForClient);

            return banknotesForClient;

        } else {

            throw new SomethingWrongException("Not enough money");

        }
    }

    private boolean checkedMoneyInCell(List<Banknote> list) {

        for (Banknote banknote : list) {

            if (!(Collections.frequency(list, banknote) <= Collections.frequency(cellStorage
                    .getCellByFace(banknote)
                    .getBanknoteList(), banknote))) {

                return false;
            }
        }

        return true;
    }

    private List<Banknote> generateBanknoteList(int money)  {

        List<Banknote> banknotesForClient = new ArrayList<>();

        Banknote[] banknotes = Banknote.values();
        int res = 0;
        int tmp;
        for (Banknote banknote : banknotes) {
            tmp = res;
            res += money / banknote.faceValue();
            if (tmp != res) {

                for (int i = 0; i < money / banknote.faceValue(); i++) {
                    banknotesForClient.add(banknote);
                }
            }
            money = money % banknote.faceValue();
        }
        if (money > 0) {

            throw new SomethingWrongException("incorrect amount");
        }
        return banknotesForClient;
    }

    private void removeBanknotes(List<Banknote> list) {

        for (Banknote x : list) {
            cellStorage.getCellByFace(x).removeBanknoteFromCell();
        }

    }

}
