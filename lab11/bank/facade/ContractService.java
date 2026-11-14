package lab11.bank.facade;
import bank.common.Log;

public class ContractService {
    public void sendContract(Account account) {
        Log.log("ContractService", "договір по рахунку " + account.number() + " відправлено клієнту "
                + account.owner().name());
    }
}