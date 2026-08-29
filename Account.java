public class Account {
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;

    public Account(int accountNumber, String name, int age,
                   double initialBalance, String accountType) {
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Age must be at least 18");
        }

        if (accountType == null || (!accountType.equalsIgnoreCase("Savings") && !accountType.equalsIgnoreCase("Current"))) {
            throw new IllegalArgumentException("Account type must be Savings or Current");
        }

        double minimumBalance = getMinimumBalanceForType(accountType);
        if (initialBalance < minimumBalance) {
            throw new IllegalArgumentException("Initial balance cannot be below minimum required balance");
        }

        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.status = "Active";
        this.pin = null;
    }

    public void deposit(double amount)
            throws InvalidAmountException, InactiveAccountException {
        validateActive();

        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero");
        }

        balance += amount;
    }

    public void withdraw(double amount, int pin)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   MinimumBalanceViolationException,
                   InactiveAccountException,
                   InvalidPinException {
        validateActive();

        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero");
        }

        if (this.pin == null) {
            throw new InvalidPinException("PIN is not set");
        }

        if (!this.pin.equals(pin)) {
            throw new InvalidPinException("Incorrect PIN");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }

        double remainingBalance = balance - amount;
        double minimumBalance = getMinimumBalanceForType(accountType);
        if (remainingBalance < minimumBalance) {
            throw new MinimumBalanceViolationException(
                    "Withdrawal would violate minimum balance requirement of " + minimumBalance);
        }

        balance = remainingBalance;
    }

    public void closeAccount() {
        if ("Inactive".equalsIgnoreCase(status)) {
            throw new IllegalStateException("Account is already closed");
        }
        status = "Inactive";
    }

    public void reopenAccount() {
        if ("Active".equalsIgnoreCase(status)) {
            throw new IllegalStateException("Account is already active");
        }
        status = "Active";
    }

    public void setPin(int pin) {
        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException("PIN must be a 4-digit number");
        }
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {
        return this.pin != null && this.pin.equals(pin);
    }

    public boolean hasPin() {
        return pin != null;
    }

    private double getMinimumBalanceForType(String type) {
        if (type.equalsIgnoreCase("Current")) {
            return MIN_BALANCE_CURRENT;
        }
        return MIN_BALANCE_SAVINGS;
    }

    private void validateActive() throws InactiveAccountException {
        if (!"Active".equalsIgnoreCase(status)) {
            throw new InactiveAccountException("Account is inactive");
        }
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

    public Integer getPin() {
        return pin;
    }

    public static void main(String[] args) {
        try {
            Account account = new Account(1001, "Yatin", 25, 1500.0, "Savings");
            account.setPin(1234);
            account.deposit(500.0);
            account.withdraw(200.0, 1234);
            System.out.println("Balance: " + account.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
