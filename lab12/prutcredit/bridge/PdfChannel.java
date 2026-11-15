package lab12.prutcredit.bridge;
import lab12.prutcredit.common.EventLog;

public class PdfChannel implements DeliveryChannel {
    @Override
    public String getName() {
        return "PDF (онлайн-банкінг)";
    }

    @Override
    public void deliver(String title, String content) {
        String fileName = title.replaceAll("[^\\p{L}\\p{N}]+", "_") + ".pdf";
        EventLog.log("PDF", "Онлайн-банкінг: сформовано файл " + fileName + " | " + content);
    }
}