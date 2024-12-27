/*
 * The ClassManager class is responsible for managing a collection of students in the class.
 * It provides methods to add, remove, and access students by their ID, as well as calculate class statistics such as class average, highest/lowest grades, and passing/failing counts.
 * It also displays the class roster and supports updating student information.
 */

import java.util.*;

public class ClassManager {
    private Map<String, Student> students = new HashMap<>(); // Map to store students with ID as the key

    // Add a student to the class using their ID
    public void addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            System.out.println("Student ID already exists.");
        } else {
            students.put(student.getId(), student);
            System.out.println("Student added.");
        }
    }

    // Remove a student from the class by ID
    public void removeStudent(String id) {
        if (students.remove(id) != null) {
            System.out.println("Student removed.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    // Show the roster of students in the class
    public void showRoster() {
        if (students.isEmpty()) {
            System.out.println("The class roster is empty.");
        } else {
            students.values().forEach(System.out::println);
        }
    }

    // Retrieve a student by their ID
    public Student getStudentById(String id) {
        return students.get(id);
    }

    // Calculate and return the average grade for the entire class
    public double calculateClassAverage() {
        if (students.isEmpty()) return 0.0;

        double total = 0;
        int count = 0;

        for (Student student : students.values()) {
            double avg = student.calculateAverage();
            if (avg > 0) {
                total += avg;
                count++;
            }
        }
        return count == 0 ? 0.0 : total / count;
    }

    // Get the highest grade across all students
    public int findClassHighestGrade() {
        int highest = 0;
        for (Student student : students.values()) {
            highest = Math.max(highest, student.findHighestGrade());
        }
        return highest;
    }

    // Get the lowest grade across all students
    public int findClassLowestGrade() {
        int lowest = Integer.MAX_VALUE;
        for (Student student : students.values()) {
            int studentLowest = student.findLowestGrade();
            if (studentLowest > 0) {
                lowest = Math.min(lowest, studentLowest);
            }
        }
        return lowest == Integer.MAX_VALUE ? 0 : lowest;
    }

    // Count the number of passing students in the class
    public long countPassingStudents() {
        return students.values().stream().filter(Student::isPassing).count();
    }

    // Count the number of failing students in the class
    public long countFailingStudents() {
        return students.size() - countPassingStudents();
    }
}
