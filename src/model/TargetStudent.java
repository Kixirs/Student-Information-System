package model;

import java.util.Objects;

public final class TargetStudent extends FundedStudent {

    private final String targetOrganization;

    public TargetStudent(
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
                    "Організація не вказана");
        }

        public TargetStudent(
                String fullName,
                int studentId,
                int enrollmentYear,
                int course,
                String email,
                double averageGrade,
                boolean hasScholarship,
                String targetOrganization
        ) {

        super(
                fullName,
                studentId,
                enrollmentYear,
                course,
                email,
                averageGrade,
                false,
                hasScholarship,
                "Цільове фінансування");

        this.targetOrganization = Objects.requireNonNull(targetOrganization);
        System.out.println("[Конструктор] TargetStudent: "
                + "ініціалізовано організацію-направника.");
    }


    }

    @Override
    public String getEducationType() {
        return "Цільове направлення";
    }

    public String getTargetOrganization() {
        return targetOrganization;
    }

    @Override
    public String toString() {
        return super.toString()
                + "└─ Організація-направник: \n"
                + targetOrganization + "\\n";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
                && Objects.equals(
                        targetOrganization,
                        ((TargetStudent) obj).targetOrganization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                targetOrganization);
    }
}