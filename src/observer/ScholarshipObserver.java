package observer;
import model.Student;

/**
 * Спостерігач 2: Система нарахування стипендій.
 * Відстежує зміни балу і реагує на зміну статусу стипендії.
*/
public class ScholarshipObserver implements Observer {
    @Override
    public void update(Student student, double oldGrade, double newGrade) {
    // Перевірка зміни статусу степендії
        boolean hadScholarship = oldGrade >= Student.SCHOLARSHIP_THRESHOLD && student.isBudget();
        boolean hasScholarshipNow = student.hasScholarship();

        String studentInfo = "Студент '" + student.getFullName() + "' (ID: " + student.getStudentId() + ")";

        if (hadScholarship && !hasScholarshipNow) {
            // Стипендію втрачено
            System.out.println("\n[СТИПЕНДІЯ] " + studentInfo + ": стипендію скасовано");
            System.out.println("   Причина: бал знизився з " + String.format("%.2f", oldGrade) + 
                             " до " + String.format("%.2f", newGrade) + 
                             " (поріг: " + Student.SCHOLARSHIP_THRESHOLD + ")");
        } else if (!hadScholarship && hasScholarshipNow) {
            // Стипендію отримано
            System.out.println("\n[СТИПЕНДІЯ] " + studentInfo + ": нараховано стипендію");
            System.out.println("   Привітання: Бал підвищено з" + String.format("%2.f", oldGrade) + 
                             "до " + String.format("%2.f", newGrade));
        } else if (hadScholarship && hasScholarshipNow) {
            // Стипендія збережена
            System.out.println("\n[СТИПЕНДІЯ] " + studentInfo + ": стипендія збережена (бал: " + 
                             String.format("%.2f", newGrade) + ")");
        } else {
            // Стипендії не було і немає
            System.out.println("\n[СТИПЕНДІЯ] " + studentInfo + ": стипендія не нараховується (бал: " + 
                             String.format("%.2f", newGrade) + ")");
        }
    }
}