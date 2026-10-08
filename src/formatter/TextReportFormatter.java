package formatter;

import model.Student;

public class TextReportFormatter implements ReportFormatter {
    @Override
    public String format(Student student) {
        StringBuilder sb = new StringBuilder(); 
        sb.append("=== ТЕКСТОВИЙ ЗВІТ ===\n");
        sb.append("ID: ").append(student.getStudentId()).append("\n");
        sb.append("ПІБ: ").append(student.getFullName()).append("\n");
        sb.append("Курс: ").append(student.getCourse()).append("\n");
        sb.append("Середній бал: ").append(student.getAverageGrade()).append("\n");
        sb.append("Бюджет: ").append(student.isBudget() ? "Так" : "Ні").append("\n");
        sb.append("Стипендія: ").append(student.hasScholarship() ? "Так" : "Ні").append("\n");
        return sb.toString();
    }

    @Override
    public String getFormatType() {
        return "TEXT";
    }
}
