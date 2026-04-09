import java.util.List;

public class HostelFeeCalculator {
    private final List<FeeComponent> feeComponents;

    public HostelFeeCalculator(List<FeeComponent> feeComponents) {
        this.feeComponents = feeComponents;
    }

    public Money calculateMonthly(BookingRequest req) {
        double total = 0.0;
        for (FeeComponent comp : feeComponents) {
            total += comp.calculate(req);
        }
        return new Money(total);
    }
}
