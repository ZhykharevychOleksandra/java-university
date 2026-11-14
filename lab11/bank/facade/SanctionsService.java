package lab11.bank.facade;
import bank.common.Log;

public class SanctionsService {
    public boolean isClear(Client client) {
        boolean clear = !client.name().toUpperCase().contains("SANCTION");
        Log.log("SanctionsService", "перевірка клієнта " + client.name() + " у санкційних списках: "
                + (clear ? "чисто" : "ЗБІГ"));
        return clear;
    }
}