package ATM.Banknotes;

public enum Nominal {

    FIFTY(50),
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500);

    private final int NOMINAL;

    Nominal(int nominal) {
        this.NOMINAL = nominal;
    }

    public int getNominal() {
        return NOMINAL;
    }
}
