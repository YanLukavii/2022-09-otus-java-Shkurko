package ATM.Cassetes;

import ATM.Banknotes.BanknoteRu;

import java.util.Stack;

public class Cassett implements Casset {

      private final Stack<BanknoteRu> money = new Stack<>();

       public Stack<BanknoteRu> getMoneyStack () {
                return money;
        }




}
