package com.autobots.bankApp;

public class BankService {

    public static  boolean transfer(BankAccount from, BankAccount to, double amount){
        if (from.withDraw(amount)){
            double convertedSum = ExchangeRate.convert(amount,from.getCurrency(), to.getCurrency());
            to.deposit(convertedSum);
            from.addTransaction("TRANSFER OUT",amount);
            to.addTransaction("TRANSFER IN", convertedSum);
            return true;
        }
        return  false;
    }
}
