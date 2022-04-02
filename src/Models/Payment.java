package Models;

public class Payment {
    public double amount;
    public int emiNo;

    public Payment(double amount, int emiNo) {
        this.amount = amount;
        this.emiNo = emiNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getEmiNo() {
        return emiNo;
    }

    public void setEmiNo(int emiNo) {
        this.emiNo = emiNo;
    }
}
