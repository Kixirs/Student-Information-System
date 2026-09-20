// Для перевірки балу.
public class InvalidGradeException extends StudentDomainException {
    private final double invalidGrade;

    public InvalidGradeException(String message, double invalidGrade) {
        super(message);
        this.invalidGrade = invalidGrade;
    }

    public double getInvalidGrade() {
        return invalidGrade;
    }
}