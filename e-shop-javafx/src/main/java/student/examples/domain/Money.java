package student.examples.domain;

public class Money {

    private int amount;
    private Currency currency;
    
    public Money(int amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money(float amount, Currency currency) {
        this.amount = (int)(amount * 100);
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public float getFloatAmount() {
        return amount / 100.0f;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }


}
