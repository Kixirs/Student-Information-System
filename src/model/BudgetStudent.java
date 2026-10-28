package model;

import java.util.Objects;

/** Студент, який навчається за бюджетною формою. */
public final class BudgetStudent extends FundedStudent {
    private final String budgetProgram;

    public BudgetStudent (
                    String fullName,
            int studentId,
            int enrollmentYear,
            int course,
            String email,
            double averageGrade,
            boolean hasScholarship
    ) {
        this(   
            fullName,
            studentId,
            enrollmentYear,
            course,
            email,
            averageGrade,
            hasScholarship,
            "Бюджетна програма");
    }

    public BudgetStudent(
            String fullName,
            int studentId,
            int enrollmentYear,
            int course,
            String email,
            double averageGrade,
            boolean hasScholarship,
            String budgetProgram
    )  {
    
    super(
        fullName,
        studentId,
        enrollmentYear,
        course,
        email,
        averageGrade,
        true,
        hasScholarship,
        "Державне фінансування");

        this.budgetProgram = Objects.requireNonNull(budgetProgram);
                System.out.println("[Конструктор] BudgetStudent: "
                + "ініціалізовано бюджетну програму.");
    }

    @Override
    public String getEducationType() {
        return "Бюджет";
    }

    public String getBudgetProgram() {
        return budgetProgram;
    }

    @Override
    public String toString() {
        return super.toString()
                + "└─ Бюджетна програма: \n"
                + budgetProgram + "\\n";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
                && Objects.equals(
                        budgetProgram,
                        ((BudgetStudent) obj).budgetProgram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), budgetProgram);
    }
}