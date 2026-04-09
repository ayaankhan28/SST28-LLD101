public class SmsSender extends NotificationSender {
    private final String to;

    public SmsSender(AuditLog audit, String to) {
        super(audit);
        this.to = to;
    }

    @Override
    public void send(String payload) {
        System.out.println("SMS -> to=" + to + " body=" + payload);
        audit.add("sms sent");
    }
}
