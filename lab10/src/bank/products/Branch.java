package lab10.src.bank.products;
import lab10.src.bank.common.Client;
import lab10.src.bank.common.Log;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Branch {
    private final String name;

    public Branch(String name) {
        this.name = name;
    }

    public ServicePackage openService(Client client, ProductPackageFactory factory) {
        Log.log(name, "Відкриття обслуговування: " + client + ", сегмент «" + factory.segmentName() + "»");

        Card card = factory.createCard();
        CurrentAccount account = factory.createAccount();
        TariffPlan tariff = factory.createTariffPlan();

        List<BankProduct> products = List.of(card, account, tariff);
        for (BankProduct product : products) {
            Log.log(name, "Видано: " + product.describe() + " [сегмент: " + product.segment() + "]");
        }

        Set<String> segments = products.stream().map(BankProduct::segment).collect(Collectors.toSet());
        if (segments.size() != 1) {
            throw new IllegalStateException("Продукти пакета належать до різних сегментів: " + segments);
        }
        Log.log(name, "Перевірка: усі продукти пакета належать до одного сегмента — " + segments.iterator().next());

        return new ServicePackage(client, card, account, tariff);
    }
}
