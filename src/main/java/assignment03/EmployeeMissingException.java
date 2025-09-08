package assignment03;

public class EmployeeMissingException extends Exception{

    public EmployeeMissingException() {
        super("No employees registered yet.");
    }
    public EmployeeMissingException(String employeeID) {
        super("Employee " + employeeID + " was not registered yet.");
    }

}
