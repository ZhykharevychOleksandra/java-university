package lab11.bank.facade;
import bank.common.Log;

public class IdentificationService {
    public boolean identify(Client client) {
        Log.log("IdentificationService", "ідентифіковано клієнта " + client.name());
        return true;
    }
}