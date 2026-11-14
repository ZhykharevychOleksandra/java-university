package lab11.bank.facade;
import bank.common.Log;

public class CardService {
    public void issueCard(Account account) {
        Log.log("CardService", "випущено картку до рахунку " + account.number());
    }

    public void reissueCard(Account account) {
        Log.log("CardService", "перевипущено картку до рахунку " + account.number());
    }
}