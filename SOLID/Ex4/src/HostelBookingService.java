import java.util.Random;

public class HostelBookingService {
    private final HostelFeeCalculator calculator;
    private final BookingRepo repo;
    
    public HostelBookingService(HostelFeeCalculator calculator, BookingRepo repo) {
        this.calculator = calculator;
        this.repo = repo;
    }
    
    public void process(BookingRequest req) {
        Money monthly = calculator.calculateMonthly(req);
        Money deposit = new Money(5000.00);
        
        ReceiptPrinter.print(req, monthly, deposit);
        
        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}
