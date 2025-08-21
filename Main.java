import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter roll number: ");
                    String roll = scanner.nextLine().trim();
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine().trim();
                    System.out.print("Enter age: ");
                    int age = readValidAge(scanner);

                    if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
                        System.out.println("❌ Fields cannot be empty.");
                    } else {
                        Student student = new Student(name, roll, grade, age);
                        sms.addStudent(student);
                    }
                    break;

                case "2":
                    System.out.print("Enter roll number of student to edit: ");
                    String editRoll = scanner.nextLine().trim();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine().trim();
                    System.out.print("Enter new grade: ");
                    String newGrade = scanner.nextLine().trim();
                    System.out.print("Enter new age: ");
                    int newAge = readValidAge(scanner);

                    sms.editStudent(editRoll, newName, newGrade, newAge);
                    break;

                case "3":
                    System.out.print("Enter roll number to remove: ");
                    String removeRoll = scanner.nextLine().trim();
                    sms.removeStudent(removeRoll);
                    break;

                case "4":
                    System.out.print("Enter roll number to search: ");
                    String searchRoll = scanner.nextLine().trim();
                    sms.searchStudent(searchRoll);
                    break;

                case "5":
                    sms.displayAllStudents();
                    break;

                case "6":
                    System.out.println("👋 Exiting... Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("❌ Invalid choice.");
            }
        }

        scanner.close();
    }

    private static int readValidAge(Scanner scanner) {
        while (true) {
            try {
                int age = Integer.parseInt(scanner.nextLine());
                if (age > 0) return age;
                else System.out.print("❌ Age must be positive. Enter again: ");
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid age. Enter a number: ");
            }
        }
    }
}
