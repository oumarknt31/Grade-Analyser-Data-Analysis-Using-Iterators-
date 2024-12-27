/*
 * The Student class represents a student in the class with details such as first name, last name, ID, and grades.
 * It provides methods to calculate the average grade, highest grade, lowest grade, and determine if the student is passing or failing.
 * It also includes getter and setter methods for modifying student information.
 */

import java.util.*;

public class Student {
    private String firstName;     // First name of the student
    private String lastName;      // Last name of the student
    private String id;            // Unique ID of the student (4 characters)
    private List<Integer> grades; // List of grades the student has received

    // Constructor to initialize a student object with name, id, and optional grades
    public Student(String firstName, String lastName, String id, List<Integer> grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.grades = grades != null ? grades : new ArrayList<>();
    }
    
    // Getter and setter methods for each field
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setGrades(List<Integer> grades) {
        this.grades = grades != null ? grades : new ArrayList<>();
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Integer> getGrades() {
        return grades; // Provide access to the grades
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    // Method to calculate the average grade for the student
    public double calculateAverage() {
        if (grades.isEmpty()) return 0.0; // If no grades, return 0
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return total / (double) grades.size();
    }

    // Method to get the highest grade
    public int findHighestGrade() {
        return grades.isEmpty() ? 0 : Collections.max(grades);
    }

    // Method to get the lowest grade
    public int findLowestGrade() {
        return grades.isEmpty() ? 0 : Collections.min(grades);
    }

    // Method to check if the student is passing (average >= 60)
    public boolean isPassing() {
        return calculateAverage() >= 60; // Assuming 60 is the passing mark
    }

    // Override toString method to display student details
    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + id + ") - Grades: " + grades;
    }
}
