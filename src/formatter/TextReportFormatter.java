package formatter;

import model.Student;

// Реалізація текстового формату звіту.
public class TextReportFormatter implements ReportFormatter {
    @Override
    public String format(Student student) {
        // Створює обєкт класу який використовується в мовах програмуваня
        // для ефективного створення та модефікації текстів.
        StringBuilder sb = new StringBuilder(); 
        sb.append("=== ТЕКСТОВИЙ ЗВІТ ===\n");
        sb.append("ID: ").append(student.getStudentId()).append("\n");
        sb.append("ПІБ: ").append(student.getFullName()).append("\n");
        sb.append("Курс: ").append(student.getCourse()).append("\n");
        sb.append("Середній бал: ").append(student.getAverageGrade()).append("\n");
        sb.append("Бюджет: ").append(student.isBudget() ? "Так" : "Ні").append("\n");
        sb.append("Стипендія: ").append(student.hasScholarship() ? "Так" : "Ні").append("\n");
        return sb.toString(); // для отримання готового результату.
    }

    @Override
    // метод відповідає за формат звіту. Цей метот пмитєво віддає рядок текст і завершує роботу.
    public String getFormatType() {
        return "TEXT";
    }
}
