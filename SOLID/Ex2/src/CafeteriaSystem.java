import java.util.*;


public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore    store;
    private final InvoiceFormatter formatter;
    private final InvoicePrinter   printer;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store,
                           InvoiceFormatter formatter,
                           InvoicePrinter printer) {
        this.store     = store;
        this.formatter = formatter;
        this.printer   = printer;
    }

    public void addToMenu(MenuItem item) { menu.put(item.id, item); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        TaxPolicy      taxPolicy      = TaxRules.forCustomerType(customerType);
        DiscountPolicy discountPolicy = DiscountRules.forCustomerType(customerType);
        BillingCalculator calc = new BillingCalculator(taxPolicy, discountPolicy);

        InvoiceResult result    = calc.calculate(invId, lines, menu);
        String        formatted = formatter.format(result);

        printer.print(formatted);
        store.save(invId, formatted);
        printer.printSaved(invId, store.countLines(invId));
    }
}
