package Service;

import Requests.LoanRequest;
import Resources.ErrorMessages;
import Resources.IDataStore;
import Response.BaseResponse;

public class LoanService implements IRequestService {

    private final LoanRequest loanRequest;
    private final IDataStore dataStore;

    public LoanService(LoanRequest loanRequest, IDataStore dataStore) {
        this.loanRequest = loanRequest;
        this.dataStore = dataStore;
    }

    @Override
    public BaseResponse service() throws Exception {
        var existingLoanDetails = dataStore.getLoanDetails(loanRequest.bankName, loanRequest.borrowerName);
        if (existingLoanDetails != null) {
            throw new Exception(ErrorMessages.LoanRecordExists());
        }

        var loanDetail = loanRequest.toLoanDetailModel();
        var isSaved = dataStore.saveLoanDetails(loanDetail);
        return new BaseResponse(isSaved, "");
    }
}
