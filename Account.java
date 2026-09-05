public abstract class Account {
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String status;
    private Integer pin;

    public abstract double getMinimumBalance();

    public abstract String getAccountType();

    public Account(int accountNumber, String name, int age,
            double initialBalance) {
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Customer must be at least 18 years old. Provided: " + age);
        }

        if (initialBalance < getMinimumBalance()) {
            throw new IllegalArgumentException(getAccountType()
                    + " account requires minimum balance of ₹" + getMinimumBalance()
                    + ". Provided: ₹" + initialBalance);
        }

        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.status = "Active";
        this.pin = null;
    }

    public void deposit(double amount)
            throws InvalidAmountException, InactiveAccountException {
        validateActive();

        validateAmount(amount);

        balance += amount;
    }

    public void withdraw(double amount, int pin)
            throws InvalidAmountException,
            InsufficientBalanceException,
            MinimumBalanceViolationException,
            InactiveAccountException,
            InvalidPinException {
        validateActive();

        validateAmount(amount);
        validatePin(pin);

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }

        double remainingBalance = balance - amount;
        double minimumBalance = getMinimumBalance();
        if (remainingBalance < minimumBalance) {
            throw new MinimumBalanceViolationException(
                    "Withdrawal would violate minimum balance requirement of " + minimumBalance);
        }

        setBalance(remainingBalance);
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

    protected void validateActive() throws InactiveAccountException {
        if (!"Active".equalsIgnoreCase(status)) {
            throw new InactiveAccountException("Account is inactive");
        }
    }

    protected void validatePin(int enteredPin) throws InvalidPinException {
        if (pin == null) {
            throw new InvalidPinException("PIN is not set");
        }
        if (!pin.equals(enteredPin)) {
            throw new InvalidPinException("Incorrect PIN");
        }
    }

    protected void validateAmount(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
    }

    protected void setBalance(double balance) {
        this.balance = balance;
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

    public String getStatus() {
        return status;
    }

    public Integer getPin() {
        return pin;
    }

    public static void main(String[] args) {
        try {
            Account account = new SavingsAccount(1001, "Yatin", 25, 1500.0);
            account.setPin(1234);
            account.deposit(500.0);
            account.withdraw(200.0, 1234);
            System.out.println("Balance: " + account.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
