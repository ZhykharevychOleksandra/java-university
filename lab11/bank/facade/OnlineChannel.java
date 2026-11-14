package lab11.bank.facade;
import bank.common.Log;

public class OnlineChannel {
    private final AccountOpeningFacade facade;

    public OnlineChannel(AccountOpeningFacade facade) {
        this.facade = facade;
    }

    public Account openAccount(Client client) {
        Log.log("OnlineChannel", "клієнт сам відкриває рахунок онлайн: " + client.name());
        return facade.openAccount(client);
    }
}