
public class InvoiceFormatter {

    public String format(InvoiceResult r) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice# ").append(r.invoiceId).append("\n");

        for (OrderLine l : r.lines) {
            MenuItem item = r.menuSnapshot.get(l.itemId);
            double lineTotal = item.price * l.qty;
            sb.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        sb.append(String.format("Subtotal: %.2f\n",       r.subtotal));
        sb.append(String.format("Tax(%.0f%%): %.2f\n",    r.taxPct, r.tax));
        sb.append(String.format("Discount: -%.2f\n",      r.discount));
        sb.append(String.format("TOTAL: %.2f\n",          r.total));

        return sb.toString();
    }
}
