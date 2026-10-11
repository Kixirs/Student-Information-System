package observer;
import model.Student;
/**
 * Спостерігач 2: Відділення.
 * Відстежує критичне падіння успішності для превентивних заходів.
 */
public class DeanOfficeObserver implements Observer {
    @Override
    public void update(Student student) {
        double grade = student.getAverageGrade();
        if (grade < Student.SATISFACTORY_THRESHOLD) { // Наприклад, < 50.0
            System.out.println("[Відділення] УВАГА! Студент " + student.getFullName() + 
                             " має критичний бал (" + grade + "). Викликликається на відділення!");
        } else if (grade >= Student.INCREASED_SCHOLARSHIP_THRESHOLD) { // >= 90.0
            System.out.println("[Відділення] Студент " + student.getFullName() + 
                             " показує видатні результати (" + grade + "). Рекомендовано на грамоту!");
        } else {
            System.out.println("[Відділення] Студент " + student.getFullName() + 
                             " продовжує навчання у штатному режимі. Бал: " + grade);
        }
    }
}