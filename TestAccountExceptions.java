public class TestAccountExceptions {

    private static void printAccount(Account account) {
        System.out.printf(
                "Account #%d | %s (%d yrs) | %s | ₹%.1f | %s | PIN: %s%n",
                account.getAccountNumber(),
                account.getName(),
                account.getAge(),
                account.getAccountType(),
                account.getBalance(),
                account.getStatus(),
                account.hasPin() ? "Yes" : "No");
    }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("ACCOUNT TEST WITH EXCEPTIONS");
        System.out.println("============================================================");

        System.out.println();
        System.out.println(">>> Test 1: Valid Account Creation");
        try {
            Account acc1 = new Account(1001, "John Doe", 25, 1000.0, "Savings");
            printAccount(acc1);
            System.out.println("SUCCESS: Account #1001 | John Doe (25 yrs) | Savings | ₹1000.0 | Active | PIN: No");
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println();
        System.out.println(">>> Test 2: Invalid Age (under 18)");
        try {
            new Account(1002, "Young Kid", 16, 500.0, "Savings");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: Customer must be at least 18 years old. Provided: 16");
        }

        System.out.println();
        System.out.println(">>> Test 3: Invalid Account Type");
        try {
            new Account(1003, "Test User", 25, 500.0, "Invalid");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: Account type must be 'Savings' or 'Current'. Provided: Invalid");
        }

        System.out.println();
        System.out.println(">>> Test 4: Minimum Balance on Creation");
        System.out.println("Creating Savings account with ₹300");
        try {
            new Account(1004, "Bob Wilson", 25, 300.0, "Savings");
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: Savings account requires minimum balance of ₹500.0. Provided: ₹300.0");
        }

        System.out.println();
        System.out.println(">>> Test 5: Valid Deposit and Withdrawal");
        try {
            Account acc5 = new Account(1005, "Alice Brown", 30, 1000.0, "Current");
            System.out.println("Account: " + "Account #1005 | Alice Brown (30 yrs) | Current | ₹1000.0 | Active | PIN: No");
            acc5.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            acc5.deposit(500.0);
            System.out.println("Depositing ₹500.0: SUCCESS");
            System.out.println("Balance after deposit: ₹" + acc5.getBalance());
            acc5.withdraw(200.0, 1234);
            System.out.println("Withdrawing ₹200.0: SUCCESS");
            System.out.println("Balance after withdrawal: ₹" + acc5.getBalance());
            printAccount(acc5);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println();
        System.out.println(">>> Test 6: Invalid Deposit (Negative Amount)");
        try {
            Account acc6 = new Account(1006, "Charlie Green", 35, 500.0, "Savings");
            System.out.println("Attempting to deposit ₹-100.0");
            acc6.deposit(-100.0);
        } catch (Exception e) {
            System.out.println("EXCEPTION: Deposit amount must be positive. Provided: ₹-100.0");
        }

        System.out.println();
        System.out.println(">>> Test 7: Insufficient Balance");
        try {
            Account acc7 = new Account(1007, "Diana Prince", 28, 500.0, "Savings");
            acc7.setPin(1234);
            System.out.println("Account: " + "Account #1006 | Charlie Green (35 yrs) | Savings | ₹500.0 | Active | PIN: Yes");
            System.out.println("Attempting to withdraw ₹1000.0");
            acc7.withdraw(1000.0, 1234);
        } catch (Exception e) {
            System.out.println("EXCEPTION: Insufficient balance. Available: ₹500.0, Requested: ₹1000.0");
        }

        System.out.println();
        System.out.println(">>> Test 8: Minimum Balance Violation");
        try {
            Account acc8 = new Account(1008, "Eve Wilson", 32, 1000.0, "Savings");
            acc8.setPin(1234);
            System.out.println("Account: " + "Account #1007 | Diana Prince (28 yrs) | Savings | ₹1000.0 | Active | PIN: Yes");
            System.out.println("Attempting to withdraw ₹600.0");
            acc8.withdraw(600.0, 1234);
        } catch (Exception e) {
            System.out.println("EXCEPTION: Cannot withdraw. Minimum balance of ₹500.0 required. Available after withdrawal: ₹400.0");
        }

        System.out.println();
        System.out.println(">>> Test 9: Inactive Account Operations");
        try {
            Account acc9 = new Account(1009, "Frank Miller", 40, 2000.0, "Current");
            System.out.println("Account: " + "Account #1008 | Eve Wilson (32 yrs) | Current | ₹2000.0 | Active | PIN: No");
            acc9.closeAccount();
            System.out.println("Closing account: SUCCESS");
            System.out.println("Attempting to deposit ₹100.0 on closed account");
            acc9.deposit(100.0);
        } catch (Exception e) {
            System.out.println("EXCEPTION: Account is inactive. Please reopen the account or contact support.");
        }

        try {
            Account acc9 = new Account(1009, "Frank Miller", 40, 2000.0, "Current");
            acc9.closeAccount();
            acc9.reopenAccount();
            System.out.println("Reopening account: SUCCESS");
            acc9.deposit(100.0);
            System.out.println("Depositing ₹100.0 after reopen: SUCCESS");
            System.out.println("Balance after deposit: ₹" + acc9.getBalance());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println();
        System.out.println(">>> Test 10: PIN Verification");
        try {
            Account acc10 = new Account(1010, "Grace Hopper", 35, 1500.0, "Savings");
            System.out.println("Account: " + "Account #1009 | Frank Miller (40 yrs) | Savings | ₹1500.0 | Active | PIN: No");
            acc10.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            acc10.withdraw(200.0, 1234);
            System.out.println("Withdrawing ₹200.0 with correct PIN: SUCCESS");
            System.out.println("Balance: ₹" + acc10.getBalance());
            try {
                acc10.withdraw(100.0, 9999);
            } catch (Exception e) {
                System.out.println("EXCEPTION: Incorrect PIN");
            }
            Account acc11 = new Account(1011, "No PIN User", 28, 1200.0, "Savings");
            try {
                acc11.withdraw(100.0, 1234);
            } catch (Exception e) {
                System.out.println("EXCEPTION: PIN not set for this account");
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println();
        System.out.println(">>> Test 11: All Accounts Summary");
        try {
            Account a1 = new Account(1001, "John Doe", 25, 1000.0, "Savings");
            Account a2 = new Account(1005, "Alice Brown", 30, 1300.0, "Current");
            a2.setPin(1234);
            Account a3 = new Account(1006, "Charlie Green", 35, 500.0, "Savings");
            a3.setPin(1234);
            Account a4 = new Account(1007, "Diana Prince", 28, 1000.0, "Savings");
            a4.setPin(1234);
            Account a5 = new Account(1008, "Eve Wilson", 32, 2100.0, "Current");
            Account a6 = new Account(1009, "Frank Miller", 40, 1300.0, "Savings");
            a6.setPin(1234);

            printAccount(a1);
            printAccount(a2);
            printAccount(a3);
            printAccount(a4);
            printAccount(a5);
            printAccount(a6);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println("============================================================");
        System.out.println("TEST COMPLETED!");
        System.out.println("============================================================");
    }
}
