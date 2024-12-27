/************************
Group Members: Md Mamun, Oumar Kante, Andrew Mobus
Professor: aid Al-Mashhadan
Date: 12-17-24123
Task: 
- This program performs data analysis on a dataset of student grades stored in a LinkedList.
- It utilizes iterators to traverse through the list of students and access their individual grades for statistical analysis.
- The program calculates and displays various statistics, including the average grade, highest and lowest grades, passing/failing status for each student, and overall class statistics.
- Features include adding/removing students, editing their information, and performing calculations on individual and class-wide grades.
*******************************/


import java.util.*;

public class Main {

    // ClassManager instance handles the operations related to student management
    private static final ClassManager classManager = new ClassManager();

    public static void main(String[] args) {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Main loop to keep the program running
        while (true) {
            // Displaying main menu
            System.out.println("\nWelcome to GradeMaster\n");
            System.out.println("1 - Show Roster");
            System.out.println("2 - Manage Students");
            System.out.println("3 - Class's Stats");
            System.out.println("0 - Exit");
            System.out.print("\nYour choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Process the user's choice
            switch (choice) {
                case 1:
                classManager.showRoster(); // Display the list of all students in the class
                    break;
                case 2:
                    manageStudents(scanner); // Handle student-related operations
                    break;
                case 3:
                    classStats(); // Display class statistics
                    break;
                case 0:
                    System.out.println("Exiting GradeMaster. Goodbye!"); // Exit the program
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageStudents(Scanner scanner) {
        System.out.println("\nStudent Management Menu");
        System.out.println("1 - Add Student");
        System.out.println("2 - Remove Student");
        System.out.println("3 - Update Student's Info");
        System.out.print("\nYour choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Manage Students: Add, Remove, Update
        switch (choice) {
            
            // Add a new student with provided information
            case 1:
                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter Student ID (4 characters): ");
                String id = scanner.nextLine();
                System.out.print("Enter Grades (comma-separated): ");
                String gradesInput = scanner.nextLine();
                List<Integer> grades = parseGrades(gradesInput);

                // Add student to the class
                classManager.addStudent(new Student(firstName, lastName, id, grades));
                break;
                
            // Remove a student by ID
            case 2:
                System.out.print("Enter Student ID to Remove: ");
                String removeId = scanner.nextLine();
                classManager.removeStudent(removeId);
                break;
            
            // Update student details based on ID    
            case 3:
                System.out.print("Enter Student ID to Update: ");
                String updateId = scanner.nextLine();
                Student studentToUpdate = classManager.getStudentById(updateId);

                if (studentToUpdate != null) {
                    System.out.println("\nStudent Found: " + studentToUpdate);
                    System.out.println("What would you like to update?");
                    System.out.println("1 - First Name");
                    System.out.println("2 - Last Name");
                    System.out.println("3 - Grades");
                    System.out.println("4 - ID");
                    System.out.print("\nYour choice: ");
                    int updateChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Switch based on user's choice to update different student info
                    switch (updateChoice) {
                        
                        // Update the first name of the student
                        case 1:
                            System.out.print("Enter New First Name: ");
                            String newFirstName = scanner.nextLine();
                            studentToUpdate.setFirstName(newFirstName);
                            System.out.println("First Name Updated.");
                            break;
                            
                        // Update the last name of the student    
                        case 2:
                            System.out.print("Enter New Last Name: ");
                            String newLastName = scanner.nextLine();
                            studentToUpdate.setLastName(newLastName);
                            System.out.println("Last Name Updated.");
                            break;
                            
                        // Update the grades of the student    
                        case 3:
                            System.out.print("Enter New Grades (comma-separated): ");
                            String newGradesInput = scanner.nextLine();
                            List<Integer> newGrades = parseGrades(newGradesInput);
                            studentToUpdate.setGrades(newGrades);
                            System.out.println("Grades Updated.");
                            break;
                            
                        // Update the student ID, ensuring no duplicates    
                        case 4:
                            System.out.print("Enter New ID (4 characters): ");
                            String newId = scanner.nextLine();
                            if (classManager.getStudentById(newId) != null) {
                                System.out.println("ID already exists. Update canceled.");
                            } else {
                                studentToUpdate.setId(newId);
                                classManager.removeStudent(updateId);
                                classManager.addStudent(studentToUpdate);
                                System.out.println("ID Updated.");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice. Update canceled.");
                    }
                } 
                // Student not found in the class
                else {
                    System.out.println("Student not found.");
                }
                break;
            default:
                // Invalid input handling
                System.out.println("Invalid choice.");
        }
    }

    // Display class stats including average, highest, lowest, passing and failing students
    private static void classStats() {
        System.out.println("\nClass Statistics:");
        System.out.println("Average Grade: " + classManager.calculateClassAverage());
        System.out.println("Highest Grade: " + classManager.findClassHighestGrade());
        System.out.println("Lowest Grade: " + classManager.findClassLowestGrade());
        System.out.println("Number of Passing Students: " + classManager.countPassingStudents());
        System.out.println("Number of Failing Students: " + classManager.countFailingStudents());
    }

    // Helper method to parse grades input and convert it into a list of integers
    private static List<Integer> parseGrades(String gradesInput) {
        // Return empty list if no grades were provided
        if (gradesInput.isEmpty()) return new ArrayList<>();
        
        // Split the input by commas and parse each grade
        String[] tokens = gradesInput.split(",");
        List<Integer> grades = new ArrayList<>();
        for (String token : tokens) {
            try {
                grades.add(Integer.parseInt(token.trim())); // Parse and add the grade
            } catch (NumberFormatException e) {
                System.out.println("Invalid grade skipped: " + token); // Handle invalid input
            }
        }
        return grades;
    }
}
