import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        
        List<FeeComponent> components = Arrays.asList(
            new RoomFeeComponent(),
            new AddOnFeeComponent()
        );
        HostelFeeCalculator calc = new HostelFeeCalculator(components);
        HostelBookingService service = new HostelBookingService(calc, new FakeBookingRepo());
        service.process(req);
    }
}
