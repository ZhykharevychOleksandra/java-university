package lab10.src.bank.products;

public interface ProductPackageFactory {
    String segmentName();

    Card createCard();

    CurrentAccount createAccount();

    TariffPlan createTariffPlan();
}