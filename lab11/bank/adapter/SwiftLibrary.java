package lab11.bank.adapter;
import bank.common.Log;

public class SwiftLibrary {
    private static final String SOURCE = "SwiftLibrary";

    public String transmit(String message) {
        Log.log(SOURCE, "отримано повідомлення:\n" + indent(message));
        String status;
        if (message.contains("CLOSED")) {
            status = "RJCT/AC04";
        } else if (message.contains("SANCTION")) {
            status = "RJCT/RR04";
        } else {
            status = "ACSC";
        }
        Log.log(SOURCE, "повертаю статус зі свого довідника: " + status);
        return status;
    }

    private static String indent(String text) {
        return "        " + text.replace("\n", "\n        ");
    }
}