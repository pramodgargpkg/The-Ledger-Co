package Response;

public class BalanceResponse extends BaseResponse {
    public String bankName;
    public String borrowerName;
    public double amountPaid;
    public int remainingEmis;

    public BalanceResponse(String bankName, String borrowerName, double amountPaid, int remainigEmis, boolean success,String message) {
        super(success,message);
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.amountPaid = amountPaid;
        this.remainingEmis = remainigEmis;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getRemainingEmis() {
        return remainingEmis;
    }

    public void setRemainingEmis(int remainingEmis) {
        this.remainingEmis = remainingEmis;
    }
}
