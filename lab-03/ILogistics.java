// 4.4
import java.util.List;

public interface ILogistics {
    void registerTransport(Transport t);
    List<Transport> searchByPayLoadCapacity(double minCapacity);
} 
