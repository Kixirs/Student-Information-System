package src;

import java.util.Scanner;
import java.util.InputMismatchException;

import model.Student;
import model.ScholarshipType;
import strategy.*;
import formatter.*;
import template.*;
import exception.*;


public class StudentAccounting {

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

    private static Student createValidatedStudent(String name, int id, int year, int course, 
                                                  String email, double grade, boolean budget, 
                                                  boolean scholarship) throws StudentDomainException {
        try {
            return new Student(name, id, year, course, email, grade, budget, scholarship);
        } catch (StudentDomainException e) {
            System.err.println("[ЛОГ] Помилка валідації даних студента '" + name + "': " + e.getMessage());
            throw e;

        }
    }

    public static void main(String[] args) {
        
        Scanner scanner = null;

        try {
            scanner = new Scanner(System.in);
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

                students[i] = createValidatedStudent(name, Id, year, course, email, grade, budget, hasScholarship);
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



            // =====================================================================
            // ДЕОНСТРАЦІЯ РОБОТИ ВСІХ МЕТОДІВ ТА ПАТЕРНІВ (Для захисту ЛР)
            // =====================================================================
            System.out.println("\n=============================================================");
            System.out.println(" ДЕМОНСТРАЦІЯ РОБОТИ ВСІХ РЕАЛІЗОВАНИХ ПАТЕРНІВ ТА МЕТОДІВ ");
            System.out.println("=============================================================");
            
            Student demoStudent = new Student("Тестовий Відмінник", 999, 2023, 2, "test@uni.edu", 92.0, true, true);

            // 1. Демонстрація Strategy (Рівень 1: Масив + цикл)
            System.out.println("\n[1] ПАТЕРН STRATEGY (Поліморфізм через масив):");
            ScholarshipCalculator[] calculators = {
                new BasicScholarshipCalculator(),
                new IncreasedScholarshipCalculator()
            };
            for (ScholarshipCalculator calc : calculators) {
                double amount = calc.calculateScholarship(demoStudent);
                System.out.println("  -> Метод getCalculatorType(): " + calc.getCalculatorType());
                System.out.println("  -> Метод calculateScholarship(): " + amount + " грн");
            }

            // 2. Демонстрація Strategy Context (Рівень 3: Зміна під час виконання)
            System.out.println("\n[2] ПАТЕРН STRATEGY (Контекстний клас із динамічною зміною):");
            ScholarshipContext context = new ScholarshipContext(new BasicScholarshipCalculator());
            System.out.println("  -> Початковий розрахунок (Basic): " + context.calculate(demoStudent) + " грн");
            context.setStrategy(new IncreasedScholarshipCalculator()); // Виклик сетера
            System.out.println("  -> Розрахунок після зміни (Increased): " + context.calculate(demoStudent) + " грн");

            // 3. Демонстрація Template Method (Рівень 3: Абстрактний клас + підкласи)
            System.out.println("\n[3] ПАТЕРН TEMPLATE METHOD (Скелет алгоритму з final-методом):");
            System.out.println("  --- Виклик BriefStudentReport ---");
            StudentReport briefReport = new BriefStudentReport();
            briefReport.generateReport(demoStudent); // Виклик final-методу скелета
            
            System.out.println("\n  --- Виклик DetailedStudentReport ---");
            StudentReport detailedReport = new DetailedStudentReport();
            detailedReport.generateReport(demoStudent); // Виклик final-методу скелета з перевизначеними кроками

            // 4. Демонстрація множинної реалізації інтерфейсів (Рівень 2)
            System.out.println("\n[4] МНОЖИННА РЕАЛІЗАЦІЯ ІНТЕРФЕЙСІВ (ReportFormatter + Loggable):");
            AdvancedReportFormatter advancedFormatter = new AdvancedReportFormatter();
            System.out.println("  -> Результат format():\n" + advancedFormatter.format(demoStudent));
            System.out.println("=============================================================");

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
}