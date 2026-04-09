public class WhatsAppSender extends NotificationSender {
    private final String to;

    public WhatsAppSender(AuditLog audit, String to) {
        super(audit);
        if (to == null || !to.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
        this.to = to;
    }

    @Override
    public void send(String payload) {
        System.out.println("WA -> to=" + to + " body=" + payload);
        audit.add("wa sent");
    }
}
