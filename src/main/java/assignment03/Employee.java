package assignment03;

public class Employee implements Comparable<Employee> {

    private final String employeeID;
    private String employeeName;
    private double grossSalary;

    public Employee (String employeeID, String name, double grossSalary) throws Exception {
        if (employeeID.isBlank()) {
            throw new EmployeeIDException();
        }
        if (name.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }
        if (grossSalary <= 0) {
            throw new Exception("Salary must be greater than zero.");
        }
        this.employeeID = employeeID;
        this.employeeName = name;
        this.grossSalary = truncateSalary(grossSalary);
    }


    @Override
    public int compareTo(Employee employee){
        double firstEmployee = this.getGrossSalary();
        double otherEmployee = employee.getGrossSalary();
        // returns 1 or -1 depending on which double variable value is greater, 0 if they are equal
        return Double.compare(firstEmployee, otherEmployee);
    }

    public double truncateSalary(double salary){
        return (Math.floor(salary * 100) / 100);
    }

    public String toString(){
        return String.format(getEmployeeName() + "'s gross salary is %.2f SEK per month.", getGrossSalary());
    }

    // SETTER METHODS
    public void setEmployeeName(String employeeName) throws Exception {
        if (employeeName.isBlank()) {
            throw new Exception("Name cannot be blank.");
        }
        this.employeeName = employeeName;
    }
    public void setGrossSalary(double grossSalary) throws Exception {
        if (grossSalary <= 0) {
            throw new Exception("Salary must be greater than zero.");
        }
        truncateSalary(this.grossSalary = grossSalary);
    }

    // GETTER METHODS
    public String getEmployeeID(){
        return this.employeeID;
    }
    public String getEmployeeName(){
        return this.employeeName;
    }
    public double getGrossSalary(){
        return this.grossSalary;
    }
    public double getRawSalary(){
        return this.grossSalary;
    }
    public double getNetSalary(){
        return truncateSalary(getGrossSalary() * 0.9);
    }

}
