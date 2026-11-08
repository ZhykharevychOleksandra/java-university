package lab10.src.bank.products;

// cпільна абстракція будь-якого продукту пакета: сегмент і опис
public interface BankProduct {
    // cегмент клієнтів, для якого призначено продукт
    String segment();

    String describe();
}