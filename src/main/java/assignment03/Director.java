package assignment03;

public class Director extends Manager{

    private String department;

    // Director's benefit to their salary
    private static final double ADDITIONAL_SALARY = 5000.00;

    // The tax bracket under which the tax rate is 20 % and over which the tax rate is 40 %
    private static final double TAX_BRACKET = 30000.00;

    public Director(String employeeID, String name, double grossSalary, String degree, String department)
            throws Exception {
        super(employeeID, name, grossSalary, degree);
        if (!(department.equals("Business") || department.equals("Human Resources") || department.equals("Technical"))){
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }
        this.department = department;
    }


    @Override
    public String toString(){
        return String.format(getDegree() + " " + getEmployeeName() + "'s gross salary is %.2f SEK per month. Dept: " +
                getDepartment(), getGrossSalary());
    }

    public void setDepartment(String department) throws Exception {
        if (!(department.equals("Business") || department.equals("Human Resources") || department.equals("Technical"))){
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }
        this.department = department;
    }

    // GETTER METHODS
    @Override
    public double getGrossSalary(){
        return truncateSalary(super.getGrossSalary() + ADDITIONAL_SALARY);
    }

    @Override
    public double getNetSalary(){
        double netSalary = 0.0;
        if (getGrossSalary() < 30000.00) {
            netSalary = super.getNetSalary();
        } else if (getGrossSalary() <= 50000.00) {
            netSalary = getGrossSalary() * 0.8;
        } else if (getGrossSalary() > 50000.00){
            netSalary = getGrossSalary() - (TAX_BRACKET * 0.2) - ((getGrossSalary() - TAX_BRACKET) * 0.4);
        }
        return truncateSalary(netSalary);
    }

    public String getDepartment(){
        return this.department;
    }


}
