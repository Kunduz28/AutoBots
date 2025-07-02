package com.autobots.bankApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Demo {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, Client> clients = new HashMap<>();

    public static void main(String[] args) {

        Client asan = new Client("Asan Uson", "123");

        DepositAccount asanDepositAccount = new DepositAccount(asan, Currency.USD);
        CreditAccount asanCreditAccount = new CreditAccount(asan, Currency.EUR);

        asan.addAccount(asanDepositAccount);
        asan.addAccount(asanCreditAccount);
        System.out.println(asan.getClientID());

        clients.put(asan.getClientID(), asan);

        System.out.println("====Welcome to Mbank====");
        System.out.print("Please enter client ID");
        String clientID = scanner.nextLine();
        Client client = clients.get(clientID);

        if (client == null) {
            System.out.println("Client was not found");
            return;
        }
        System.out.print("Please enter pin");
        String pinCode = scanner.nextLine();
        if (!client.authenticate(pinCode)) {
            System.out.println("You entered wrong pin");
            return;
        }
        System.out.println(client.getFullName() + " welcome to Mbank");
        while (true) {
            System.out.println("1. View accounts");
            System.out.println("2. Perevesti mezhdu schetami");
            System.out.println("3. Pokazat' chek");
            System.out.println("0. Exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    for (BankAccount bankAccount : client.getAccounts()) {
                        System.out.printf("%s | %s | Баланс: %.2f %s\n",
                                bankAccount.getClass().getSimpleName(),
                                bankAccount.getAccountNumber(),
                                bankAccount.getBalance(), bankAccount.getCurrency());
                    }
                    break;

                case "2":
                    System.out.print("Please enter account number of sender");
                    String fromID = scanner.nextLine();
                    System.out.println("Please enter account number of recipient");
                    String toID = scanner.nextLine();
                    System.out.println("Sum: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    BankAccount from = findAccount(fromID);
                    BankAccount to = findAccount(toID);
                    if (from != null && to != null) {
                        boolean result = BankService.transfer(from, to, amount);
                        System.out.println(result ? "Operation is successfully done" : "Error");
                    } else {
                        System.out.println("Accounts are not found");
                    }
                    break;

                case "3":
                    for (BankAccount bankAccount : client.getAccounts()) {
                        System.out.println("\n == check po schetu " + bankAccount.getAccountNumber());
                        for (Transaction transaction : bankAccount.getTransactions()) {
                            System.out.println(transaction);
                        }
                    }
                    break;

                case "0":
                    System.out.println("Quit...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

        private static BankAccount findAccount (String accountNumber){
            for (Client client : clients.values()) {
                for (BankAccount bankAccount : client.getAccounts()) {
                    if (bankAccount.getAccountNumber().equals(accountNumber)) {
                        return bankAccount;
                    }
                }
            }
            return null;
        }

    }
