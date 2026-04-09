import java.util.List;

// Checkpoint D: Interface for persistence.
// OnboardingService depends on this abstraction, not on FakeDb directly.
// Any store (SQL, Mongo, in-memory) just needs to implement these two methods.
public interface StudentRepository {
    void save(StudentRecord record);
    int count();
    List<StudentRecord> all();
}
