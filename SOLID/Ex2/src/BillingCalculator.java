import java.util.List;
import java.util.Map;


public class BillingCalculator {
    private final TaxPolicy      taxPolicy;
    private final DiscountPolicy discountPolicy;

    public BillingCalculator(TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        this.taxPolicy      = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    public InvoiceResult calculate(String invoiceId, List<OrderLine> lines, Map<String, MenuItem> menu) {
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            subtotal += menu.get(l.itemId).price * l.qty;
        }
        double taxPct    = taxPolicy.taxPercent();
        double tax       = subtotal * (taxPct / 100.0);
        double discount  = discountPolicy.discountAmount(subtotal, lines.size());
        double total     = subtotal + tax - discount;

        return new InvoiceResult(invoiceId, lines, menu, subtotal, taxPct, tax, discount, total);
    }
}
