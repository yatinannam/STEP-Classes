public class BankingApp {

    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount(
                1101,
                "Yatin Annam",
                19,
                5000.0);

        CurrentAccount current = new CurrentAccount(
                1102,
                "Aman Verma",
                20,
                3000.0);

        System.out.println("=== Savings Account ===");
        System.out.println("Initial Balance: " + savings.getBalance());
        try {
            savings.deposit(1000);
            System.out.println("After deposit: " + savings.getBalance());
            savings.applyInterest();
            System.out.println("After interest: " + savings.getBalance());
        } catch (Exception e) {
            System.out.println("Savings operation failed: " + e.getMessage());
        }
        savings.displaySavingsAccount();

        System.out.println();
        System.out.println("=== Current Account ===");
        System.out.println("Initial Balance: " + current.getBalance());
        current.setPin(1234);
        try {
            current.withdraw(4500, 1234);
            System.out.println("Withdraw 4500: SUCCESS");
        } catch (Exception e) {
            System.out.println("Withdraw 4500: FAILED");
        }
        System.out.println("Balance after withdrawal: " + current.getBalance());
        current.displayCurrentAccount();
    }
}
