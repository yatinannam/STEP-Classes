public class TestAccountSubclasses {

    private static void printAccount(Account account) {
        System.out.printf("Account #%d | %s (%d yrs) | %s | ₹%.1f | %s | PIN: %s%n",
                account.getAccountNumber(), account.getName(), account.getAge(),
                account.getAccountType(), account.getBalance(), account.getStatus(),
                account.hasPin() ? "Yes" : "No");
    }

    private static void printAccountSummary(Account account) {
        System.out.printf("Account #%d | %s (%d yrs) | %s | ₹%.1f | %s | Type: %s, Min Balance: ₹%.1f%n",
                account.getAccountNumber(), account.getName(), account.getAge(),
                account.getAccountType(), account.getBalance(), account.getStatus(),
                account.getAccountType(), account.getMinimumBalance());
    }

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println(" ACCOUNT SUBCLASSES TEST (SAVINGS & CURRENT)");
        System.out.println("============================================================");

        SavingsAccount savings = null;
        CurrentAccount current = null;

        System.out.println(">>> Test 1: Creating Accounts");
        try {
            savings = new SavingsAccount(1001, "John Doe", 25, 1000.0);
            current = new CurrentAccount(1002, "Jane Smith", 30, 2000.0);
            System.out.print("Savings Account: ");
            printAccount(savings);
            System.out.print("Current Account: ");
            printAccount(current);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println(">>> Test 2: Account Type and Minimum Balance");
        if (savings != null && current != null) {
            System.out.printf("Savings Account - Type: %s, Minimum Balance: ₹%.1f%n",
                    savings.getAccountType(), savings.getMinimumBalance());
            System.out.printf("Current Account - Type: %s, Minimum Balance: ₹%.1f%n",
                    current.getAccountType(), current.getMinimumBalance());
        }

        System.out.println(">>> Test 3: Savings Account - Interest Calculation");
        if (savings != null) {
            try {
                System.out.print("Savings Account: ");
                printAccountWithoutPin(savings);
                System.out.println("Interest Rate: " + savings.getInterestRate() + "% per annum");
                System.out.println("Interest for 1 year: ₹" + savings.calculateInterest(1));
                System.out.println("Interest for 2 years: ₹" + savings.calculateInterest(2));
                System.out.println("Interest for 5 years: ₹" + savings.calculateInterest(5));
                System.out.println("After 2 years with interest: Balance would be ₹"
                        + (savings.getBalance() + savings.calculateInterest(2)));
            } catch (Exception e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }
        }

        System.out.println(">>> Test 4: Current Account - Overdraft Feature");
        if (current != null) {
            try {
                current.setPin(1234);
                System.out.print("Current Account: ");
                printAccountWithoutPin(current);
                System.out.println("Overdraft Limit: ₹" + current.getOverdraftLimit());
                System.out.println("Available Overdraft: ₹" + current.getAvailableOverdraft());
                System.out.println("Overdraft Used: ₹" + current.getOverdraftUsed());
                System.out.println("Is Using Overdraft: " + current.isUsingOverdraft());
                System.out.println("Withdrawing ₹1500.0 (goes below minimum balance of ₹1000)");
                System.out.println("Balance before: ₹" + current.getBalance());
                current.withdraw(1500.0, 1234);
                System.out.println("Withdrawing: ₹1500.0 - SUCCESS");
                System.out.println("Balance after: ₹" + current.getBalance());
                System.out.println("Overdraft Used: ₹" + current.getOverdraftUsed());
                System.out.println("Available Overdraft: ₹" + current.getAvailableOverdraft());
                System.out.println("Is Using Overdraft: " + current.isUsingOverdraft());

                double availableFunds = current.getBalance() + current.getAvailableOverdraft();
                System.out.println("Attempting to withdraw ₹6000.0 (would exceed overdraft)");
                System.out.println("Available funds: ₹" + current.getBalance()
                        + " (balance) + ₹" + current.getAvailableOverdraft()
                        + " (overdraft) = ₹" + availableFunds);
                try {
                    current.withdraw(6000.0, 1234);
                    System.out.println("Withdrawing ₹6000.0 - SUCCESS");
                } catch (Exception e) {
                    System.out.println("EXCEPTION: " + e.getMessage());
                }

                System.out.println("Repaying overdraft of ₹500.0");
                System.out.println("Balance before repayment: ₹" + current.getBalance());
                System.out.println("Overdraft Used before: ₹" + current.getOverdraftUsed());
                current.repayOverdraft(500.0);
                System.out.println("Repaying ₹500.0 - SUCCESS");
                System.out.println("Balance after repayment: ₹" + current.getBalance());
                System.out.println("Overdraft Used after: ₹" + current.getOverdraftUsed());
                System.out.println("Is Using Overdraft: " + current.isUsingOverdraft());
            } catch (Exception e) {
                System.out.println("EXCEPTION: " + e.getMessage());
            }
        }

        System.out.println(">>> Test 5: Polymorphism - Treating Accounts Uniformly");
        try {
            Account[] accounts = {
                    savings,
                    current,
                    new SavingsAccount(1003, "Bob Wilson", 35, 500.0),
                    new CurrentAccount(1004, "Alice Brown", 28, 1500.0)
            };
            System.out.println("Processing accounts polymorphically:");
            double totalBalance = 0.0;
            int totalAccounts = 0;
            for (Account account : accounts) {
                if (account != null) {
                    printAccountSummary(account);
                    totalBalance += account.getBalance();
                    totalAccounts++;
                }
            }
            System.out.println("Total accounts: " + totalAccounts);
            System.out.println("Total balance across all accounts: ₹" + totalBalance);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println(">>> Test 6: Validation - Invalid Creation Attempts");
        try {
            System.out.println("Attempting to create SavingsAccount with ₹300 (below minimum)");
            new SavingsAccount(1007, "Invalid Savings", 25, 300.0);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        try {
            System.out.println("Attempting to create CurrentAccount with ₹500 (below minimum)");
            new CurrentAccount(1008, "Invalid Current", 25, 500.0);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        try {
            System.out.println("Attempting to create SavingsAccount with age 16");
            new SavingsAccount(1009, "Invalid Age", 16, 500.0);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println(">>> Test 7: Savings Account - PIN and Operations");
        try {
            SavingsAccount account = new SavingsAccount(1005, "Charlie Green", 40, 2000.0);
            System.out.print("Savings Account: ");
            printAccountWithoutPin(account);
            account.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            account.deposit(500.0);
            System.out.println("Depositing ₹500.0: SUCCESS");
            System.out.println("Balance after deposit: ₹" + account.getBalance());
            account.withdraw(300.0, 1234);
            System.out.println("Withdrawing ₹300.0 with correct PIN: SUCCESS");
            System.out.println("Balance after withdrawal: ₹" + account.getBalance());
            try {
                account.withdraw(2000.0, 1234);
            } catch (Exception e) {
                System.out.println("Attempting to withdraw ₹2000.0 (would violate minimum balance)");
                System.out.println("EXCEPTION: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println(">>> Test 8: Current Account - Active Status Operations");
        try {
            CurrentAccount account = new CurrentAccount(1006, "Diana Prince", 35, 3000.0);
            System.out.print("Current Account: ");
            printAccountWithoutPin(account);
            account.closeAccount();
            System.out.println("Closing account: SUCCESS");
            try {
                account.deposit(100.0);
            } catch (Exception e) {
                System.out.println("Attempting to deposit ₹100.0 on closed account");
                System.out.println("EXCEPTION: " + e.getMessage());
            }
            account.reopenAccount();
            System.out.println("Reopening account: SUCCESS");
            account.deposit(100.0);
            System.out.println("Depositing ₹100.0 after reopen: SUCCESS");
            System.out.println("Balance after reopen: ₹" + account.getBalance());
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println(">>> Test 9: All Accounts Summary");
        try {
            Account[] accounts = {
                    savings,
                    current,
                    new SavingsAccount(1003, "Bob Wilson", 35, 500.0),
                    new CurrentAccount(1004, "Alice Brown", 28, 1500.0),
                    new SavingsAccount(1005, "Charlie Green", 40, 2200.0),
                    new CurrentAccount(1006, "Diana Prince", 35, 3100.0)
            };
            for (Account account : accounts) {
                printAccount(account);
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

        System.out.println("============================================================");
        System.out.println(" TEST COMPLETED!");
        System.out.println("============================================================");
    }

    private static void printAccountWithoutPin(Account account) {
        System.out.printf("Account #%d | %s (%d yrs) | %s | ₹%.1f | %s%n",
                account.getAccountNumber(), account.getName(), account.getAge(),
                account.getAccountType(), account.getBalance(), account.getStatus());
    }
}
