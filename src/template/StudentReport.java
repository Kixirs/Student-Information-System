package template;
import model.Student;

public abstract class StudentReport {
    public final void generateReport(Student student) {
        printHeader();
        printStudentData(student);
        printAdditionalInfo(student);
        printFooter();
    }

    protected abstract void printHeader();
    protected abstract void printFooter();

// Hook method
    protected void printAdditionalInfo(Student student) {
        System.out.println("Додаткова інформація: стандартна");
    }

    protected void printStudentData(Student student) {
        System.out.println("Студент: " + student.getFullName());
        System.out.println("ID: " + student.getStudentId());
        System.out.println("Середній бал: " + student.getAverageGrade());
    }
}