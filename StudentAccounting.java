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

    
    // Виведення списку через цикл for-each.
    Sysyem.out.println("\n========== СПИСОК СТУДЕНТІВ =========\n");
    for (var s : students) {
        System.out.println(s);
    }
}