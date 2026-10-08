package formatter;

import model.Student;

public interface ReportFormatter {
    String format(Student student);
    String getFormatType();
}