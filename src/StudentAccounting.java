import java.util.Scanner;
import java.util.InputMismatchException;

import model.Student;
import model.ScholarshipType;
import strategy.*;
import formatter.*;
import template.*;
import exception.*;
import factory.StudentFactory;
import factory.StudentType;


public class StudentAccounting {

        public static void sortByGrade(Student[] students) {
            int n = students.length; 
            for (int i = 0; i < n - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < n; j++) {
                    if (students[j].getAverageGrade() < students[minIndex].getAverageGrade()) {
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

    private static Student createValidatedStudent(String name, int id, int year, int course, 
                                                  String email, double grade,  
                                                  boolean hasAcademicDebts, 
                                                  boolean isSociallyProtected,
                                                  StudentType type) throws StudentDomainException {
        try {
            // Делегуємо створення об'єкта фабриці.
            return StudentFactory.createStudent( 
                type, name, id, year, course, email, grade, hasAcademicDebts, isSociallyProtected
            );
        } catch (StudentDomainException e) {
            System.err.println("[ЛОГ] Помилка валідації даних студента '" + name + "': " + e.getMessage());
            throw e;

        }
    }

    // =====================================================================
    // ДЕМОНСТРАЦІЇ ВСІХ ПАТЕРНІВ 
    // =====================================================================
    public static void demonstratePatterns() {
        System.out.println("\n=============================================================");
        System.out.println(" ДЕМОНСТРАЦІЯ ВИКОНАННЯ ЗАВДАНЬ ЛАБОРАТОРНОЇ РОБОТИ ");
        System.out.println("=============================================================");

        Student demoStudent = new Student("Тестовий Відмінник", 999, 2023, 2, "demo@uni.edu", 92.0, true, true);

        // -----------------------------------------------------------------
        // 1. ПАТЕРН STRATEGY (Рівень 1: Поліморфізм через масив)
        // -----------------------------------------------------------------
        System.out.println("\n[1] STRATEGY: Поліморфний виклик через масив інтерфейсів");
        ScholarshipCalculator[] strategies = {
            new BasicScholarshipCalculator(),
            new IncreasedScholarshipCalculator()
        };
        for (ScholarshipCalculator strategy : strategies) {
            System.out.println("  -> Тип: " + strategy.getCalculatorType());
            System.out.println("  -> Сума: " + strategy.calculateScholarship(demoStudent) + " грн \n");
        }

        // -----------------------------------------------------------------
        // 2. ПАТЕРН STRATEGY CONTEXT (Рівень 3: Динамічна зміна поведінки)
        // -----------------------------------------------------------------
        System.out.println("\n[2] STRATEGY CONTEXT: Зміна алгоритму під час виконання (Runtime)");
        ScholarshipContext context = new ScholarshipContext(new BasicScholarshipCalculator());
        System.out.println("  -> Початковий розрахунок: " + context.calculate(demoStudent) + " грн");
        
        // Демонстрація сетера (зміна стратегії на льоту)
        context.setStrategy(new IncreasedScholarshipCalculator());
        System.out.println("  -> Розрахунок після зміни: " + context.calculate(demoStudent) + " грн");

        // -----------------------------------------------------------------
        // 3. ПАТЕРН TEMPLATE METHOD (Рівень 3: Скелет алгоритму)
        // -----------------------------------------------------------------
        System.out.println("\n[3] TEMPLATE METHOD: Виклик final-методу generateReport()");
        
        System.out.println("  --- Короткий звіт (BriefStudentReport) ---");
        StudentReport briefReport = new BriefStudentReport();
        briefReport.generateReport(demoStudent); // Виклик скелета
        
        System.out.println("\n  --- Детальний звіт (DetailedStudentReport) ---");
        StudentReport detailedReport = new DetailedStudentReport();
        detailedReport.generateReport(demoStudent); // Виклик скелета з перевизначеними кроками

        // -----------------------------------------------------------------
        // 4. МНОЖИННА РЕАЛІЗАЦІЯ ІНТЕРФЕЙСІВ (Рівень 2)
        // -----------------------------------------------------------------
        System.out.println("\n[4] МНОЖИННА РЕАЛІЗАЦІЯ: ReportFormatter + Loggable");
        AdvancedReportFormatter advancedFormatter = new AdvancedReportFormatter();
        System.out.println("  -> Результат форматування:\n" + advancedFormatter.format(demoStudent));
        
        System.out.println("=============================================================\n");
    }


    public static void main(String[] args) {
        
        Scanner scanner = null;

        try {
            scanner = new Scanner(System.in);

            System.out.println("=== Система обліку студентів: Додавання нових студентів ===\n");

            System.out.println("Оберіть тип:");
            System.out.println("1 - Бюджет");
            System.out.println("2 - Контракт");
            System.out.println("3 - Цільове направлення");
            int typeChoice = getValidInt (
                scanner, "Введіть в термінал номер обраної категорії: ", 1, 3
            );
            
            StudentType type = switch (typeChoice) {
                case 1 -> StudentType.BUDGET;
                case 2 -> StudentType.CONTRACT;
                case 3 -> StudentType.TARGET;
                default -> StudentType.BUDGET;
            };

            System.out.print("Введіть кількість студентів для обробки: ");
            int n = scanner.nextInt();
            scanner.nextLine(); 

            
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

                boolean hasAcademicDebts = getValidBoolean(scanner, "Чи має студент академічні заборгованості? (так/ні): ");
                boolean isSociallyProtected = getValidBoolean(scanner, "Чи належить студент до пільгової категорії? (так/ні): ");


                students[i] = createValidatedStudent(name, Id, year, course, email, grade, hasAcademicDebts, isSociallyProtected, type);
            }

    
            System.out.println("\n--------- СПИСОК СТУДЕНТІВ ---------\n");
            for (var s : students) {
                System.out.println(s);
            }

            System.out.println("\n------------ СТАТИСТИКА ------------\n");
            int budgetStudentsCount = 0;
            for (var s : students) {
                if(s.isBudget()) {
                    budgetStudentsCount++;
                }
            }

            int highAchieversCount = 0;
            double targetGrade = 70.5;

            for(var s : students){
                if(s.getAverageGrade() > targetGrade) {
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

            demonstratePatterns();

        } catch (InvalidNameException e) {
            System.err.println("Помилка домену (ПІБ): " + e.getMessage() +
                    " (Ви ввели: '" + e.getInvalidName() + "')");

        } catch (InvalidGradeException e) {
            System.err.println("Помилка домену (Бал): " + e.getMessage() +
                    " (Ви ввели: " + e.getInvalidGrade() + ")");

        } catch (StudentDomainException e) {
            System.err.println("Загальна доменна помилка: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.err.println("Помилка введення: Будь ласка, вводьте дані у правильному числовому форматі.");

        } catch (Exception e) {

            System.err.println("Сталася непередбачена помилка: " + e.getMessage());

        } finally {
            if(scanner != null) {
                scanner.close();
                System.out.println("\n[FINALLY] Ресурс було успішно закрито.");
            }
        }
    }

        private static int getValidInt(Scanner scanner, String prompt, int min, int max) {
            while (true) {
                System.out.print(prompt);
                if (scanner.hasNextInt()) {
                    int value = scanner.nextInt();
                    scanner.nextLine(); 
                    if (value >= min && value <= max) return value;
                    System.out.println("[ERROR] число має бути від " + min + " до " + max + ".");
                } else {
                    System.out.println("[ERROR] введено не число. Спробуйте ще раз.");
                    scanner.next(); 
                }
            }
        }

        private static boolean getValidBoolean(Scanner scanner, String prompt) {
            while (true) {
                System.out.print(prompt);
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("так") || input.equals("yes") || input.equals("y") || input.equals("1") || input.equals("true")) {
                    return true;
                }
                if (input.equals("ні") || input.equals("no") || input.equals("n") || input.equals("0") || input.equals("false")) {
                    return false;
                }
                System.out.println("[ERROR] введіть 'так' або 'ні'.");
            }
        }
}
