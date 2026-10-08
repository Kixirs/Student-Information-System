package strategy; 

import model.ScholarshipType;
import model.Student;

public interface ScholarshipResolver {
    ScholarshipType resolve(Student student);
}