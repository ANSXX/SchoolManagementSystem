package School.Management.System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class to run the school management system.
 */
public class Main {
    private static School school;
    private static final Scanner scanner = new Scanner(System.in);

    // MYSQL's connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/school_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        // Initialize school from MySQL database
        List<Teacher> teacherList = getAllTeachersFromDB();
        List<Student> studentList = getAllStudentsFromDB();
        school = new School(teacherList, studentList);

        // Check if the studentList has at least 4 students
        if (studentList.size() >= 4) {
            // Sample fee payment
            for (int i = 0; i < 4; i++) {
                studentList.get(i).payFees(250);
            }
        } else {
            System.out.println("Not enough students in the database for sample fee payments.");
        }

        System.out.println("School has earned $" + school.getTotalMoneyEarned());

        // Pay salary to teachers
        for (Teacher teacher : teacherList) {
            teacher.receiveSalary(teacher.getSalary());
        }

        // User interface
        while (true) {
            System.out.println("\n--- School Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Display All Students");
            System.out.println("4. Display All Teachers");
            System.out.println("5. Pay Student Fees");
            System.out.println("6. Pay Teacher Salary");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addTeacher();
                case 3 -> displayAllStudents();
                case 4 -> displayAllTeachers();
                case 5 -> payStudentFees();
                case 6 -> payTeacherSalary();
                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static List<Teacher> getAllTeachersFromDB() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM teachers");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int salary = resultSet.getInt("salary");
                teachers.add(new Teacher(id, name, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    private static List<Student> getAllStudentsFromDB() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int grade = resultSet.getInt("grade");
                int feesPaid = resultSet.getInt("fees_paid");
                int feesTotal = resultSet.getInt("fees_total");
                students.add(new Student(id, name, grade, feesPaid, feesTotal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO students (id, name, grade, fees_paid, fees_total) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, grade);
            statement.setInt(4, 0); // Initialize fees_paid as 0
            statement.setInt(5, 500); // Assuming fees_total is $500 per year

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student added successfully to database.");
                school.addStudent(new Student(id, name, grade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addTeacher() {
        System.out.print("Enter teacher ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();
        System.out.print("Enter teacher salary: ");
        int salary = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO teachers (id, name, salary, salary_earned) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, salary);
            statement.setInt(4, 0); // Initialize salary_earned as 0

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Teacher added successfully to database.");
                school.addTeacher(new Teacher(id, name, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllStudents() {
        List<Student> students = school.getStudents();
        System.out.println("\nStudents:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void displayAllTeachers() {
        List<Teacher> teachers = school.getTeachers();
        System.out.println("\nTeachers:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }

    private static void payStudentFees() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter fee amount: $");
        int fees = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Student studentToPay = school.getStudents().stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);

        if (studentToPay != null) {
            studentToPay.payFees(fees);
            updateStudentFeesInDB(studentToPay);
            System.out.println("Fees paid successfully for " + studentToPay.getName());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudentFeesInDB(Student student) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE students SET fees_paid = ? WHERE id = ?")) {
            statement.setInt(1, student.getFeesPaid());
            statement.setInt(2, student.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student fees updated in database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void payTeacherSalary() {
        System.out.print("Enter teacher ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Teacher teacherToPay = school.getTeachers().stream()
                .filter(teacher -> teacher.getId() == id)
                .findFirst()
                .orElse(null);

        if (teacherToPay != null) {
            teacherToPay.receiveSalary(teacherToPay.getSalary());
            updateTeacherSalaryInDB(teacherToPay);
            System.out.println("Salary paid successfully to " + teacherToPay.getName());
        } else {
            System.out.println("Teacher not found.");
        }
    }

    private static void updateTeacherSalaryInDB(Teacher teacher) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE teachers SET salary_earned = ? WHERE id = ?")) {
            statement.setInt(1, teacher.getSalaryEarned());
            statement.setInt(2, teacher.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Teacher salary updated in database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
