public class EmailSender extends NotificationSender {
    private final String to;
    private final String subject;

    public EmailSender(AuditLog audit, String to, String subject) {
        super(audit);
        this.to = to;
        this.subject = subject;
    }

    @Override
    public void send(String payload) {
        System.out.println("EMAIL -> to=" + to + " subject=" + subject + " body=" + payload);
        audit.add("email sent");
    }
}
