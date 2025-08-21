import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = Math.max(initialBalance, 0);
    }

    public String deposit(double amount) {
        if (amount <= 0) return "❌ Deposit must be greater than 0.";
        balance += amount;
        return String.format("✅ Deposited $%.2f. New balance: $%.2f", amount, balance);
    }

    public String withdraw(double amount) {
        if (amount <= 0) return "❌ Withdrawal must be greater than 0.";
        if (amount > balance) return "❌ Insufficient funds.";
        balance -= amount;
        return String.format("✅ Withdrew $%.2f. New balance: $%.2f", amount, balance);
    }

    public String checkBalance() {
        return String.format("💰 Current balance: $%.2f", balance);
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n===== ATM MENU =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("Choose an option (1-4): ");
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println(account.checkBalance());
                    break;
                case "2":
                    System.out.print("Enter deposit amount: $");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        System.out.println(account.deposit(amount));
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid input. Please enter a number.");
                    }
                    break;
                case "3":
                    System.out.print("Enter withdrawal amount: $");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        System.out.println(account.withdraw(amount));
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid input. Please enter a number.");
                    }
                    break;
                case "4":
                    System.out.println("👋 Thank you for using the ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("❌ Invalid option. Please choose between 1 and 4.");
            }
        }
        scanner.close();
    }
}

public class ATMApp {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(100.0); // Initial balance
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
