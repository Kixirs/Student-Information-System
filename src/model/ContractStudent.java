package model;

import java.util.Objects;

/** Студент, який навчається за контрактною формою. */
public final class ContractStudent extends Student {
    private final String contractPlan;

    public ContractStudent(
            String fullName,
            int studentId,
            int enrollmentYear,
            int course,
            String email,
            double averageGrade
        ) {
            this(
                    fullName,
                    studentId,
                    enrollmentYear,
                    course,
                    email,
                    averageGrade,
                    "Повна оплата");
        }

        public ContractStudent(
                String fullName,
                int studentId,
                int enrollmentYear,
                int course,
                String email,
                double averageGrade,
                String contractPlan
        ) {

        super(
                fullName,
                studentId,
                enrollmentYear,
                course,
                email,
                averageGrade,
                false,
                false);

        this.contractPlan = Objects.requireNonNull(contractPlan);
    }

    @Override
    public String getEducationType() {
        return "Контракт";
    }

    public String getContractPlan() {
        return contractPlan;
    }

    @Override
    public String toString() {
        return super.toString()
                + "└─ Умови контракту: \n"
                + contractPlan + "\\n";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
                && Objects.equals(
                        contractPlan,
                        ((ContractStudent) obj).contractPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contractPlan);
    }
}