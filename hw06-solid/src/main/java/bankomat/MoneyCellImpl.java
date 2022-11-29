package bankomat;

import java.util.ArrayDeque;
import java.util.Deque;

public class MoneyCellImpl {


    Deque<BanknoteImpl> banknotes = new ArrayDeque<>();

    void removeBanknote () {

        banknotes.pop();
    }

    void addBanknote (BanknoteImpl banknote) {

        banknotes.push(banknote);
    }


    BanknoteImpl getBanknote (){

        return  banknotes.getFirst();
    }


}
