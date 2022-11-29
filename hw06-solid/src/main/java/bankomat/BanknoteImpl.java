package bankomat;

public class BanknoteImpl {

    Nominal nominal;

    public BanknoteImpl(Nominal nominal) {
        this.nominal = nominal;
    }


    public Nominal getNominal() {
        return nominal;
    }
}
