// 4.5
import java.util.ArrayList;
import java.util.List;
public class LogisticsCompany implements ILogistics{
    private ArrayList<Transport> transports = new ArrayList<>();

    @Override 
    public void registerTransport(Transport t) {
        transports.add(t);
    }

    @Override 
    public List<Transport> searchByPayLoadCapacity(double minCapacity) {
        List<Transport> result = new ArrayList<>();
        for (Transport t : transports) {
            if (t.getPayLoadCapacity() >= minCapacity) {
                result.add(t);
            }
        } 
        return result;
    }
    public void printAll() {
        for (Transport t : transports) {
            System.out.println(t.info());
        }
    }
}
