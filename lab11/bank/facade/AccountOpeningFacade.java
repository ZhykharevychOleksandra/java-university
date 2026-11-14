package lab11.bank.facade;
import bank.common.Log;

public class AccountOpeningFacade {
    private static final String SOURCE = "AccountOpeningFacade";

    private final IdentificationService identification;
    private final SanctionsService sanctions;
    private final AccountService accounts;
    private final CardService cards;
    private final OnlineBankingService onlineBanking;
    private final ContractService contracts;

    public AccountOpeningFacade(IdentificationService identification, SanctionsService sanctions,
                                AccountService accounts, CardService cards,
                                OnlineBankingService onlineBanking, ContractService contracts) {
        this.identification = identification;
        this.sanctions = sanctions;
        this.accounts = accounts;
        this.cards = cards;
        this.onlineBanking = onlineBanking;
        this.contracts = contracts;
    }

    public Account openAccount(Client client) {
        Log.log(SOURCE, "починаю відкриття рахунку для " + client.name());
        if (!identification.identify(client)) {
            Log.log(SOURCE, "ідентифікацію не пройдено, відкриття скасовано");
            return null;
        }
        if (!sanctions.isClear(client)) {
            Log.log(SOURCE, "клієнт у санкційному списку, відкриття скасовано");
            return null;
        }
        Account account = accounts.create(client);
        cards.issueCard(account);
        onlineBanking.connect(account);
        contracts.sendContract(account);
        Log.log(SOURCE, "рахунок " + account.number() + " відкрито");
        return account;
    }
}