import java.util.*;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    @SuppressWarnings("unchecked")
    public <T> T getFirstOfCapability(Class<T> capability) {
        for (Object d : devices) {
            if (capability.isInstance(d)) return (T) d;
        }
        throw new IllegalStateException("Missing: " + capability.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAllOfCapability(Class<T> capability) {
        List<T> result = new ArrayList<>();
        for (Object d : devices) {
            if (capability.isInstance(d)) result.add((T) d);
        }
        return result;
    }
}
