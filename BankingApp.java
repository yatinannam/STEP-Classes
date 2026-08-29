public class BankingApp {

    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount(
                1101,
                "Yatin Annam",
                19,
                5000.0,
                4.5);

        CurrentAccount current = new CurrentAccount(
                1102,
                "Aman Verma",
                20,
                3000.0,
                1500.0);

        System.out.println("=== Savings Account ===");
        System.out.println("Initial Balance: " + savings.getBalance());
        savings.deposit(1000);
        System.out.println("After deposit: " + savings.getBalance());
        savings.applyInterest();
        System.out.println("After interest: " + savings.getBalance());
        savings.displaySavingsAccount();

        System.out.println();
        System.out.println("=== Current Account ===");
        System.out.println("Initial Balance: " + current.getBalance());
        System.out.println("Withdraw 4500: " + current.withdraw(4500));
        System.out.println("Balance after withdrawal: " + current.getBalance());
        current.displayCurrentAccount();
    }
}
