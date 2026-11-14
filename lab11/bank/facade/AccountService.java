package lab11.bank.facade;
import bank.common.Log;

public class AccountService {
    private int counter = 0;

    public Account create(Client client) {
        Account account = new Account("UA00 0000 " + String.format("%06d", ++counter), client);
        Log.log("AccountService", "створено рахунок " + account.number() + " для " + client.name());
        return account;
    }
}