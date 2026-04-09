public class Main {
    public static void main(String[] args) {
        System.out.println("=== Notification Demo ===");
        AuditLog audit = new AuditLog();

        Notification n = new Notification("Welcome", "Hello and welcome to SST!", "riya@sst.edu", "9876543210");

        NotificationSender email = new EmailSender(audit, n.email, n.subject);
        NotificationSender sms = new SmsSender(audit, n.phone);

        email.send(n.body);
        sms.send(n.body);

        try {
            NotificationSender wa = new WhatsAppSender(audit, n.phone);
            wa.send(n.body);
        } catch (RuntimeException ex) {
            System.out.println("WA ERROR: " + ex.getMessage());
            audit.add("WA failed");
        }

        System.out.println("AUDIT entries=" + audit.size());
    }
}
