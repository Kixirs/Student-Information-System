package formatter;
import model.Student;

// Клас реалізує 2 інтерфейси одночасно.
public class AdvancedReportFormatter implements ReportFormatter, Loggable {
    
    @Override
    public String format(Student student) {
        logAction("Форматування розширеного звіту для: " + student.getFullName());
        return "РОЗШИРЕНИЙ ЗВІТ\n" +
               "Студент: " + student.getFullName() + "\n" +
               "Середній бал: " + student.getAverageGrade() + "\n" +
               "Статус: " + (student.isBudget() ? "Бюджет" : "Контракт");
    }

    @Override
    public String getFormatType() {
        return "ADVANCED";
    }

    @Override
    public void logAction(String message) {
        System.out.println("[SYSTEM LOG] " + message);
    }
}