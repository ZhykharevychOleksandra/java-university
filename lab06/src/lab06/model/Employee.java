package lab06.model;

import lab06.annotations.NumericFormat;
import lab06.annotations.Range;
import lab06.annotations.SalaryByExperience;
import java.time.LocalDate;

// Доменна сутність "Співробітник" (варіант 3). Рівно 5 полів різних типів
@SalaryByExperience(
        experienceField = "experienceYears",
        salaryField = "salary",
        minYears = 5,
        minSalary = 30000.0)
public class Employee {

    private String fullName;                    

    @NumericFormat(length = 10)                 
    private String taxId;                       

    @Range(min = 8000.0, max = 500000.0)         
    private double salary;                        

    @Range(min = 0, max = 50)                     
    private int experienceYears;         

    private LocalDate hireDate;         

    public Employee(String fullName, String taxId, double salary, int experienceYears, LocalDate hireDate) {
        this.fullName = fullName;
        this.taxId = taxId;
        this.salary = salary;
        this.experienceYears = experienceYears;
        this.hireDate = hireDate;
    }

    public String getFullName() { return fullName; }

    public String getTaxId() { return taxId; }

    public double getSalary() { return salary; }

    public int getExperienceYears() { return experienceYears; }

    public LocalDate getHireDate() { return hireDate; }

    @Override
    public String toString() {
        return "Employee{fullName='" + fullName + "', taxId='" + taxId + "', salary=" + salary
                + ", experienceYears=" + experienceYears + ", hireDate=" + hireDate + "}";
    }
}

