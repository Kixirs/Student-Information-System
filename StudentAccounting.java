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