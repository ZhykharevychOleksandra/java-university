package lab10.src.bank.products;

public class PremiumProductFactory implements ProductPackageFactory {
    static final String SEGMENT = "Преміальний";

    @Override
    public String segmentName() {
        return SEGMENT;
    }

    @Override
    public Card createCard() {
        return new PremiumCard();
    }

    @Override
    public CurrentAccount createAccount() {
        return new PremiumAccount();
    }

    @Override
    public TariffPlan createTariffPlan() {
        return new PremiumTariff();
    }
}

record PremiumCard() implements Card {
    public String segment() {
        return PremiumProductFactory.SEGMENT;
    }

    public String describe() {
        return "Картка Visa Platinum (консьєрж-сервіс, доступ до лаунджів)";
    }
}

record PremiumAccount() implements CurrentAccount {
    public String segment() {
        return PremiumProductFactory.SEGMENT;
    }

    public String describe() {
        return "Преміальний рахунок (UAH/USD/EUR, персональний менеджер)";
    }
}

record PremiumTariff() implements TariffPlan {
    public String segment() {
        return PremiumProductFactory.SEGMENT;
    }

    public String describe() {
        return "Тариф «Преміум»: обслуговування 0 грн, перекази без комісії";
    }
}
