package observer;
import model.Student;
import model.ScholarshipType;
/**
 * Спостерігач 1: Стипендіальний відділ.
 * Реагує на зміну балу, перевіряючи право на стипендію.
 */
public class ScholarshipObserver implements Observer {
    @Override
    public void update(Student student) {
        ScholarshipType type = student.getScholarshipType();
        if (type != ScholarshipType.NONE) {
            System.out.println("[Стипендіальний відділ] Студенту " + student.getFullName() + 
                             " призначено стипендію типу: " + type.getDescription());
        } else {
            System.out.println("[Стипендіальний відділ] Студенту " + student.getFullName() + 
                             " стипендію скасовано або не призначено.");
        }
    }
}