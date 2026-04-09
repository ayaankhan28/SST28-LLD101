import java.util.Map;
import java.util.HashMap;

public class AddOnFeeComponent implements FeeComponent {
    private final Map<AddOn, Double> prices = new HashMap<>();

    public AddOnFeeComponent() {
        prices.put(AddOn.MESS, 1000.0);
        prices.put(AddOn.LAUNDRY, 500.0);
        prices.put(AddOn.GYM, 300.0);
    }

    @Override
    public double calculate(BookingRequest req) {
        double total = 0.0;
        for (AddOn a : req.addOns) {
            total += prices.getOrDefault(a, 0.0);
        }
        return total;
    }
}
