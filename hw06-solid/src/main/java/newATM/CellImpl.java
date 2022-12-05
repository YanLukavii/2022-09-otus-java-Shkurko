package newATM;

import java.util.ArrayList;
import java.util.List;

public class CellImpl implements Cell {

    private final List<Banknote> banknoteList = new ArrayList<>();
    private final Banknote cellType;

    public CellImpl(Banknote cellType) {
        this.cellType = cellType;
    }

    public void addBanknoteToCell(Banknote banknote)  {
        if (cellType.faceValue() == banknote.faceValue()) {
            banknoteList.add(banknote);
        } else {
            throw new SomethingWrongException("wrong denomination");
        }

    }

    public void removeBanknoteFromCell() {
        banknoteList.remove(0);
    }

    public List<Banknote> getBanknoteList() {
        return banknoteList;
    }


}
