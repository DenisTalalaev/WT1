public class Transaction {
    private  int amount;
    private String walletTo;
    private String walletFrom;

    public Transaction(int amount, String walletTo, String walletFrom) {
        this.amount = amount;
        this.walletTo = walletTo;
        this.walletFrom = walletFrom;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getWalletTo() {
        return walletTo;
    }

    public void setWalletTo(String walletTo) {
        this.walletTo = walletTo;
    }

    public String getWalletFrom() {
        return walletFrom;
    }

    public void setWalletFrom(String walletFrom) {
        this.walletFrom = walletFrom;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", walletTo='" + walletTo + '\'' +
                ", walletFrom='" + walletFrom + '\'' +
                '}';
    }
}
