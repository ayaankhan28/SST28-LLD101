import java.util.List;
import java.util.Map;


public class InvoiceResult {
    public final String invoiceId;
    public final List<OrderLine> lines;
    public final Map<String, MenuItem> menuSnapshot; 
    public final double subtotal;
    public final double taxPct;
    public final double tax;
    public final double discount;
    public final double total;

    public InvoiceResult(String invoiceId, List<OrderLine> lines,
                         Map<String, MenuItem> menuSnapshot,
                         double subtotal, double taxPct, double tax,
                         double discount, double total) {
        this.invoiceId    = invoiceId;
        this.lines        = lines;
        this.menuSnapshot = menuSnapshot;
        this.subtotal     = subtotal;
        this.taxPct       = taxPct;
        this.tax          = tax;
        this.discount     = discount;
        this.total        = total;
    }
}
