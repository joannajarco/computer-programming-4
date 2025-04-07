package pl.jjarco.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    void cardIsIdentifiedWithNumber() {
        //Arrange   //Given     //Input
        var number = "1234-5678";
        //Act       //When      //Action
        var card = new CreditCard(number);
        //Assert    //Then      //Output
        assertEquals(
                "1234-5678",
                card.getNumber()
        );
    }

    @Test
    void assignInitialCredit() {
        //Arrange
        var card = new CreditCard("1234-5678");
        //Act
        card.assignCredit(BigDecimal.valueOf(1500));
        //Assert
        assertEquals(
                BigDecimal.valueOf(1500),
                card.getBalance()
        );
    }

    @Test
    void limitCantBeLowerThanThreshold() {
        var card = new CreditCard("1234-5678");

        try {
            card.assignCredit(BigDecimal.valueOf(50));
            fail("Exception should be thrown");
        } catch (CreditBelowThresholdException e) {
            assertTrue(true);
        }
    }

    @Test
    void limitCantBeLowerThanThresholdV2() {
        var card = new CreditCard("1234-5678");

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCredit(BigDecimal.valueOf(50))
        );
    }


    @Test
    void limitCantBeLowerThanThresholdV3() {
        var card = new CreditCard("1234-5678");

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCredit(BigDecimal.valueOf(99))
        );

        assertDoesNotThrow(
                () -> card.assignCredit(BigDecimal.valueOf(100))
        );
    }

    @Test
    void creditCantBeAssignedTwice() {
        var card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(1000));

        assertThrows(
                CreditCantBeAssignedTwiceException.class,
                () -> card.assignCredit(BigDecimal.valueOf(1500))
        );
    }

    @Test
    void itAllowsToWithdrawSomeMoney() {
        var card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(100));

        assertEquals(
                BigDecimal.valueOf(900),
                card.getBalance()
        );
    }

    @Test
    void itAllowsToWithdrawSomeMoneyV2() {
        var card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(100));
        card.withdraw(BigDecimal.valueOf(100));
        card.withdraw(BigDecimal.valueOf(100));

        assertEquals(
                BigDecimal.valueOf(700),
                card.getBalance()
        );
    }

    @Test
    void cantWithdrawWhenNotEnoughMoney() {
        var card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(1000));
        card.withdraw(BigDecimal.valueOf(600));

        assertThrows(
                NotEnoughMoneyException.class,
                () -> card.withdraw(BigDecimal.valueOf(600))
        );
    }

    @Test
    void canWithdrawWhenSpendingTotalCredit() {
        var card = new CreditCard("1234-5678");
        card.assignCredit(BigDecimal.valueOf(1000));

        assertDoesNotThrow(
                () -> card.withdraw(BigDecimal.valueOf(1000))
        );
    }

}
