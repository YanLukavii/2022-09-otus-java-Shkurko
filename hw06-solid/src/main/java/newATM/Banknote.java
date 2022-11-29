package newATM;

public enum Banknote {

    ONE_THOUSAND(1000),
    FIVE_HUNDRED(500),
    TWO_HUNDRED(200),
    ONE_HUNDRED(100),
    FIFTY(50);

    private final int faceValue;

    Banknote(int faceValue) {
        this.faceValue = faceValue;
    }

    public int faceValue() {
        return faceValue;
    }

}
