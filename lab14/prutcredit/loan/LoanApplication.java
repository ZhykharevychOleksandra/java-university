package lab14.prutcredit.loan;

public class LoanApplication {
    private final int id;
    private final String clientName;
    private final int amount;
    private final int creditScore;          // використовує скоринг
    private final boolean securityFlagged;  // використовує служба безпеки
    private final int collateralValue;      // використовує оцінювач застави
    private LoanStatus status = LoanStatus.NEW;

    public LoanApplication(int id, String clientName, int amount,
                           int creditScore, boolean securityFlagged, int collateralValue) {
        this.id = id; this.clientName = clientName; this.amount = amount;
        this.creditScore = creditScore; this.securityFlagged = securityFlagged;
        this.collateralValue = collateralValue;
    }

    public int getId() { return id; }
    public String getClientName() { return clientName; }
    public int getAmount() { return amount; }
    public int getCreditScore() { return creditScore; }
    public boolean isSecurityFlagged() { return securityFlagged; }
    public int getCollateralValue() { return collateralValue; }
    public LoanStatus getStatus() { return status; }
    public void setStatus(LoanStatus status) { this.status = status; }

    @Override public String toString() { return "№" + id + " (" + clientName + ", " + amount + " грн)"; }
}