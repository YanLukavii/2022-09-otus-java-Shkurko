package newATM;

import java.util.List;

public interface Cell {

    void addBanknoteToCell(Banknote banknote);

    void removeBanknoteFromCell();

    List<Banknote> getBanknoteList();

}
