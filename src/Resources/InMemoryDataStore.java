package Resources;

import Models.LoanDetail;
import Models.Payment;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDataStore implements IDataStore {
    private static final Map<Pair<String, String>, LoanDetail> loanRecords = new HashMap<>();

    @Override
    public boolean saveLoanDetails(LoanDetail loanDetail) {
        var loanRecordKey = GetLoanRecordKey(loanDetail.bankName, loanDetail.borrowerName);
        loanRecords.put(loanRecordKey, loanDetail);
        return true;
    }

    @Override
    public LoanDetail getLoanDetails(String bankName, String borrowerName) {
        var loanRecordKey = GetLoanRecordKey(bankName, borrowerName);
        var existingLoanDetails = GetLoanRecord(loanRecordKey);
        return existingLoanDetails;
    }

    @Override
    public boolean savePayment(String bankName, String borrowerName, Payment payment) {
        var loanRecordKey = GetLoanRecordKey(bankName, borrowerName);
        var existingLoanDetails = GetLoanRecord(loanRecordKey);

        if (existingLoanDetails == null)
            return false;

        if (existingLoanDetails.payments == null)
            existingLoanDetails.payments = new ArrayList<>();

        existingLoanDetails.payments.add(payment);
        return true;
    }

    public LoanDetail GetLoanRecord(Pair<String, String> loanRecordKey) {
        LoanDetail existingLoanDetails = null;
        if (loanRecords.containsKey(loanRecordKey)) {
            existingLoanDetails = loanRecords.get(loanRecordKey);
            return existingLoanDetails;
        }
        return existingLoanDetails;
    }

    private Pair<String, String> GetLoanRecordKey(String bankName, String borrowerName) {
        var loanRecordKey = new Pair<String, String>(bankName, borrowerName);
        return loanRecordKey;
    }


}
