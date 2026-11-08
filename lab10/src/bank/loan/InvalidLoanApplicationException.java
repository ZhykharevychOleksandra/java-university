package lab10.src.bank.loan;

public class InvalidLoanApplicationException extends IllegalStateException {
    public InvalidLoanApplicationException(String reason) {
        super("Неможливо сформувати заявку: " + reason);
    }
}