package School.Mangement.System;

import java.util.List;

/**
 * there are going to be multiple teachers and student
 * so we are going to create an array list of teachers and studnets
 *
 */
public class School {
    private List<Teacher> teacher;
    private List<Student> student;
    private static int totalMoneyEarned;
    private static int totalMoneySpent;

    /**
     * new school object is created
     * @param teacher list of teachers in the school
     * @param student list of Student in the school
     */
    public School(List<Teacher> teacher, List<Student> student) {
        this.teacher = teacher;
        this.student = student;
        totalMoneyEarned=0;
        totalMoneySpent=0;

    }

    /**
     *
     * @return the list of techers
     */
    public List<Teacher> getTeacher() {
        return teacher;
    }

    /**
     * add the teacher to the school
     * @param teacher
     */
    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }

    /**
     * add teacher to the school
     * @param teacher
     */
    public void addTeacher(Teacher teacher){
        this.teacher.add(teacher);
    }
    public int getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    /**
     * adds the total money earned by the school
     * @param MoneyEarned
     */

    //with static we can access the method otherwise wwe have instance of method
    public static void updateTotalMoneyEarned(int MoneyEarned) {
        totalMoneyEarned += MoneyEarned;
    }

    /**
     * @return the list of the students in the school
     */
    public List<Student> getStudent() {
        return student;
    }

    /**
     * add student to the school
     * @param student student to be added to the school
     */
    public void addStudents(Student student){
        this.student.add(student);
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    /**
     *
     * @return total money spent
     */
    public int getTotalMoneySpent() {
        return totalMoneySpent;
    }

    /**
     * update the money spent by the school(salaries of the teachers)
     * @param MoneySpent the money spent by the school
     */
    public static void updateMoneySpent(int MoneySpent) {
        totalMoneySpent -= MoneySpent;
    }
}