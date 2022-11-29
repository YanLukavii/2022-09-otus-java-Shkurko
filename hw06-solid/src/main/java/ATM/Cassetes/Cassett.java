package ATM.Cassetes;

import ATM.Banknotes.Banknote;


import java.util.Stack;

public class Cassett implements Casset {

      private final Stack<Banknote> money = new Stack<>();

       public Stack<Banknote> getMoneyStack () {
                return money;
        }




}
