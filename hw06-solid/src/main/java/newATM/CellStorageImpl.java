package newATM;

import java.util.Map;
import java.util.TreeMap;

public class CellStorageImpl implements CellStorage {

   private final Map<Integer,Cell> cellMap = new TreeMap<>();

    public CellStorageImpl(Banknote[] banknotes) {
        for (Banknote banknote:banknotes) {
            cellMap.put(banknote.faceValue(),new CellImpl(banknote));
        }
    }

    public Cell getCellByFace(Banknote banknote) {
       return cellMap.get(banknote.faceValue());
    }

}
