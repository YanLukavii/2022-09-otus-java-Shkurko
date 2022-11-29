package newATM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ATMImpl implements ATM {

    private CellStoreage cellStoreage;

    public void loadCellsWithMoneyByCollector() {

        this.cellStoreage = new CellStoreageImpl();

        for (Banknote x : Banknote.values()) {
            for (int i = 0; i < 100; i++) {
                cellStoreage.getCellByFace(x).addBanknoteToCell(x);
            }
        }
    }

    public int getAmountOfMoneyInAtm() {
        int amount = 0;
        for (Banknote banknote : Banknote.values()) {
            amount = amount + cellStoreage
                    .getCellByFace(banknote)
                    .getBanknoteList().size() * banknote
                    .faceValue();
        }
        return amount;
    }

    public CellStoreage getCellStorage() {
        return cellStoreage;
    }

    public List<Banknote> getMoney(int money) {

        List<Banknote> banknotesForClient = generateBanknoteList(money);

        if (checkedMoneyInCell(banknotesForClient)) {

            removeBanknotes(banknotesForClient);

            return banknotesForClient;

        } else {
            System.out.println("Not enough money");
            return new ArrayList<>();
        }
    }

    public boolean checkedMoneyInCell(List<Banknote> list) {

        for (Banknote x : list) {

            if (!(Collections.frequency(list, x) <= Collections.frequency(cellStoreage
                    .getCellByFace(x)
                    .getBanknoteList(), x))) {

                return false;
            }
        }

        return true;
    }

    public List<Banknote> generateBanknoteList(int money) {

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
            System.out.println("Incorrect sum");
        }
        return banknotesForClient;
    }

    public void removeBanknotes(List<Banknote> list) {

        for (Banknote x : list) {
            cellStoreage.getCellByFace(x).removeBanknoteFromCell();
        }

    }

}
