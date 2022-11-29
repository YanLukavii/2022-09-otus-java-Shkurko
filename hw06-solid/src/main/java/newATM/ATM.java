package newATM;

import java.util.List;

public interface ATM {

    void loadCellsWithMoneyByCollector();

    int getAmountOfMoneyInAtm ();

    CellStoreage getCellStorage();

    List<Banknote> getMoney(int n);

}
