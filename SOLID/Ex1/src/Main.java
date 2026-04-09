// Main: wires up all collaborators (composition root) and invokes the workflow.
// Output is identical to the original.
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");

        // Infrastructure
        FakeDb db = new FakeDb();

        // Collaborators
        RawInputParser      parser    = new RawInputParser();
        StudentValidator    validator = new StudentValidator();
        ConfirmationPrinter printer   = new ConfirmationPrinter();

        // Thin orchestrator — depends on interfaces/collaborators, not concrete DB
        OnboardingService svc = new OnboardingService(parser, validator, db, printer);

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}
