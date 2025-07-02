package com.autobots.bankApp;

public class CreditAccount extends BankAccount {

    private final double creditLimit = 1000;

    public CreditAccount(Client owner, Currency currency) {
        super(owner, currency);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        addTransaction("DEPOSIT", amount);

    }

    @Override
    public boolean withDraw(double amount) {
        if (amount <= balance + creditLimit) {
            balance -= amount;
            addTransaction("WITHDRAW", amount);
            return true;
        }
        return false;
    }
}
