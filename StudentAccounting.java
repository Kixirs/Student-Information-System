import java.util.Scanner;

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість студентів для обробки: ");
        int n = scanner.nextInt();
        scanner.nextLine(); //очищення буферу.

        //Масив для об'єктів класу 'Student'.
        var students = new Student[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\n--- Введення даних для студента " + (i + 1) + " ---");
            
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

            boolean budget = false;
            if (grade >= Student.SUFFICIENT_THRESHOLD) {
                System.out.print("Навчається на бюджеті? (true/false): ");
                budget = scanner.nextBoolean();
                scanner.nextLine();
            }

            boolean hasScholarship = false;
            if (grade >= Student.SUFFICIENT_THRESHOLD) {
                System.out.print("Чи нараховується стипендія? (true/false): ");
                hasScholarship = scanner.nextBoolean();
                scanner.nextLine();
            }

            students[i] = new Student(name, Id, year, course, email, grade, budget, hasScholarship);
        }

        
        // Виведення списку через цикл for-each.
        System.out.println("\n========== СПИСОК СТУДЕНТІВ =========\n");
        for (var s : students) {
            System.out.println(s);
        }

        int scholarshipCount = 0;
        for (var s : students) {
            if(s.hasScholarship()) {
                scholarshipCount++;
            }
        }
    }

