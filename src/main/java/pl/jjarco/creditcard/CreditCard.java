package pl.jjarco.creditcard;

import java.math.BigDecimal;

public class CreditCard {

    public static final int CREDIT_THRESHOLD = 100;
    private final String cardNumber;
    private BigDecimal initialCredit;
    private BigDecimal balance;

    public CreditCard(String cardNumber) {this.cardNumber = cardNumber;
    }

    public String getNumber() {return cardNumber;
    }

    public void assignCredit(BigDecimal initialCredit) {

        if (isCreditBelowThreshold(initialCredit)) {
            throw new CreditBelowThresholdException();
        }

        if (isCreditAlreadyAssigned()) {
            throw new CreditCantBeAssignedTwiceException();
        }

        this.initialCredit = initialCredit;
        this.balance = initialCredit;
    }

    private boolean isCreditAlreadyAssigned() {
        return this.initialCredit != null;
    }

    private static boolean isCreditBelowThreshold(BigDecimal initialCredit) {
        return BigDecimal.valueOf(CREDIT_THRESHOLD).compareTo(initialCredit) > 0;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal money) {
        if (!canAfford(money)) {
            throw new NotEnoughMoneyException();
        }

        this.balance = this.balance.subtract(money);
    }

    private boolean canAfford(BigDecimal money) {
        return this.balance.subtract(money).compareTo(BigDecimal.ZERO) >= 0;
    }

}
