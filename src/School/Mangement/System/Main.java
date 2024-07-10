package School.Mangement.System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static School school;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize school with some teachers and students
        List<Teacher> teacherList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();

        teacherList.add(new Teacher(1, "Anirban", 400));
        teacherList.add(new Teacher(2, "Sambhunath", 500));
        teacherList.add(new Teacher(3, "Somya", 300));
        teacherList.add(new Teacher(4, "Salina", 450));

        studentList.add(new Student(1, "Himanshu", 12));
        studentList.add(new Student(2, "Yashaswi", 5));
        studentList.add(new Student(3, "Lakshmi", 10));
        studentList.add(new Student(4, "Rajesh", 8));

        school = new School(teacherList, studentList);

        // Sample fee payment and salary payments
        studentList.get(0).payFees(250);
        studentList.get(1).payFees(250);
        studentList.get(2).payFees(250);
        studentList.get(3).payFees(250);

        System.out.println("School has earned $" + school.getTotalMoneyEarned());

        teacherList.get(0).reciveSalary(teacherList.get(0).getSalary());
        teacherList.get(1).reciveSalary(teacherList.get(1).getSalary());
        teacherList.get(2).reciveSalary(teacherList.get(2).getSalary());
        teacherList.get(3).reciveSalary(teacherList.get(3).getSalary());

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
                case 1:
                    addStudent();
                    break;
                case 2:
                    addTeacher();
                    break;
                case 3:
                    displayAllStudents();
                    break;
                case 4:
                    displayAllTeachers();
                    break;
                case 5:
                    payStudentFees();
                    break;
                case 6:
                    payTeacherSalary();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
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

        Student student = new Student(id, name, grade);
        school.getStudent().add(student);
        System.out.println("Student added successfully.");
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

        Teacher teacher = new Teacher(id, name, salary);
        school.getTeacher().add(teacher);
        System.out.println("Teacher added successfully.");
    }

    private static void displayAllStudents() {
        List<Student> students = school.getStudent();
        System.out.println("\nStudents:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void displayAllTeachers() {
        List<Teacher> teachers = school.getTeacher();
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

        for (Student student : school.getStudent()) {
            if (student.getId() == id) {
                student.payFees(fees);
                System.out.println("Fees paid successfully for " + student.getName());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void payTeacherSalary() {
        System.out.print("Enter teacher ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (Teacher teacher : school.getTeacher()) {
            if (teacher.getId() == id) {
                teacher.reciveSalary(teacher.getSalary());
                System.out.println("Salary paid successfully to " + teacher.getName());
                return;
            }
        }
        System.out.println("Teacher not found.");
    }
}
