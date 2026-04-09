import java.util.List;

// After SRP refactor: OnboardingService is now a thin workflow orchestrator.
// It delegates every responsibility to a focused collaborator:
//   - RawInputParser  → parsing
//   - StudentValidator → validation
//   - StudentRepository → persistence
//   - ConfirmationPrinter → console output
// Adding a new field (e.g. "city") only requires touching the parser, validator,
// and StudentRecord — never this class.
public class OnboardingService {

    private final RawInputParser    parser;
    private final StudentValidator  validator;
    private final StudentRepository repository;
    private final ConfirmationPrinter printer;

    public OnboardingService(
            RawInputParser    parser,
            StudentValidator  validator,
            StudentRepository repository,
            ConfirmationPrinter printer) {
        this.parser     = parser;
        this.validator  = validator;
        this.repository = repository;
        this.printer    = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        ParsedInput input = parser.parse(raw);

        List<String> errors = validator.validate(input);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id  = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, input.name, input.email, input.phone, input.program);

        repository.save(rec);
        printer.printSuccess(rec, repository.count());
    }
}
