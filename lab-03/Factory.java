// 2.1 (інкапсуляція)
public class Factory {
    private String name;
    private String city;
    private  int workers;
    private int outputPerDay;
    private double price;
    private double salaryWorkers;
    private int foundedYear;

    public Factory() { }

    public Factory(String name, String city, int workers, int outputPerDay, double price, double salaryWorkers, int foundedYear) {
        this.name = name;
        this.city = city;
        setWorkers(workers);
        this.outputPerDay = outputPerDay;
        this.price = price;
        this.salaryWorkers = salaryWorkers; 
        this.foundedYear = foundedYear;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    } 


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }


    public int getWorkers() {
        return workers;
    }
    public void setWorkers(int workers) {
        if (workers >= 0) {
            this.workers = workers; 
        }
    } 


    public int getOutputPerDay() {
        return outputPerDay;
    }
    public void setOutputPerDay(int outputPerDay) {
        this.outputPerDay = outputPerDay;
    }


    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


    public double getSalaryWorkers() {
        return salaryWorkers;
    }
    public void setSalaryWorkers(double salaryWorkers) {
        this.salaryWorkers = salaryWorkers;
    }


    public int getFoundedYear() {
        return foundedYear;
    }
    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    //  виручка за день
    public double dailyRevenue(int outputPerDay, double price) {
        return outputPerDay * price;
    }

    // фонд зарплат за місяць
    public double monthlySalaryCost(int workers, double salaryWorkers) {
        return workers * salaryWorkers;
    }

    // скільки років заводу
    public int age(int currentYear, int foundedYear) {
        return currentYear - foundedYear;
    }
}
