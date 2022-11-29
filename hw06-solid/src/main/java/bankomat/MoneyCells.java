package bankomat;

import java.util.Map;
import java.util.TreeMap;

public class MoneyCells {

    MoneyCellImpl cell ;

    Map<Nominal,MoneyCellImpl> map = new TreeMap<>();

    void addMoneyCell (MoneyCellImpl moneyCell) {
        cell = moneyCell;
        map.put(cell.getBanknote().getNominal(),moneyCell);

    }



}
