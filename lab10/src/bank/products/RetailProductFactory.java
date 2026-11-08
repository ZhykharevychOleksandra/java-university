package lab10.src.bank.products;

public class RetailProductFactory implements ProductPackageFactory {
    static final String SEGMENT = "Роздрібний";

    @Override
    public String segmentName() {
        return SEGMENT;
    }

    @Override
    public Card createCard() {
        return new RetailCard();
    }

    @Override
    public CurrentAccount createAccount() {
        return new RetailAccount();
    }

    @Override
    public TariffPlan createTariffPlan() {
        return new RetailTariff();
    }
}

record RetailCard() implements Card {
    public String segment() {
        return RetailProductFactory.SEGMENT;
    }

    public String describe() {
        return "Картка Visa Classic (випуск безкоштовний)";
    }
}

record RetailAccount() implements CurrentAccount {
    public String segment() {
        return RetailProductFactory.SEGMENT;
    }

    public String describe() {
        return "Поточний рахунок «Стандарт» (UAH)";
    }
}

record RetailTariff() implements TariffPlan {
    public String segment() {
        return RetailProductFactory.SEGMENT;
    }

    public String describe() {
        return "Тариф «Роздрібний»: обслуговування 30 грн/міс., комісія за перекази 1%";
    }
}
