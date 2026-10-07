package model;

public enum ScholarshipType {
    NONE("Без начислення степендії", 0.0),
    BASIC("Базова степендії", 1500.0),
    INCREASED("Пірвищеня степендія", 2000.0);

    private final String description;
    private final double amount;

    ScholarshipType(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription(){
        return description;
    }

    public double getAmount(){
        return amount;
    }

    public static ScholarshipType getByAverageGrade(double averageGrade) {
        if (averageGrade < Student.SUFFICIENT_THRESHOLD) {
            return NONE;
        } else if (averageGrade < Student.SCHOLARSHIP_THRESHOLD) {
            return BASIC;
        } else if (averageGrade < Student.INCREASED_SCHOLARSHIP_THRESHOLD) {
            return INCREASED;
        }
    }
}