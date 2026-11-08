package lab10.src.bank.products.students;
import lab10.src.bank.products.Card;
import lab10.src.bank.products.CurrentAccount;
import lab10.src.bank.products.ProductPackageFactory;
import lab10.src.bank.products.TariffPlan;

public class StudentProductFactory implements ProductPackageFactory {
    static final String SEGMENT = "Студентський";

    @Override
    public String segmentName() {
        return SEGMENT;
    }

    @Override
    public Card createCard() {
        return new StudentCard();
    }

    @Override
    public CurrentAccount createAccount() {
        return new StudentAccount();
    }

    @Override
    public TariffPlan createTariffPlan() {
        return new StudentTariff();
    }
}

record StudentCard() implements Card {
    public String segment() {
        return StudentProductFactory.SEGMENT;
    }

    public String describe() {
        return "Картка Visa Student (знижки в університетських кафе)";
    }
}

record StudentAccount() implements CurrentAccount {
    public String segment() {
        return StudentProductFactory.SEGMENT;
    }

    public String describe() {
        return "Студентський рахунок (UAH, без мінімального залишку)";
    }
}

record StudentTariff() implements TariffPlan {
    public String segment() {
        return StudentProductFactory.SEGMENT;
    }

    public String describe() {
        return "Тариф «Студентський»: обслуговування 0 грн, перекази без комісії до 5 000 грн";
    }
}
