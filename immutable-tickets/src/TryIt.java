import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nAfter service mutations (new instance returned): " + escalated);

        try {
            List<String> tags = escalated.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("Mutated tags successfully?!?! " + tags);
        } catch (UnsupportedOperationException e) {
            System.out.println("\nException expected/caught when trying to mutate tags: " + e.getClass().getName());
        }

        System.out.println("\nFinal ticket state: " + escalated);
    }
}
