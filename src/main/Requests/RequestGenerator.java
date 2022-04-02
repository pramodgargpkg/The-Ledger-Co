package main.Requests;

import main.Resources.ErrorMessages;
import main.Resources.IDataStore;
import main.Resources.InMemoryDataStore;
import main.Service.BalanceService;
import main.Service.IRequestService;
import main.Service.LoanService;
import main.Service.PaymentService;

public class RequestGenerator {
    private static final String loan = "LOAN";
    private static final String payment = "PAYMENT";
    private static final String balance = "BALANCE";

    public static IRequestService getService(String command) throws Exception {
        IRequestService request = null;
        IDataStore dataStore = new InMemoryDataStore();
        String[] args = command.split(" ");
        if (args != null) {
            switch (args[0]) {
                case loan:
                    request = getLoanService(args, dataStore);
                    break;
                case payment:
                    request = getPaymentService(args, dataStore);
                    break;
                case balance:
                    request = getBalanceService(args, dataStore);
                    break;
                default:
                    throw new Exception(ErrorMessages.InvalidCommand());
            }
        }
        return request;
    }

    private static LoanService getLoanService(String[] args, IDataStore dataStore) {
        var bankName = args[1];
        var borrowerName = args[2];
        var principal = args[3];
        double principalAmount = Double.parseDouble(principal);
        var tenure = args[4];
        int loanTenure = Integer.parseInt(tenure);
        var rateOfInterest = args[5];
        double roi = Double.parseDouble(rateOfInterest);

        LoanRequest loanRequest = new LoanRequest(bankName, borrowerName, principalAmount, loanTenure, roi);
        LoanService loanService = new LoanService(loanRequest, dataStore);
        return loanService;
    }

    private static PaymentService getPaymentService(String[] args, IDataStore dataStore) {
        var bankName = args[1];
        var borrowerName = args[2];
        var amount = args[3];
        double lumpSumAmount = Double.parseDouble(amount);
        var emi = args[4];
        int emiNo = Integer.parseInt(emi);

        PaymentRequest paymentRequest = new PaymentRequest(bankName, borrowerName, lumpSumAmount, emiNo);
        PaymentService paymentService = new PaymentService(paymentRequest, dataStore);
        return paymentService;
    }

    private static BalanceService getBalanceService(String[] args, IDataStore dataStore) {
        var bankName = args[1];
        var borrowerName = args[2];
        var emi = args[3];
        int emiNo = Integer.parseInt(emi);

        BalanceRequest balanceRequest = new BalanceRequest(bankName, borrowerName, emiNo);
        BalanceService balanceService = new BalanceService(balanceRequest, dataStore);
        return balanceService;
    }

}
