package model;

import java.util.Objects;

/**
 * Проміжний абстрактний клас для студентів які навчаються 
 * за бюджетною формою.
 * Якщо винести всі специфічні дані лише в окремі класи доведеться дублювати частину логіки.
 * FundedStudent об'єднує те, що є спільним саме для цих підкласів.
 */
public abstract sealed class FundedStudent extends Student permits BudgetStudent, TargetStudent {

    private final String fundingBasis;

    protected FundedStudent(
        String fullName,
        int studentId,
        int enrollmentYear,
        int course,
        String email,
        double averageGrade,
        boolean isBudget,
        boolean hasScholarship,
        String fundingBasis // Підстава фінансуваня.
    ) {
        // Передає загальні дані до конструктору Student.
        super(
            fullName,
            studentId,
            enrollmentYear,
            course,
            email,
            averageGrade,
            isBudget,
            hasScholarship);
        
        this.fundingBasis = Objects.requireNonNull(fundingBasis);
    }

    public final String getFundingBasis() {
        return fundingBasis;
    }

    @Override 
    public String toString() {
        return super.toString()
                + "└─ Підстава фінансування: \n"
                + fundingBasis + "\\n";
    }

}