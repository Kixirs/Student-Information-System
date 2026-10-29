package service;

import model.Student;
import java.util.List;

public interface StudentService extends CRUDService<Student> {
    List<Student> findAllSortedByNameAndByAverageGrade();
}