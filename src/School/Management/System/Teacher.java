package School.Management.System;

/*
 *
 * This class represents a Teacher in the school.
   */
public class Teacher {
    private int id;
    private String name;
    private int salary;
    private int salaryEarned;

    public Teacher(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.salaryEarned = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getSalaryEarned() {
        return salaryEarned;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void receiveSalary(int salary) {
        salaryEarned += salary;
        School.updateMoneySpent(salary);
    }

    @Override
    public String toString() {
        return "-----------------------\n" +
                "Teacher Name: " + name + "\n" +
                "ID no: " + id + "\n" +
                "Salary: $" + salary + "\n" +
                "Paid so far: $" + salaryEarned + "\n" +
                "-----------------------";
    }
}
