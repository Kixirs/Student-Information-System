package service; 

import model.Student;
import java.util.Comparator;
import java.util.List;

public class InMemoryStudentService extends InMemoryCrudService<Student> implements StudentService {
    @Override
           
    public List<Student> findAllSortedByNameAndByAverageGrade() {
        return findAllSorted(
            Comparator.comparing(Student::getFullName)
                        .thenComparing(Student::getAverageGrade)
                            // Або можна лямбда-виразом student -> student.getName()
        );
    }
}