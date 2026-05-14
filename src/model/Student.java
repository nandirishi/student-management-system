package model;

public class Student {

    private int id;
    private String name;
    private String rollNo;
    private String department;
    private int attendance;
    private double marks;

    public Student() {
    }

    public Student(int id, String name, String rollNo,
                   String department, int attendance, double marks) {

        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.department = department;
        this.attendance = attendance;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}