public class Bank {

    private Account[] accounts;
    private int totalAccounts;

    public Bank(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Bank capacity must be greater than 0");
        }
        this.accounts = new Account[capacity];
        this.totalAccounts = 0;
    }

    public boolean addAccount(Account account) {
        if (account == null || totalAccounts >= accounts.length) {
            return false;
        }

        for (int i = 0; i < totalAccounts; i++) {
            if (accounts[i].getAccountNumber() == account.getAccountNumber()) {
                return false;
            }
        }

        accounts[totalAccounts] = account;
        totalAccounts++;
        return true;
    }

    public Account findAccount(int accountNumber) {
        for (int i = 0; i < totalAccounts; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    public boolean deposit(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        return account.deposit(amount);
    }

    public boolean withdraw(int accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            return false;
        }
        return account.withdraw(amount);
    }

    public boolean removeAccount(int accountNumber) {
        for (int i = 0; i < totalAccounts; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                for (int j = i; j < totalAccounts - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                accounts[totalAccounts - 1] = null;
                totalAccounts--;
                return true;
            }
        }
        return false;
    }

    public double getTotalBalance() {
        double total = 0;
        for (int i = 0; i < totalAccounts; i++) {
            total += accounts[i].getBalance();
        }
        return total;
    }

    public void displayAllAccounts() {
        if (totalAccounts == 0) {
            System.out.println("No accounts available in the bank.");
            return;
        }

        System.out.println("Bank Accounts:");
        for (int i = 0; i < totalAccounts; i++) {
            System.out.println("------------------------");
            System.out.println("Account Number: " + accounts[i].getAccountNumber());
            System.out.println("Name: " + accounts[i].getName());
            System.out.println("Account Type: " + accounts[i].getAccountType());
            System.out.println("Balance: " + accounts[i].getBalance());
            System.out.println("Status: " + accounts[i].getStatus());
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank(5);

        Account account1 = new Account(1001, "Yatin Annam", 19, 5000.0, "Savings");
        Account account2 = new Account(1002, "Riya Sharma", 20, 2500.0, "Current");
        Account account3 = new Account(1003, "Aman Verma", 21, 7000.0, "Savings");

        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);

        System.out.println("Total balance before transactions: " + bank.getTotalBalance());
        System.out.println("Deposit to account 1002: " + bank.deposit(1002, 1500.0));
        System.out.println("Withdraw from account 1001: " + bank.withdraw(1001, 1000.0));

        System.out.println("Balance of account 1002: " + bank.findAccount(1002).getBalance());
        System.out.println("Balance of account 1001: " + bank.findAccount(1001).getBalance());

        System.out.println("Remove account 1003: " + bank.removeAccount(1003));
        bank.displayAllAccounts();
    }
}
