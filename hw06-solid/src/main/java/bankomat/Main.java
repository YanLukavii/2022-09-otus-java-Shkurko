package bankomat;

public class Main {


    public static void main(String[] args) {

        BanknoteImpl b1 =  new BanknoteImpl(Nominal.FIFTY);

        MoneyCellImpl moneyCell =  new MoneyCellImpl();

        moneyCell.addBanknote(b1);

        MoneyCells moneyCells = new MoneyCells();

        moneyCells.addMoneyCell(moneyCell);


     //  ATMImpl atm = new ATMImpl(moneyCells);
    }








}
