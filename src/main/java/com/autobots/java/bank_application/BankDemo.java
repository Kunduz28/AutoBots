package com.autobots.java.bank_application;

public class BankDemo {
    public static void main(String[] args) throws Exception{
        MBank aliiaMbank = new MBank(123456789111L, 444444444);

        System.out.println(aliiaMbank.getBalance());
        aliiaMbank.deposit(100000);
        System.out.println("--------");
        System.out.println(aliiaMbank.getBalance());

        Optima ulugbekOptima = new Optima(123456789122L, 555555555);
        System.out.println(ulugbekOptima.getBalance());

        Bank.transferFunds(aliiaMbank, ulugbekOptima, 500);
        System.out.println(aliiaMbank.getBalance());
        System.out.println(ulugbekOptima.getBalance());
        System.out.println("--------");

        DemirBank zinaidaDemir = new DemirBank(123333344555L, 666666666);
        System.out.println(zinaidaDemir.getBalance());

//        zinaidaDemir.withDraw(5000000);
        System.out.println("--------");
        BankBase.allBankRecords.stream().filter((n->n.getBalance() > 100)).forEach(System.out::println);
        System.out.println("--------");
        double allSumBanks = BankBase.allBankRecords.stream().mapToDouble(Bank::getBalance).sum();
        System.out.println(allSumBanks);



    }
}






