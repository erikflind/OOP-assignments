package assignment03;

public class Manager extends Employee{

    private String degree;

    public Manager(String employeeID, String name, double grossSalary, String degree) throws Exception {
        super(employeeID, name, grossSalary);
        if (!(degree.equals("BSc") || degree.equals("MSc") || degree.equals("PhD"))) {
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }
        this.degree = degree;
    }


    @Override
    public String toString(){
        return String.format(getDegree() + " " + getEmployeeName() +
                "'s gross salary is %.2f SEK per month.", getGrossSalary());
    }

    public void setDegree(String newDegree) throws Exception {
        if (!(newDegree.equals("BSc") || newDegree.equals("MSc") || newDegree.equals("PhD"))) {
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }
        this.degree = newDegree;
    }

    // GETTER METHODS
    @Override
    public double getGrossSalary(){
        double bonus = 1.00;
        switch (this.degree) {
            case "BSc": bonus = 1.10;
            break;
            case "MSc": bonus = 1.20;
            break;
            case "PhD": bonus = 1.35;
        }
        return truncateSalary(super.getGrossSalary() * bonus);
    }

    public String getDegree(){
        return this.degree;
    }

}
