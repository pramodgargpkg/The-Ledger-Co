package Service;

import Requests.PaymentRequest;
import Resources.ErrorMessages;
import Resources.IDataStore;
import Response.BaseResponse;

public class PaymentService implements IRequestService {
    private final PaymentRequest paymentRequest;
    private final IDataStore dataStore;

    public PaymentService(PaymentRequest paymentRequest, IDataStore dataStore) {
        this.paymentRequest = paymentRequest;
        this.dataStore = dataStore;
    }


    @Override
    public BaseResponse service() throws Exception {
        var existingLoanRecord = dataStore.getLoanDetails(paymentRequest.bankName, paymentRequest.borrowerName);
        if (existingLoanRecord == null)
            throw new Exception(ErrorMessages.LoanRecordNotFound());
        var totalValidEmis = existingLoanRecord.loanTenure * 12;
        if (paymentRequest.emiNo > totalValidEmis)
            throw new Exception(ErrorMessages.InvalidEmi());
        var payment = paymentRequest.toPaymentModel();
        var isSaved = dataStore.savePayment(paymentRequest.bankName, paymentRequest.borrowerName, payment);
        return new BaseResponse(isSaved, "");
    }
}
