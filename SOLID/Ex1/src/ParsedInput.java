// Checkpoint B: plain data object produced by RawInputParser.
// No logic here — just a container so parsing result can be passed as structured data.
public class ParsedInput {
    public final String name;
    public final String email;
    public final String phone;
    public final String program;

    public ParsedInput(String name, String email, String phone, String program) {
        this.name    = name;
        this.email   = email;
        this.phone   = phone;
        this.program = program;
    }
}
