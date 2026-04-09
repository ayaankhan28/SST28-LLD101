// TextTable now depends on the StudentRepository interface, not FakeDb directly.
// Its formatting logic is unchanged.
public class TextTable {
    public static String render3(StudentRepository repo) {
        StringBuilder sb = new StringBuilder();
        sb.append("| ID             | NAME | PROGRAM |\n");
        for (StudentRecord r : repo.all()) {
            sb.append(String.format("| %-14s | %-4s | %-7s |\n", r.id, r.name, r.program));
        }
        return sb.toString();
    }
}
