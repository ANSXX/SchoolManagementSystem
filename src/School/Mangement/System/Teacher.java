package School.Mangement.System;

/**
 * This is for keeping track of teachers
 * Name, ID, Salary
 */
public class Teacher {
    private int id;
    private String name;
    private int Salary;
    private int SalaryEarned;

    /**
     * creates a new Teacher object
     * @param id for the teacher
     * @param name of the teacher
     * @param salary of the teacher
     */

    public Teacher(int id, String name, int salary){
        this.id=id;
        this.name=name;
        this.Salary=salary;
        this.SalaryEarned=0;
    }

    /**
     *
     * @return the ID, Name, Salary of the teacher.
     */

    public String getName(){
        return this.name;

    }

    public int getId(){
        return id;
    }

    public int getSalary() {
        return Salary;
    }

    /**
     * Sets the salary
     * @param salary
     */
    public void setSalary(int salary){
        this.Salary=salary;
    }

    /**
     * adds to the salary
     * removes from the total money earned to School
     * @param salary
     */
    public void reciveSalary(int salary){
        SalaryEarned+=Salary;
        School.updateMoneySpent(salary);
    }

    @Override
    public String toString(){
        return "-----------------------"+"\nTeacher Name: "+name+"\nID no: "+getId()+"\nSalary: $"+ getSalary()+"\nPaid so far: $"+SalaryEarned+"\n-----------------------";
    }
}
