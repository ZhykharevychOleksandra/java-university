package lab11.bank.proxy;
import bank.common.Log;

public class RealCreditBureau implements CreditBureau {
    private static final String SOURCE = "RealCreditBureau";
    private int paidRequests = 0;

    @Override
    public CreditReport getHistory(Employee requester, String clientId) {
        paidRequests++;
        Log.log(SOURCE, "ПЛАТНИЙ запит до зовнішнього бюро по клієнту " + clientId
                + " (тривалість ~3 с, запит №" + paidRequests + ")");
        int score = 600 + Math.abs(clientId.hashCode() % 200);
        return new CreditReport(clientId, score, "активних кредитів: 1, прострочень: 0");
    }

    public int getPaidRequests() {
        return paidRequests;
    }
}