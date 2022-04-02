package Requests;

import Models.LoanDetail;

public class LoanRequest extends BaseRequest {

    public String bankName;
    public String borrowerName;
    public double principalAmount;
    public int duration;
    public double rateOfInterest;

    public LoanRequest(String bankName, String borrowerName, double principalAmount, int duration, double interest) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principalAmount = principalAmount;
        this.duration = duration;
        this.rateOfInterest = interest;
    }

    @Override
    public boolean validate() {
        return true;
    }

    public LoanDetail toLoanDetailModel() {
        return new LoanDetail(this.bankName, this.borrowerName, this.principalAmount, this.rateOfInterest, this.duration);
    }
}
