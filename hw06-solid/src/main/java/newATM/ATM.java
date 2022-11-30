package newATM;

import java.util.List;

public interface ATM {

    void loadCellsWithMoneyByCollector() throws SomethingWrongException;

    int getAmountOfCashBalance();

    List<Banknote> getMoney(int n) throws SomethingWrongException;

}
