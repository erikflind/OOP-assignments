package assignment03;

public class Intern extends Employee{

    private int GPA;
    private static final double INTERN_BONUS = 1000.00;

    public Intern(String employeeID, String name, double grossSalary, int GPA) throws Exception {
        super(employeeID, name, grossSalary);
        if (GPA < 0 || GPA > 10) {
            throw new Exception(GPA + " outside range. Must be between 0-10.");
        }
        this.GPA = GPA;
    }


    @Override
    public String toString(){
        return String.format(getEmployeeName() + "'s gross salary is %.2f SEK per month. GPA: "
                + getGPA(), getGrossSalary());
    }

    public void setGPA(int newGPA) throws Exception {
        if (newGPA < 0 || newGPA > 10) {
            throw new Exception(newGPA + " outside range. Must be between 0-10.");
        }
        this.GPA = newGPA;
    }

    // GETTER METHODS
    @Override
    public double getGrossSalary() {
        double grossSalary = 0.0;
        if (GPA > 5 && GPA < 8) {
            grossSalary = super.getGrossSalary();
        } else if (GPA >= 8){
            grossSalary = super.getGrossSalary() + INTERN_BONUS;
        }
        return truncateSalary(grossSalary);
    }

    @Override
    public double getNetSalary(){
        return getGrossSalary();
    }

    public int getGPA(){
        return this.GPA;
    }

}
