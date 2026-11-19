/**
 * Course: Object-Oriented Programming / Java Programming
 * Assignment: 2nd Assignment - Simple ATM Simulation
 * Student Name: Yasir Saifaddin Yousif Awadalla
 * Roll Number: 202510776
 * Institution: Kigali Independent University (ULK)
 */

import java.util.Scanner;

public class ATMSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        final int STORED_PIN = 1234;
        final int MAX_ATTEMPTS = 3;
        double balance = 1000.00;

       
        String[] transactionHistory = new String[100];
        int transactionCount = 0;

       
        int attempts = 0;
        boolean isAuthenticated = false;

        System.out.println("==========================================");
        System.out.println("        WELCOME TO THE ATM SYSTEM         ");
        System.out.println("==========================================");

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Please enter your 4-digit PIN: ");
            int enteredPin = scanner.nextInt();

            if (enteredPin == STORED_PIN) {
                isAuthenticated = true;
                System.out.println("Authentication successful.\n");
                break;
            } else {
                attempts++;
                int remaining = MAX_ATTEMPTS - attempts;
                if (remaining > 0) {
                    System.out.println("Incorrect PIN. Attempts remaining: " + remaining);
                }
            }
        }

       
        if (!isAuthenticated) {
            System.out.println("\nMaximum attempts reached. Access denied.");
            scanner.close();
            return;
        }

        
        int choice = 0;
        while (choice != 5) {
            System.out.println("---------------- MAIN MENU ----------------");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.println("-------------------------------------------");
            System.out.print("Select an option (1-5): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                  
                    System.out.printf("Current Account Balance: $%.2f%n%n", balance);
                    break;

                case 2:
                    
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();

                    if (depositAmount > 0) {
                        balance += depositAmount;
                        if (transactionCount < transactionHistory.length) {
                            transactionHistory[transactionCount] = String.format("Deposit: +$%.2f", depositAmount);
                            transactionCount++;
                        }
                        System.out.printf("Successfully deposited $%.2f%n%n", depositAmount);
                    } else {
                        System.out.println("Error: Deposit amount must be greater than zero.\n");
                    }
                    break;

                case 3:
                    
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();

                    if (withdrawAmount <= 0) {
                        System.out.println("Error: Withdrawal amount must be greater than zero.\n");
                    } else if (withdrawAmount > balance) {
                        System.out.println("Error: Insufficient balance for this transaction.\n");
                    } else {
                        balance -= withdrawAmount;
                        if (transactionCount < transactionHistory.length) {
                            transactionHistory[transactionCount] = String.format("Withdrawal: -$%.2f", withdrawAmount);
                            transactionCount++;
                        }
                        System.out.printf("Successfully withdrew $%.2f%n%n", withdrawAmount);
                    }
                    break;

                case 4:
                   
                    System.out.println("\n=========== TRANSACTION HISTORY ===========");
                    if (transactionCount == 0) {
                        System.out.println("No transactions recorded during this session.");
                    } else {
                        for (int i = 0; i < transactionCount; i++) {
                            System.out.println((i + 1) + ". " + transactionHistory[i]);
                        }
                    }
                    System.out.println("===========================================\n");
                    break;

                case 5:
                   
                    System.out.println("\nThank you for using the ATM. Session terminated.");
                    break;

                default:
                    System.out.println("Invalid selection. Please enter a number between 1 and 5.\n");
            }
        }

        scanner.close();
    }
}
