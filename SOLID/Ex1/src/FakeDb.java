import java.util.*;

// Checkpoint D: FakeDb now implements StudentRepository (the persistence interface).
// Its internal logic is unchanged; it just declares the contract.
public class FakeDb implements StudentRepository {
    private final List<StudentRecord> rows = new ArrayList<>();

    @Override public void save(StudentRecord r) { rows.add(r); }
    @Override public int count()                { return rows.size(); }
    @Override public List<StudentRecord> all()  { return Collections.unmodifiableList(rows); }
}
