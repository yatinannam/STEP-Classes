public class Account {

    public static final double MIN_SAVINGS_BALANCE = 500.0;
    public static final double MIN_CURRENT_BALANCE = 0.0;
    public static final int MIN_AGE = 18;

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private int pin;
    private boolean pinSet;

    public Account(int accountNumber, String name, int age,
            double initialBalance, String accountType) {

        this.accountNumber = accountNumber;
        this.name = name;
        this.age = (age < MIN_AGE) ? MIN_AGE : age;
        this.accountType = normalizeAccountType(accountType);
        this.balance = enforceMinimumBalance(initialBalance, this.accountType);
        this.status = "Active";
        this.pin = 0;
        this.pinSet = false;
    }

    private String normalizeAccountType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return "Savings";
        }

        String normalized = type.trim();
        if (normalized.equalsIgnoreCase("Savings")) {
            return "Savings";
        }
        if (normalized.equalsIgnoreCase("Current")) {
            return "Current";
        }
        return "Savings";
    }

    private double minimumBalanceForType(String type) {
        if ("Current".equalsIgnoreCase(type)) {
            return MIN_CURRENT_BALANCE;
        }
        return MIN_SAVINGS_BALANCE;
    }

    private double enforceMinimumBalance(double value, String type) {
        double minimum = minimumBalanceForType(type);
        if (value < minimum) {
            return minimum;
        }
        return value;
    }

    public boolean deposit(double amount) {
        if (amount <= 0 || "Inactive".equalsIgnoreCase(status)) {
            return false;
        }

        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || "Inactive".equalsIgnoreCase(status)) {
            return false;
        }

        double newBalance = balance - amount;
        if (newBalance < minimumBalanceForType(accountType)) {
            return false;
        }

        balance = newBalance;
        return true;
    }

    public boolean withdraw(double amount, int pin) {
        if (!pinSet) {
            return false;
        }
        if (!verifyPin(pin)) {
            return false;
        }
        return withdraw(amount);
    }

    public boolean closeAccount() {
        if ("Inactive".equalsIgnoreCase(status)) {
            return false;
        }
        status = "Inactive";
        return true;
    }

    public boolean reopenAccount() {
        if ("Active".equalsIgnoreCase(status)) {
            return false;
        }
        status = "Active";
        return true;
    }

    public boolean setPin(int pin) {
        if (pin <= 0) {
            return false;
        }
        this.pin = pin;
        this.pinSet = true;
        return true;
    }

    public boolean verifyPin(int pin) {
        return pinSet && this.pin == pin;
    }

    public boolean isPinSet() {
        return pinSet;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setAge(int age) {
        this.age = (age < MIN_AGE) ? MIN_AGE : age;
    }

    public void setBalance(double balance) {
        this.balance = enforceMinimumBalance(balance, accountType);
    }

    public void setAccountType(String accountType) {
        this.accountType = normalizeAccountType(accountType);
        this.balance = enforceMinimumBalance(this.balance, this.accountType);
    }

    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status.trim();
        }
    }

    public void displayAccount() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Balance: " + balance);
        System.out.println("Account Type: " + accountType);
        System.out.println("Status: " + status);
    }

    public static void main(String[] args) {
        Account account = new Account(1001, "Yatin Annam", 19, 5000.0, "Savings");
        account.displayAccount();
        System.out.println();
        System.out.println("Deposit 2000: " + account.deposit(2000));
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Withdraw 1000: " + account.withdraw(1000));
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Withdraw 10000: " + account.withdraw(10000));
        System.out.println("Balance: " + account.getBalance());
    }
}
