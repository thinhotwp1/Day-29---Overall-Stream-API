public class Employee {
    public enum Status { ACTIVE, INACTIVE }

    private int id;
    private String name;
    private String department;
    private String title;
    private Status status;
    private double salary;

    // Constructor, Getters...
    public Employee(int id, String name, String department, String title, Status status, Double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.title = title;
        this.status = status;
        this.salary = salary;
    }

    public String getDepartment() { return department; }
    public String getTitle() { return title; }
    public Status getStatus() { return status; }
    public double getSalary() { return salary; }
}