package lab05;
public class EmployeeDepartmentInfo {
    private String employeeName;
    private String position;
    private String departmentName;

    public EmployeeDepartmentInfo(String employeeName,
                                  String position,
                                  String departmentName) {
        this.employeeName = employeeName;
        this.position = position;
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeDepartmentInfo{" +
                "employeeName='" + employeeName + '\'' +
                ", position='" + position + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
