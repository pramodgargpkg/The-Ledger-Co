package Resources;

import Models.LoanDetail;
import Models.Payment;

public interface IDataStore {
    boolean saveLoanDetails(LoanDetail loanDetail);

    LoanDetail getLoanDetails(String bankName, String borrowerName);

    boolean savePayment(String bankName, String borrowerName, Payment payment);

}
