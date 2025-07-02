package com.autobots.bankApp;

import java.util.HashMap;
import java.util.Map;

public class BankProApp {

    private static final Map<String,Client> clients = new HashMap<>();
    public static void main(String[] args) {

        Client asan = new Client("Asan,Uson","123");

        DepositAccount asanDepositAccount = new DepositAccount(asan,Currency.USD);
        CreditAccount asanCreditAccount = new CreditAccount(asan,Currency.EUR);

        asan.addAccount(asanDepositAccount);
        asan.addAccount(asanCreditAccount);

        clients.put(asan.getClientID(), asan);
    }
}
