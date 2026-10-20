package exception;

// Для перевірки валідності курсу..
public class InvalidCourseException extends StudentDomainException {
    private final int invalidCourse;

    public InvalidCourseException(String message, int invalidCourse) {
        super(message);
        this.invalidCourse = invalidCourse;
    }

    public double getinvalidCourse() {
        return invalidCourse;
    }
}