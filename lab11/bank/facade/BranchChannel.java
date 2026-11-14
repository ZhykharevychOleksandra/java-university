package lab11.bank.facade;
import bank.common.Log;

public class BranchChannel {
    private final AccountOpeningFacade facade;

    public BranchChannel(AccountOpeningFacade facade) {
        this.facade = facade;
    }

    public Account openAccount(Client client) {
        Log.log("BranchChannel", "менеджер відділення відкриває рахунок клієнту " + client.name());
        return facade.openAccount(client);
    }
}