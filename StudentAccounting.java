import java.util.Scanner;

        
        public static void sortByGrade(Student[] students) {
            int n = students.length; 

            for (int i = 0; i < n - 1; i++) {
                int minIndex = i;

                for (int j = i + 1; j < n; j++) {
                    if (students[j].averageGrade() < students[minIndex].averageGrade()) {
                        minIndex = j;
                    }
                }
                if (minIndex != i) {
                    Student temp = students[i];
                    students[i] = students[minIndex];
                    students[minIndex] = temp;
                }
            }
        }

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
            boolean hasScholarship = false;
            if (grade >= Student.SUFFICIENT_THRESHOLD) {
                scanner.nextLine();

                System.out.print("Навчається на бюджеті? (true/false): ");
                budget = scanner.nextBoolean();

                System.out.print("Чи нараховується стипендія? (true/false): ");
                hasScholarship = scanner.nextBoolean();
                
                scanner.nextLine();
            } else {
                scanner.nextLine();
            }

            students[i] = new Student(name, Id, year, course, email, grade, budget, hasScholarship);
        }

        
        // Виведення списку через цикл for-each.
        System.out.println("\n--------- СПИСОК СТУДЕНТІВ ---------\n");
        for (var s : students) {
            System.out.println(s);
        }

        System.out.println("\n------------ СТАТИСТИКА ------------\n");
        // 1. підрахунок студентів які на бюджеті.
        int budgetStudentsCount = 0;
        for (var s : students) {
            if(s.isBudget()) {
                budgetStudentsCount++;
            }
        }

        // 2. Підрахунок студентів з високим балом.
        int highAchieversCount = 0;
        double targetGrade = 70.5;

        for(var s : students){
            if(s.averageGrade() > targetGrade) {
                highAchieversCount++;
            }
        }

        System.out.printf("Кількість оброблених студентів: %d%n", students.length);
        System.out.printf("Кількість студентів на бюджеті: %d%n", budgetStudentsCount);
        System.out.printf("Кількість студентів з балом > %.2f: %d%n", targetGrade, highAchieversCount);

        sortByGrade(students);

        System.out.println("\n--------- СПИСОК СТУДЕНТІВ (Після сортування) ---------\n");
        for (var s : students) {
            System.out.println(s);
        }
        scanner.close();
    }
