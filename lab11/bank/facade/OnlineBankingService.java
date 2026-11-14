package lab11.bank.facade;
import bank.common.Log;

public class OnlineBankingService {
    public void connect(Account account) {
        Log.log("OnlineBankingService", "підключено онлайн-банкінг до рахунку " + account.number());
    }
}