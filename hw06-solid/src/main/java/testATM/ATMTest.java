package testATM;

import newATM.ATM;
import newATM.ATMImpl;
import newATM.Banknote;
import newATM.SomethingWrongException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ATMTest {

    private ATM atm;
    int initAmount = 185000;


    @BeforeEach
    void initATM() {

        atm = new ATMImpl();
        atm.loadCellsWithMoneyByCollector(100);
    }

    @Test
    @DisplayName("Тест на вывод среств в банкомате")
    void atmAmountTest() {

        int actual = atm.getAmountOfCashBalance();

        Assertions.assertEquals(initAmount,actual);
    }

    @Test
    @DisplayName("Тест на выдачу банкнот")
    void correctLoadMoneyInAtmTest() {

        List<Banknote> expected = List.of(Banknote.TWO_HUNDRED, Banknote.FIFTY);
        List<Banknote> actual = atm.getMoney(250);

        Assertions.assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Тест на уменьшение баланса после выдачи банкнот")
    void decreaseInBalanceTest () {

        atm.getMoney(1000);

        Assertions.assertEquals(initAmount - 1000 , atm.getAmountOfCashBalance());

    }

    @Test
    @DisplayName("Тест на выдачу банкнот при некорректном запросе денег")
    void incorrectAmount () {

        Assertions.assertThrows(SomethingWrongException.class,() -> atm.getMoney(1));

    }

    @Test
    @DisplayName("Тест на недостаток средств в банкомте")
    void insufficientFunds () {

        Assertions.assertThrows(SomethingWrongException.class,() -> atm.getMoney(1000000));

    }
}