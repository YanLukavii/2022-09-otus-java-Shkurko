package ATM.Banknotes;

import bankomat.Nominal;

public class BanknoteRu implements Banknote {

    Nominal nominal;

    public Nominal getNominal() {
        return nominal;
    }

    public BanknoteRu(Nominal nominal) {
        this.nominal = nominal;
    }

}
