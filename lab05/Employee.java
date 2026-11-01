package lab05;
public class Employee {
    private String personnelNumber;
    private String name;
    private String position;
    private double salary;
    private int hireYear;
    private String department;
    private int completedProjectsCount;
    private int departmentId;

    public Employee(String personnelNumber, String name, String position,
                    double salary, int hireYear, String department,
                    int completedProjectsCount, int departmentId) {
        this.personnelNumber = personnelNumber;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.hireYear = hireYear;
        this.department = department;
        this.completedProjectsCount = completedProjectsCount;
        this.departmentId = departmentId;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public int getHireYear() {
        return hireYear;
    }

    public String getDepartment() {
        return department;
    }

    public int getCompletedProjectsCount() {
        return completedProjectsCount;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "personnelNumber='" + personnelNumber + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", hireYear=" + hireYear +
                ", department='" + department + '\'' +
                ", completedProjectsCount=" + completedProjectsCount +
                ", departmentId=" + departmentId +
                '}';
    }
}