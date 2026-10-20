import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashSet;
import java.util.Set;

import model.Student;
import model.BudgetStudent;
import model.ContractStudent;
import model.TargetStudent;

import strategy.*;
import template.*;
import exception.*;
import factory.StudentFactory;
import factory.StudentType;
import observer.Observer;
import observer.DeanOfficeObserver;
import observer.ScholarshipObserver;


public class StudentAccounting {

    // =====================================================================
    // СОРТУВАННЯ
    // =====================================================================
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

    // =====================================================================
    // СТВОРЕННЯ СТУДЕНТА ЧЕРЕЗ ФАБРИКУ (Рівень 1: Factory Method)
    // =====================================================================
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

    // ============================================================
    // ЛАБОРАТОРНА РОБОТА №6
    // ПОЛІМОРФІЗМ, INSTANCEOF, SWITCH PATTERN MATCHING
    // ============================================================

    private static void demonstratePolymorphism(Student[] students) {
        System.out.println("\n========== ПОЛІМОРФІЗМ ==========");

        for (Student student : students) {
            // Усі об'єкти зберігаються через базовий тип Student.
            // Конкретний тип кожного об'єкта визначається під час виконання.
            System.out.println("Студент: " + student.getFullName());
            System.out.println("Об'єкт: " + student.getClass().getSimpleName());

            if (student instanceof BudgetStudent) {
                System.out.println("Категорія: бюджет");
            } 
            else if (student instanceof ContractStudent) {
                System.out.println("Категорія: контракт");
            } 
            else if (student instanceof TargetStudent) {
                System.out.println("Категорія: цільове направлення");
            }

            System.out.println("Дані: " + student);
            System.out.println("----------------------------------");
        }
    }

    // ============================================================
    // SWITCH ІЗ PATTERN MATCHING
    // ============================================================
    private static void demonstratePatternSwitch(Student[] students) {
        System.out.println("\n========== PATTERN MATCHING SWITCH ==========");

        for (Student student : students) {

            String category = switch (student) {
                case BudgetStudent b ->
                        "Бюджетний студент";

                case ContractStudent c ->
                        "Контрактний студент";

                case TargetStudent t ->
                        "Студент за цільовим направленням";

                default ->
                        "Інший тип студента";
            };

            System.out.println( student.getFullName() + ": " + category);
        }
    }

    // ============================================================
    // EQUALS, HASHCODE ТА HASHSET
    // ============================================================
    private static void demonstrateEquality(Student[] students) {
        System.out.println("\n========== EQUALS ТА HASHCODE ==========");

        Set<Student> uniqueStudents = new HashSet<>();

        for (Student student : students) {
            uniqueStudents.add(student);
        }

        System.out.println(
                "Кількість об'єктів у масиві: " + students.length
        );
            // HashSet використовує equals() і hashCode() для визначення рівності об'єктів.
        System.out.println(
                "Кількість елементів у HashSet: " + uniqueStudents.size()
        );
    }

    // ============================================================
    // КОМПОЗИЦІЯ
    // ПРОФІЛЬ СТУДЕНТА МІСТИТЬ НАВЧАЛЬНИЙ ЗАПИС
    // ============================================================
    private static class StudentProfile {
        private final Student student;
        private final AcademicRecord academicRecord;

        public StudentProfile(
                Student student,
                AcademicRecord academicRecord
        ) {
            this.student = student;
            this.academicRecord = academicRecord;
          }

        public void printProfile() {
            System.out.println("Студент: " + student.getFullName());
            System.out.println("Навчальний запис: " + academicRecord);
        }
    }
    private record AcademicRecord(
            double averageGrade,
            String description
    ) {}

    private static void demonstrateComposition(Student[] students) {
        System.out.println("\n========== КОМПОЗИЦІЯ ==========");

        if (students.length == 0) {
            System.out.println("Немає студентів для демонстрації.");
            return;
        }

        Student student = students[0];

        AcademicRecord record = new AcademicRecord(
                student.getAverageGrade(),
                "Запис про успішність студента"
        );

        StudentProfile profile = new StudentProfile(student, record);

        profile.printProfile();
    }

    // ============================================================
    // ЗАГАЛЬНА ДЕМОНСТРАЦІЯ
    // ============================================================
    private static void demonstrateLab6(Student[] students) {
        System.out.println("\n");
        System.out.println("=================================================");
        System.out.println("                    ДЕМОНСТРАЦІЯ                 ");
        System.out.println("=================================================");

        demonstratePolymorphism(students);
        demonstratePatternSwitch(students);
        demonstrateEquality(students);
        demonstrateComposition(students);
        System.out.println("\nДемонстрацію завершено.");
    }


    // =====================================================================
    // ГОЛОВНИЙ МЕТОД
    // =====================================================================
    public static void main(String[] args) {
        
        Scanner scanner = null;

        try {
            scanner = new Scanner(System.in);

            System.out.println("=== Система обліку студентів: Додавання нових студентів ===\n");

            System.out.println("Оберіть тип стдента:");
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
                default -> StudentType.CONTRACT;
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
                double grade = getValidDouble(scanner, "Введіть бал (0.0-100.0): ", Student.ZERO, Student.HUNDRED);
                boolean hasAcademicDebts = getValidBoolean(scanner, "Чи має студент академічні заборгованості? (так/ні): ");
                boolean isSociallyProtected = getValidBoolean(scanner, "Чи належить студент до пільгової категорії? (так/ні): ");


                students[i] = createValidatedStudent(name, Id, year, course, email, grade, hasAcademicDebts, isSociallyProtected, type);
            }

    
            System.out.println("\n--------- СПИСОК СТУДЕНТІВ ---------\n");
            for (var s : students) {
                System.out.println(s);
            }

            // демо лаб 6.
            demonstrateLab6(students);

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

            // =====================================================================
            // ІНТЕРАКТИВНА ДЕМОНСТРАЦІЯ ПАТЕРНІВ (Вбудована в main)
            // =====================================================================
            System.out.println("=============================================================\n");
            System.out.println("Щоб перейти до демострації патернів натисніть Enter. Щоб вийти введіть 'ні'");
            String continueDemo = scanner.nextLine().trim().toLowerCase();

            if (!continueDemo.equals("ні") && !continueDemo.equals("no") && !continueDemo.equals("n") && !continueDemo.equals("0")) {
                
                System.out.println("\n[КРОК 1] Оберіть студента за ID для демонстрації:");
                System.out.println("Доступні ID:");
                for (Student s : students) {
                    System.out.printf("  -> ID: %-6d | %s | Поточний бал: %.2f%n", 
                        s.getStudentId(), s.getFullName(), s.getAverageGrade());
                }
                
                int targetId = getValidInt(scanner, "\nВведіть ID студента для демонстрації: ", 1, Integer.MAX_VALUE);
                
                Student demoStudent = null;
                for (Student s : students) {
                    if (s.getStudentId() == targetId) {
                        demoStudent = s;
                        break;
                    }
                }

                if (demoStudent == null) {
                    System.out.println("[ERROR] Студента з таким ID не знайдено. Демонстрацію скасовано.");
                } else {
                    System.out.println("\nОбрано студента: " + demoStudent.getFullName());
                    
                    // --- ДЕМОНСТРАЦІЯ OBSERVER ---
                    System.out.println("\n---------------------------------------------------------");
                    System.out.println("  ДЕМОНСТРАЦІЯ OBSERVER (Рівень 2)");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("1. Підписати обох спостерігачів (Відділення + Стипендія) на цього студента.");
                    System.out.println("2. НЕ підписувати (щоб потім побачити різницю).");

                    int obsChoice = getValidInt(scanner, "Ваш вибір (1 або 2): ", 1, 2);
                    
                    Observer deanObserver = new DeanOfficeObserver();
                    Observer scholarshipObserver = new ScholarshipObserver();
                    
                    if (obsChoice == 1) {
                        demoStudent.attsch(deanObserver);
                        demoStudent.attsch(scholarshipObserver);
                        System.out.println("Спостерігачі були підписані.");
                    }

                    System.out.print("\n Введіть новий середній бал для цього студента \n");
                    double newGrade = getValidDouble(scanner, "Новий бал: ", Student.ZERO, Student.HUNDRED);
                    
                    System.out.println("\n  РЕАКЦІЯ СИСТЕМИ ");
                    demoStudent.setAverageGrade(newGrade); // тут спрацьовує спостерігач.
                    System.out.println("  КІНЕЦЬ РЕАКЦІЇ \n");

                    if (obsChoice == 1) {
                        demoStudent.detach(deanObserver);
                        demoStudent.detach(scholarshipObserver);
                        double checkGrade = getValidDouble(scanner, "Введіть будь-який бал для перевірки: ", 0.0, 100.0);
                        demoStudent.setAverageGrade(checkGrade);
                    }

                    // --- ДЕМОНСТРАЦІЯ STRATEGY ---
                    System.out.println("\n---------------------------------------------------------");
                    System.out.println("  ДЕМОНСТРАЦІЯ STRATEGY");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("Поточний бал студента: " + demoStudent.getAverageGrade());
                    System.out.println("Оберіть стратегію розрахунку:");
                    System.out.println("1. Базова стипендія ");
                    System.out.println("2. Підвищена стипендія ");
                    System.out.println("3. Порівняти обидва варіанти");
                    int stratChoice = getValidInt(scanner, "Ваш вибір (1-3): ", 1, 3);

                    if (stratChoice == 1) {
                        ScholarshipCalculator strat = new BasicScholarshipCalculator();
                        System.out.println("  -> Результат (" + strat.getCalculatorType() + "): " + strat.calculateScholarship(demoStudent) + " грн");
                    } else if (stratChoice == 2) {
                        ScholarshipCalculator strat = new IncreasedScholarshipCalculator();
                        System.out.println("  -> Результат (" + strat.getCalculatorType() + "): " + strat.calculateScholarship(demoStudent) + " грн");
                    } else {
                        System.out.println("  -> Базова: " + new BasicScholarshipCalculator().calculateScholarship(demoStudent) + " грн");
                        System.out.println("  -> Підвищена: " + new IncreasedScholarshipCalculator().calculateScholarship(demoStudent) + " грн");
                    }

                    // --- ДЕМОНСТРАЦІЯ TEMPLATE METHOD ---
                    System.out.println("\n---------------------------------------------------------");
                    System.out.println(" ДЕМОНСТРАЦІЯ TEMPLATE METHOD");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("Оберіть тип звіту для генерації:");
                    System.out.println("1. Короткий звіт ");
                    System.out.println("2. Детальний звіт");
                    System.out.println("3. Згенерувати обидва");
                    int reportChoice = getValidInt(scanner, "Ваш вибір (1-3): ", 1, 3);

                    if (reportChoice == 1 || reportChoice == 3) {
                        System.out.println("\n[Генерація короткого звіту...]");
                        new BriefStudentReport().generateReport(demoStudent);
                    }
                    if (reportChoice == 2 || reportChoice == 3) {
                        System.out.println("\n[Генерація детального звіту...]");
                        new DetailedStudentReport().generateReport(demoStudent);
                    }
                    
                    System.out.println("\n=================================================================");
                    System.out.println(" Демонстрацію патернів успішно завершено!");
                }
            } else {
                System.out.println("Демонстрацію скасовано. Програма завершує роботу.");
            }



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

        private static double getValidDouble(Scanner scanner, String prompt, double min, double max) {
            while (true) {
                System.out.print(prompt);
                if (scanner.hasNextDouble()) {
                    double value = scanner.nextDouble();
                    scanner.nextLine();
                    if (value >= min && value <= max) return value;
                    System.out.println("[ERROR] число має бути від " + min + " до " + max + ".");
                } else {
                    System.out.println("[ERROR] введено не число. Спробуйте ще раз.");
                    scanner.next();
                }
            }
    }
}
