package main.Service;

import main.Requests.BalanceRequest;
import main.Resources.ErrorMessages;
import main.Resources.IDataStore;
import main.Response.BalanceResponse;
import main.Response.BaseResponse;

public class BalanceService implements IRequestService {
    private final IDataStore dataStore;
    private final BalanceRequest balanceRequest;

    public BalanceService(BalanceRequest balanceRequest, IDataStore dataStore) {
        this.dataStore = dataStore;
        this.balanceRequest = balanceRequest;
    }

    @Override
    public BaseResponse service() throws Exception {
        var existingLoanRecord = dataStore.getLoanDetails(balanceRequest.bankName, balanceRequest.borrowerName);
        if (existingLoanRecord == null)
            throw new Exception(ErrorMessages.LoanRecordNotFound());

        var emiAmount = existingLoanRecord.emiAmount();
        var totalAmountByEmi = balanceRequest.emiNo * emiAmount;
        var totalLumpSumPaid = existingLoanRecord.lumpSumPaidTillEmiNumber(balanceRequest.emiNo);

        var totalAmountPaidTillEmi = totalAmountByEmi + totalLumpSumPaid;

        var amountPending = existingLoanRecord.totalAmountToBeRepaid() - totalAmountPaidTillEmi;
        var remainingEmis = Math.ceil(amountPending / emiAmount);

        BalanceResponse balanceResponse = new BalanceResponse(existingLoanRecord.bankName,
                existingLoanRecord.borrowerName,
                (long) totalAmountPaidTillEmi,
                remainingEmis > 0 ? (int) remainingEmis : 0,
                true,
                ""
        );
        return balanceResponse;
    }
}
