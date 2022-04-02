package main.Models;

import java.util.List;

public class LoanDetail {
    public String bankName;
    public String borrowerName;
    public double principalAmount;
    public double rateOfInterest;
    public int loanTenure;

    public List<Payment> payments;

    public LoanDetail(String bankName, String borrowerName, double principalAmount, double rateOfInterest, int loanTenure) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principalAmount = principalAmount;
        this.rateOfInterest = rateOfInterest;
        this.loanTenure = loanTenure;
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

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(long principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public int getDuration() {
        return loanTenure;
    }

    public void setDuration(int duration) {
        this.loanTenure = duration;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public double totalAmountToBeRepaid() {
        if (this.loanTenure > 0)
            return this.principalAmount + ((this.principalAmount * this.loanTenure * this.rateOfInterest) / 100);
        else
            return 0;
    }

    public double emiAmount() {
        var totalAmountToBeRepaid = totalAmountToBeRepaid();
        if (totalAmountToBeRepaid > 0)
            return Math.ceil(totalAmountToBeRepaid / (this.loanTenure * 12));
        else
            return 0;
    }

    public double lumpSumPaidTillEmiNumber(int emiNumber) {
        int lumpSumPaid = 0;
        if (this.payments != null && this.payments.size() > 0) {
            for (Payment payment : this.payments) {
                if (payment.emiNo <= emiNumber) {
                    lumpSumPaid += payment.amount;
                }
            }
        }
        return lumpSumPaid;
    }
}
