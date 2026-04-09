
public class InvoicePrinter {

    public void print(String formattedInvoice) {
        System.out.print(formattedInvoice);
    }

    public void printSaved(String invoiceId, int lineCount) {
        System.out.println("Saved invoice: " + invoiceId + " (lines=" + lineCount + ")");
    }
}
