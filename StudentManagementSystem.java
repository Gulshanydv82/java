import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        if (getStudentByRoll(student.getRollNumber()) != null) {
            System.out.println("❌ Student with this roll number already exists.");
            return;
        }
        students.add(student);
        saveToFile();
        System.out.println("✅ Student added successfully.");
    }

    public void removeStudent(String rollNumber) {
        Student student = getStudentByRoll(rollNumber);
        if (student != null) {
            students.remove(student);
            saveToFile();
            System.out.println("✅ Student removed successfully.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    public void editStudent(String rollNumber, String newName, String newGrade, int newAge) {
        Student student = getStudentByRoll(rollNumber);
        if (student != null) {
            student.setName(newName);
            student.setGrade(newGrade);
            student.setAge(newAge);
            saveToFile();
            System.out.println("✅ Student updated successfully.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    public void searchStudent(String rollNumber) {
        Student student = getStudentByRoll(rollNumber);
        if (student != null) {
            System.out.println("🔍 Found Student:");
            System.out.println(student);
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("📭 No students found.");
        } else {
            System.out.println("📋 All Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private Student getStudentByRoll(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("❌ Error saving to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            students = (List<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Error loading data. Starting with an empty list.");
        }
    }
}
