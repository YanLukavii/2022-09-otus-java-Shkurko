package newATM;

import java.util.List;

public interface ATM {

    void loadCellsWithMoneyByCollector(int numberOfBanknotes) throws SomethingWrongException;

    int getAmountOfCashBalance();

    List<Banknote> getMoney(int n) throws SomethingWrongException;

}
