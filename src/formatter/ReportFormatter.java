package formatter;

import model.Student;

// Для формування звітів про студента.
public interface ReportFormatter {
    String format(Student student);
    String getFormatType();
}