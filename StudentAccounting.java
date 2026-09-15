import java.util.Scanner;

void main() {
    
    Scanner scanner = new Scanner(System.in);

    System.out.print("Введіть кількість студентів для обробки: ");
    int n = scanner.nextInt();
    scanner.nextLine() //очищення буферу.

    //Масив для об'єктів класу 'Student'.
    var students = new Student[n];

    for (int i = 0; i < n; i++) {
        System.out.print("Введіть ПІБ студента: ");
        String name = scanner.nextLine();

        System.out.print("Введіть ID студента: ");
        int Id = scanner.nextInt();

        System.out.print("Рік вступу: ");
        int year = scanner.nextInt();

        System.out.print("Навчальний курс студента: ");
        int course = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Середній бал студента: ");
        double grade = scanner.nextDouble();

        System.out.print("Навчається на бюджеті? (true/false): ");
        boolean budget = scanner.nextBoolean();

        students[i] = Student(name, id, year, course, email, grade, budget);
    }


    double deficit;
    String scholarshipTier;

    if (averageGrade < 30) {
        deficit = 30.0 - averageGrade;
        scholarshipTier = "Мінімальна успішність";
    } else if (averageGrade < 50) {
        deficit = 50.0 - averageGrade;
        scholarshipTier = "Задовільний рівень";
    } else if (averageGrade < 70) {
        deficit = 70.0 - averageGrade;
        scholarshipTier = "Достатній рівень";
    } else if (averageGrade < 85) {
        deficit = 85.0 - averageGrade;
        scholarshipTier = "Нарахування стипендії";
    } else if (averageGrade < 90) {
        deficit = 90.0 - averageGrade;
        scholarshipTier = "Підвищене нарахування";
    } else {
        deficit = 0.0;
        scholarshipTier = "Максимальне нарахування";
    }

    System.out.println("\n=== АНАЛІТИКА УСПІШНОСТІ СТУДЕНТА ===");
    System.out.printf("Студент: %s (ID: %d)%n", fullName, studentId);
    System.out.printf("Курс: %d | Середній бал: %.2f%n", course, averageGrade);
    System.out.printf("Отримує стипендію: %b%n", hasScholarship);

    if (deficit > 0) {
        System.out.printf("Для досягнення рівня '%s' необхідно підвищити бал на: %.2f%n", scholarshipTier, deficit);
    } else {
        System.out.printf("Поточний статус: %s%n", scholarshipTier);
    }
    System.out.println("=====================================");

    scanner.close();
}