public class TestAccountEnhanced {

    private static String pinStatus(Account account) {
        return account.isPinSet() ? "Yes" : "No";
    }

    private static void printAccount(Account account) {
        System.out.printf(
                "Account #%d | %s (%d yrs) | %s | ₹%.1f | %s | PIN: %s%n",
                account.getAccountNumber(),
                account.getName(),
                account.getAge(),
                account.getAccountType(),
                account.getBalance(),
                account.getStatus(),
                pinStatus(account));
    }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("ENHANCED ACCOUNT TEST (BOOLEAN RETURNS)");
        System.out.println("============================================================");

        System.out.println();
        System.out.println(">>> Test 1: Valid Account Creation");
        Account acc1 = new Account(1001, "John Doe", 25, 1000.0, "Savings");
        printAccount(acc1);

        System.out.println();
        System.out.println(">>> Test 2: Invalid Age (under 18)");
        System.out.println("Creating account with age 16");
        Account acc2 = new Account(1002, "Young Kid", 16, 500.0, "Savings");
        System.out.println("Age auto-corrected to: " + acc2.getAge());
        printAccount(acc2);

        System.out.println();
        System.out.println(">>> Test 3: Invalid Account Type");
        System.out.println("Creating account with type \"Invalid\"");
        Account acc3 = new Account(1003, "Test User", 25, 500.0, "Invalid");
        System.out.println("Account type defaulted to: " + acc3.getAccountType());
        printAccount(acc3);

        System.out.println();
        System.out.println(">>> Test 4: Minimum Balance Enforcement on Creation");
        System.out.println("Creating Savings account with ₹300 (below minimum)");
        Account acc4 = new Account(1004, "Bob Wilson", 25, 300.0, "Savings");
        System.out.println("Balance auto-corrected to minimum: ₹" + acc4.getBalance());
        printAccount(acc4);

        System.out.println();
        System.out.println(">>> Test 5: Withdrawal with Minimum Balance");
        Account acc5 = new Account(1005, "Alice Brown", 30, 1000.0, "Current");
        acc5.setPin(4321);
        System.out.println("Initial: ");
        printAccount(acc5);
        System.out.println("Withdrawing ₹200.0: " + (acc5.withdraw(200.0) ? "SUCCESS" : "FAILED"));
        System.out.println("New balance: ₹" + acc5.getBalance());
        System.out.println("After withdrawal: ");
        printAccount(acc5);
        System.out.println("Withdrawing ₹900.0 (would leave ₹-100): " + (acc5.withdraw(900.0) ? "SUCCESS" : "FAILED (Minimum balance violation)"));
        System.out.println("Current balance: ₹" + acc5.getBalance());

        System.out.println();
        System.out.println(">>> Test 6: Account Status Management");
        Account acc6 = new Account(1006, "Charlie Green", 35, 2000.0, "Savings");
        System.out.println("Initial: ");
        printAccount(acc6);
        System.out.println("Closing account: " + (acc6.closeAccount() ? "SUCCESS" : "FAILED"));
        System.out.println("After close: ");
        printAccount(acc6);
        System.out.println("Depositing ₹500.0 to closed account: " + (acc6.deposit(500.0) ? "SUCCESS" : "FAILED (Account inactive)"));
        System.out.println("Reopening account: " + (acc6.reopenAccount() ? "SUCCESS" : "FAILED"));
        System.out.println("After reopen: ");
        printAccount(acc6);

        System.out.println();
        System.out.println(">>> Test 7: PIN Protection");
        Account acc7 = new Account(1007, "Diana Prince", 28, 1500.0, "Savings");
        System.out.println("Setting PIN 1234: " + (acc7.setPin(1234) ? "SUCCESS" : "FAILED"));
        System.out.println("Withdrawing ₹200.0 with correct PIN (1234): " + (acc7.withdraw(200.0, 1234) ? "SUCCESS" : "FAILED"));
        System.out.println("New balance: ₹" + acc7.getBalance());
        System.out.println("Withdrawing ₹100.0 with incorrect PIN (9999): " + (acc7.withdraw(100.0, 9999) ? "SUCCESS" : "FAILED (Incorrect PIN)"));
        Account acc8 = new Account(1008, "No PIN User", 28, 1500.0, "Savings");
        System.out.println("Withdrawing ₹100.0 with PIN not set: " + (acc8.withdraw(100.0, 0000) ? "SUCCESS" : "FAILED (PIN not set)"));

        System.out.println();
        System.out.println(">>> Test 8: All Accounts Summary");
        printAccount(acc1);
        printAccount(acc2);
        printAccount(acc3);
        printAccount(acc4);
        printAccount(acc5);
        printAccount(acc6);
        printAccount(acc7);

        System.out.println("============================================================");
        System.out.println("ENHANCED TEST COMPLETED!");
        System.out.println("============================================================");
    }
}
