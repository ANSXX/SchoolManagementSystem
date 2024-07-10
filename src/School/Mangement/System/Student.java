package School.Mangement.System;

/*
 this is for keeping track of fees, name, grade and fees paid
 */

public class Student {
    private int id;
    private String name;
    private int Grade;
    private int feesPaid;
    private int feesTotal;

    /**
     * role of constructor to create a new object by initializing values
     * fees is going to be $500 per year (used USD instead of Inr because only $ sign is present in keyboard)
     * initially fees paid is $0
     * @param id id is for the student : unique
     * @param name name of the student.
     * Not going to alter student name and id
     * @param grade grade of the student
     */
    public Student(int id, String name,int grade){
        this.feesPaid=0;
        this.feesTotal=500;
        this.id=id;
        this.name=name;
        this.Grade=grade;

    }

    /**
     * used to update student grade
     * @param grade new grade of the student
     */
    public void setGrade(int grade){
        this.Grade=grade;

    }

    /**
     * add the fees to fees paid
     * keep adding the fees to fees paid field
     * the school is going to receive funds
     * @param fees
     */
    public void payFees(int fees){
        feesPaid+=fees;
        School.updateTotalMoneyEarned(feesPaid);
    }

    /**
     * due fees
     * @return
     */
    public int dueFees(){
        return feesTotal-feesPaid;
    }

    /**
     *
     * @return the name, id, fees paid, fees total and grade of the student
     */
    public int getFeesPaid() {
        return feesPaid;
    }

    public int getGrade() {
        return Grade;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getFeesTotal() {
        return feesTotal;
    }
    @Override
    public String toString(){
        return "-----------------------"+"\nStudent Name: "+name+"\nID no: "+getId()+"\nStudent Grade: "+getGrade()+"\nTotal Fees: "+getFeesTotal()+"\nFees paid so far: "+ feesPaid+"\n"+"Fees Due: "+dueFees()+"\n-----------------------";
    }

}
