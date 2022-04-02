package main.Requests;

public class BalanceRequest extends main.Requests.BaseRequest {

    public String bankName;
    public String borrowerName;
    public int emiNo;

    public BalanceRequest(String bankName, String borrowerName, int emiNo) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.emiNo = emiNo;
    }

    @Override
    public boolean validate() {
        return true;
    }
}
