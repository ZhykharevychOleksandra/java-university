package lab10.src.bank.products;

// пакет для бізнес-клієнтів
public class BusinessProductFactory implements ProductPackageFactory {
    static final String SEGMENT = "Бізнес";

    @Override
    public String segmentName() {
        return SEGMENT;
    }

    @Override
    public Card createCard() {
        return new BusinessCard();
    }

    @Override
    public CurrentAccount createAccount() {
        return new BusinessAccount();
    }

    @Override
    public TariffPlan createTariffPlan() {
        return new BusinessTariff();
    }
}

record BusinessCard() implements Card {
    public String segment() {
        return BusinessProductFactory.SEGMENT;
    }

    public String describe() {
        return "Корпоративна картка Mastercard Business";
    }
}

record BusinessAccount() implements CurrentAccount {
    public String segment() {
        return BusinessProductFactory.SEGMENT;
    }

    public String describe() {
        return "Розрахунковий рахунок підприємства (UAH)";
    }
}

record BusinessTariff() implements TariffPlan {
    public String segment() {
        return BusinessProductFactory.SEGMENT;
    }

    public String describe() {
        return "Тариф «Бізнес»: обслуговування 150 грн/міс., платежі 0,2%";
    }
}
