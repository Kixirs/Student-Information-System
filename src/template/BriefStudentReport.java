package template;
import model.Student;

public class BriefStudentReport extends StudentReport {
    
    @Override
    protected void printHeader() {
        System.out.println("--- КОРОТКИЙ ЗВІТ ---");
    }

    @Override
    protected void printFooter() {
        System.out.println("--- КІНЕЦЬ ---");
    }
}