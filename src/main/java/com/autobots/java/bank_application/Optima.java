package com.autobots.java.bank_application;

public class Optima extends BankBase{

    private double balance = 0;

    public Optima(long accountNumber, long routingNumber) throws Exception{
        super(accountNumber, routingNumber);
        addToAllBankRecords(this);



    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {

        if (amount < 0 || amount >=300000){
            throw  new IllegalArgumentException("invalid amount can not  be amount");
        }
        balance += amount;
    }

    @Override
    public void withDraw(double amount) {

        if (amount<0 || amount >= 250000){
            throw new IllegalArgumentException("invalid amount can not be withDrew");
        }else {
            if (amount > balance){
                throw  new IllegalArgumentException("insufficient funds");
            }else {
                balance -= amount;
            }
        }

    }

}