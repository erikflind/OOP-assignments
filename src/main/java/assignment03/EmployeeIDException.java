package assignment03;

public class EmployeeIDException extends Exception {

    public EmployeeIDException() {
        super("ID cannot be blank.");
    }
    public EmployeeIDException(String employeeID) {
        super("Cannot register. ID " + employeeID + " is already registered.");
    }

}
