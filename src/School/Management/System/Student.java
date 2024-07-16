package School.Management.System;

/**
 * This class represents a Student in the school.
 */
public class Student {
    private int id;
    private String name;
    private int grade;
    private int feesPaid;
    private int feesTotal;

    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.feesPaid = 0;
        this.feesTotal = 500; // Assuming fees total is $500 per year
    }

    public Student(int id, String name, int grade, int feesPaid, int feesTotal) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.feesPaid = feesPaid;
        this.feesTotal = feesTotal;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public int getFeesTotal() {
        return feesTotal;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void payFees(int fees) {
        feesPaid += fees;
        School.updateTotalMoneyEarned(feesPaid);
    }

    public int dueFees() {
        return feesTotal - feesPaid;
    }

    @Override
    public String toString() {
        return "-----------------------\n" +
                "Student Name: " + name + "\n" +
                "ID no: " + id + "\n" +
                "Student Grade: " + grade + "\n" +
                "Total Fees: " + feesTotal + "\n" +
                "Fees paid so far: $" + feesPaid + "\n" +
                "Fees Due: $" + dueFees() + "\n" +
                "-----------------------";
    }
}
