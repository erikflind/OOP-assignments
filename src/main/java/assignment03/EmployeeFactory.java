package assignment03;

public class EmployeeFactory {

    public static Employee createEmployee(String employeeID, String name, double grossSalary) throws Exception{
        return new Employee(employeeID, name, grossSalary);
    }

    public static Intern createEmployee(String employeeID, String name, double grossSalary, int GPA) throws Exception{
        return new Intern(employeeID, name, grossSalary, GPA);
    }

    public static Manager createEmployee(String employeeID, String name, double grossSalary, String degree) throws Exception{
        return new Manager(employeeID, name, grossSalary, degree);
    }

    public static Director createEmployee(String employeeID, String name, double grossSalary, String degree,
                                          String department) throws Exception{
        return new Director(employeeID, name, grossSalary, degree, department);
    }

}
