
public class TaxRules implements TaxPolicy {
    private final double pct;

    private TaxRules(double pct) { this.pct = pct; }

    @Override
    public double taxPercent() { return pct; }

    public static TaxPolicy forCustomerType(String customerType) {
        if ("student".equalsIgnoreCase(customerType)) return new TaxRules(5.0);
        if ("staff".equalsIgnoreCase(customerType))   return new TaxRules(2.0);
        return new TaxRules(8.0);
    }
}
