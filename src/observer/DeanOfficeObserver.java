package observer;
import model.Student;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Універсальний спостерігач Відділення.
 * Поєднує логування змін балу та аналіз успішності для превентивних заходів.
 */
public class DeanOfficeObserver implements Observer {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void update(Student student, double oldGrade, double newGrade) {
        // 1. Логування
        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.out.println("[LOG " + timestamp + "] Відділення: Студент '" + student.getFullName() + 
                           "' (ID: " + student.getStudentId() + "): бал змінено з " + 
                           String.format("%.2f", oldGrade) + " на " + String.format("%.2f", newGrade));

                // 2. Аналіз нового стану та прийняття рішень
        if (newGrade < Student.SATISFACTORY_THRESHOLD) { // Наприклад, < 50.0
            System.out.println("[УВАГА] Студент " + student.getFullName() + 
                             " має низьку успішність. Студент викликликається на відділення для профілактичної бесіди!");
        } else if (newGrade >= Student.INCREASED_SCHOLARSHIP_THRESHOLD) { // >= 90.0
            System.out.println("[ЗАУВАЖЕННЯ] Студент " + student.getFullName() + 
                             " показує видатні результати (" + newGrade + "). Рекомендовано на грамоту!");
        } else {
            System.out.println("[ІНФО] Студент " + student.getFullName() + 
                             " продовжує навчання у штатному режимі. Бал: " + newGrade);
        }
    }
}