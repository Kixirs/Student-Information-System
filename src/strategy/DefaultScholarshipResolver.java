package strategy;

import model.ScholarshipType;
import model.Student;

public class DefaultScholarshipResolver implements ScholarshipResolver {
    
    @Override
    public ScholarshipType resolve(Student student) {
        double grade = student.getAverageGrade();
        
        if (grade < Student.SUFFICIENT_THRESHOLD) {
            return ScholarshipType.NONE;
        } else if (grade < Student.SCHOLARSHIP_THRESHOLD) {
            return ScholarshipType.BASIC;
        } else {
            return ScholarshipType.INCREASED;
        }
    }
}