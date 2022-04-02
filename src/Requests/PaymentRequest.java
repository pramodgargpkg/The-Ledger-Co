package Requests;

import Models.Payment;

public class PaymentRequest extends BaseRequest {

    public String bankName;
    public String borrowerName;
    public double lumpSumAmount;
    public int emiNo;

    public PaymentRequest(String bankName, String borrowerName, double lumpSumAmount, int emiNo) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.lumpSumAmount = lumpSumAmount;
        this.emiNo = emiNo;
    }

    public Payment toPaymentModel() {
        return new Payment(this.lumpSumAmount, this.emiNo);
    }

    @Override
    public boolean validate() {
        return true;
    }
}
