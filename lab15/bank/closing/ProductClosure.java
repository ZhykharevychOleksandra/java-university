package lab15.bank.closing;
import lab15.bank.common.Client;
import lab15.bank.common.Log15;

public abstract class ProductClosure {
    private final String source;

    protected ProductClosure(String source) { 
        this.source = source; 
    }

    public final void close(Client client) {
        Log15.log(source, "--- Початок закриття продукту ---");
        identify(client);
        if (!checkDebt(client)) {
            Log15.log(source, "Етап 2. Перевірка заборгованості: ВІДМОВА, борг "
                    + Log15.money(client.getDebt()) + ". Процедуру зупинено");
            return;
        }
        Log15.log(source, "Етап 2. Перевірка заборгованості: заборгованості немає");
        finalSettlement(client);
        withholdTax(client);
        closeProduct(client);
        issueCertificate(client);
        Log15.log(source, "--- Продукт закрито ---");
    }

    private void identify(Client c) {
        Log15.log(source, "Етап 1. Ідентифікація клієнта: " + c.getName());
    }

    private void issueCertificate(Client c) {
        Log15.log(source, "Етап 5. Довідка: «Продукт [" + productName()
                + "] клієнта " + c.getName() + " закрито. Претензій банк не має»");
    }

    protected abstract String productName();
    protected abstract void finalSettlement(Client c);

    protected boolean checkDebt(Client c) { return c.getDebt() <= 0; }

    protected void withholdTax(Client c) {
        Log15.log(source, "Етап 3б. Утримання податку: не застосовується");
    }

    protected void closeProduct(Client c) {
        Log15.log(source, "Етап 4. Продукт [" + productName() + "] переведено у статус «Закрито»");
    }
}