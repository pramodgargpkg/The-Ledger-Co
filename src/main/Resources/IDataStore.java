package main.Resources;

import main.Models.LoanDetail;
import main.Models.Payment;

public interface IDataStore {
    boolean saveLoanDetails(LoanDetail loanDetail);

    LoanDetail getLoanDetails(String bankName, String borrowerName);

    boolean savePayment(String bankName, String borrowerName, Payment payment);

}
