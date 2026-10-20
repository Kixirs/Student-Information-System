package strategy; 
import model.Student;

public abstract class AbstractScholarshipCalculator implements ScholarshipCalculator {

    @Override
    public double calculateScholarship(Student student) {
        if (!student.hasScholarship()) {
            return 0.0;
        }
        // Делегуємо специфічний розрахунок підкласам.
        return calculateSpecificAmount(student);
    }
    protected abstract double calculateSpecificAmount(Student student);
}