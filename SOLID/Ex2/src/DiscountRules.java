
public class DiscountRules implements DiscountPolicy {
    private final String customerType;

    private DiscountRules(String customerType) { this.customerType = customerType; }

    @Override
    public double discountAmount(double subtotal, int distinctLines) {
        if ("student".equalsIgnoreCase(customerType)) {
            return subtotal >= 180.0 ? 10.0 : 0.0;
        }
        if ("staff".equalsIgnoreCase(customerType)) {
            return distinctLines >= 3 ? 15.0 : 5.0;
        }
        return 0.0;
    }

    public static DiscountPolicy forCustomerType(String customerType) {
        return new DiscountRules(customerType);
    }
}
