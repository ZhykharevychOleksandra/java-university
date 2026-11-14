package lab11.bank.proxy;

public interface CreditBureau {
    CreditReport getHistory(Employee requester, String clientId);
}