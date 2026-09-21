// Для перевірки ПІБ.
public class InvalidNameException extends StudentDomainException {
    private final String invalidName;

    public InvalidNameException(String message, String invalidName) {
        super(message);
        this.invalidName = invalidName;
    }

    public String getInvalidName() {
        return invalidName;
    }
}