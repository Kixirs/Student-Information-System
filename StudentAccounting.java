import java.util.Scanner;

void main()

    Scanner scanner = new Scanner(System.in);

    print("Введіть ПІБ студента: ");
    string fullname = scanner.nextline();

    print("Введіть ID студента: ");
    int studentid= scanner.nextInt();

    print("Навчальний курс студента: (наприклад 3)");
    int course = scanner.nextInt();

    print("Середній бал студента: ");
    double averageGrade = scanner.nextDouble();

    print("Чи студент отримує стипендію? (true/false): ");
    boolean hasScholarship = scanner.nextBoolean();

    double deficit; //кількість балів, яких не вистачає студенту до наступного порогу
    String scholarshipTier; //xназва цілі

    if (averageGrade < 30) {
        deficit = 30.0 - averageGrade;
        scholarshipTier = "Мінімальна успішності";
    } else if (averageGrade < 50) {
        deficit = 50.0 - averageGrade;
        scholarshipTier = "Задовільний рівень";
    } else if (averageGrade < 70) {
        deficit = 70.0 - averageGrade;
        scholarshipTier = "Достатній рівень";
    } else if (averageGrade < 85) {
        deficit = 85.0 - averageGrade;
        scholarshipTier = "Нарахування степендії";
    } else if (averageGrade < 90) {
        deficit = 90.0 - averageGrade;
        scholarshipTier = "Нарахування підвищеної степендії";
    } else {
        deficit = 0.0;
        scholarshipTier = "Максимальний рівень";
    }
