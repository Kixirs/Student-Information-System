package service;

import model.Student;
import java.util.List;

public interface StudentService extends CRUDService<Student> {
    // Сортування за іменем, потім за середнім балом.
    List<Student> findAllSortedByNameAndByGrade();
}