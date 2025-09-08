package assignment03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Company {

    private static final String EOL = System.lineSeparator();

    private final ArrayList<Employee> employees;

    public Company (){
        this.employees = new ArrayList<>();
    }


    // CREATING EMPLOYEE METHODS
    public String createEmployee(String employeeID, String name, double grossSalary) throws Exception{
        try {
        if (findID(employeeID)) {
            throw new EmployeeIDException (employeeID);
        }
            employees.add(EmployeeFactory.createEmployee(employeeID, name, grossSalary));
            return "Employee " + employeeID + " was registered successfully.";
        } catch (EmployeeIDException employeeIDException) {
            throw new Exception(employeeIDException.getMessage());
        }
    }

    public String createEmployee(String employeeID, String name, double grossSalary, String degree) throws Exception {
        try {
            if (findID(employeeID)) {
                throw new EmployeeIDException(employeeID);
            }
            employees.add(EmployeeFactory.createEmployee(employeeID, name, grossSalary, degree));
            return "Employee " + employeeID + " was registered successfully.";
        } catch (EmployeeIDException employeeIDException) {
            throw new Exception(employeeIDException.getMessage());
        }
    }

    public String createEmployee(String employeeID, String name, double grossSalary, String degree, String department)
            throws Exception {
        try {
            if (findID(employeeID)) {
                throw new EmployeeIDException(employeeID);
            }
            employees.add(EmployeeFactory.createEmployee(employeeID, name, grossSalary, degree, department));
            return "Employee " + employeeID + " was registered successfully.";
        } catch (EmployeeIDException employeeIDException) {
            throw new Exception(employeeIDException.getMessage());
        }
    }

    public String createEmployee(String employeeID, String name, double grossSalary, int GPA) throws Exception {
        try {
            if (findID(employeeID)) {
                throw new EmployeeIDException(employeeID);
            }
            employees.add(EmployeeFactory.createEmployee(employeeID, name, grossSalary, GPA));
            return "Employee " + employeeID + " was registered successfully.";
        } catch (EmployeeIDException employeeIDException) {
            throw new Exception(employeeIDException.getMessage());
        }
    }


    // Returns true if the ID is used/has already been registered
    public boolean findID(String employeeID){
        for (Employee employee : employees){
            if (employee.getEmployeeID().equals(employeeID)) {
                return true;
            }
        }
        return false;
    }

    // Throws exception if the method can't find an existing employee with the same employeeID
    // otherwise returns the Employee object with the specified ID from the ArrayList of employees
    public Employee retrieveEmployee(String employeeID) throws Exception{
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeID)) {
                return employee;
            }
        }
        throw new EmployeeMissingException(employeeID);
    }

    // Removes an Employee object from the ArrayList
    public String removeEmployee(String employeeID) throws Exception{
        if (!findID(employeeID)){
            throw new EmployeeMissingException(employeeID);
        }
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(employeeID)) {
                employees.remove(i);
            }
        }
        return "Employee " + employeeID + " was successfully removed.";
    }


    // PRINT METHODS
    public String printEmployee(String employeeID) throws Exception {
        try {
            return retrieveEmployee(employeeID).toString();
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }

    public String printAllEmployees() throws Exception{
        if (employees.size() == 0) {
            throw new EmployeeMissingException();
        }
        String allEmployees = "All registered employees:" + EOL;
        for (Employee employee : employees) {
            allEmployees += employee.toString() + EOL;
        }
        return allEmployees;
    }

    // Returns toString of each employee in a copy of the employee ArrayList, sorted by gross salary
    public String printSortedEmployees() throws Exception {
        if (employees.size() == 0) {
            throw new EmployeeMissingException();
        } else {
            String employeesSorted = "Employees sorted by gross salary (ascending order):" + EOL;
            ArrayList<Employee> sortedBySalary = employees;
            Collections.sort(sortedBySalary);
            for (Employee employee : sortedBySalary) {
                employeesSorted += employee.toString() + EOL;
            }
            return employeesSorted;
        }
    }


    /* Creates a HashMap. Iterates through the employee ArrayList, checks if the current employee is an instance of the
     * Manager class (includes Directors) and if true retrieves this.degree from the object.
     * Specifies the retrieved degree as a map key with a value of int = 1, unless the key already exists within the
     * map, in which case the method instead increments the int value inside the map key by 1.
     * */
    public HashMap<String, Integer> mapEachDegree() throws Exception {
        if (employees.size() == 0) {
            throw new EmployeeMissingException();
        }
        HashMap<String, Integer> numberOfEachDegree = new HashMap<>();
        for (Employee employee : employees) {
            if (employee instanceof Manager) {
                String currentDegree = ((Manager) employee).getDegree();
                if (numberOfEachDegree.containsKey(currentDegree)) {
                    int thisDegreeAmount = numberOfEachDegree.get(currentDegree);
                    thisDegreeAmount += 1;
                    numberOfEachDegree.put(currentDegree, thisDegreeAmount);
                } else {
                    numberOfEachDegree.put(currentDegree, 1);
                }
            }
        }
        return numberOfEachDegree;
    }


    // GETTER METHODS
    public double getNetSalary(String employeeID) throws Exception {
        try {
            return retrieveEmployee(employeeID).getNetSalary();
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }

    public double getTotalNetSalary() throws Exception {
        if (employees.size() == 0) {
            throw new EmployeeMissingException();
        }
        double totalNetSalary = 0.0;
        for (Employee employee : employees) {
            totalNetSalary += employee.getNetSalary();
        }
        return (Math.floor(totalNetSalary * 100) / 100); // truncates to two decimals
    }

    // (not used anywhere - including the tests, but part of the user story 1.2 specification)
    public int totalNumberOfEmployees() {
        return employees.size();
    }

    // (not used anywhere - including the tests, but part of the user story 1.2 specification)
    public double getTotalGrossSalary() throws Exception {
        if (employees.size() == 0) {
            throw new EmployeeMissingException();
        }
        double totalGrossSalary = 0.0;
        for (Employee employee : employees) {
            totalGrossSalary += employee.getGrossSalary();
        }
        return (Math.floor(totalGrossSalary * 100) / 100);
    }


    // UPDATING EMPLOYEE METHODS
    // With the first method the return message String used in the other methods can easily be modified
    public String employeeUpdateSucceeded(String employeeID) {
        return "Employee " + employeeID + " was updated successfully";
    }
    public String updateEmployeeName(String employeeID, String newName) throws Exception {
        try {
            retrieveEmployee(employeeID).setEmployeeName(newName);
            return employeeUpdateSucceeded(employeeID);
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }
    public String updateGrossSalary(String employeeID, double newSalary) throws Exception {
        try {
            retrieveEmployee(employeeID).setGrossSalary(newSalary);
            return employeeUpdateSucceeded(employeeID);
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }
    public String updateInternGPA(String employeeID, int newGPA) throws Exception {
        try {
            if (!(retrieveEmployee(employeeID) instanceof Intern)) {
                throw new Exception();
            }
            ((Intern) retrieveEmployee(employeeID)).setGPA(newGPA);
            return employeeUpdateSucceeded(employeeID);
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }
    public String updateManagerDegree(String employeeID, String newDegree) throws Exception {
        try {
            if (!(retrieveEmployee(employeeID) instanceof Manager)) {
                throw new Exception();
            }
            ((Manager) retrieveEmployee(employeeID)).setDegree(newDegree);
            return employeeUpdateSucceeded(employeeID);
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }
    public String updateDirectorDept(String employeeID, String newDept) throws Exception {
        try {
            if (!(retrieveEmployee(employeeID) instanceof Director)) {
                throw new Exception();
            }
            ((Director) retrieveEmployee(employeeID)).setDepartment(newDept);
            return employeeUpdateSucceeded(employeeID);
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }


    // PROMOTION METHODS
    public String promoteToIntern(String employeeID, int GPA) throws Exception {
        try {
            String employeeName = retrieveEmployee(employeeID).getEmployeeName();
            double grossSalary = retrieveEmployee(employeeID).getRawSalary();
            removeEmployee(employeeID);
            createEmployee(employeeID, employeeName, grossSalary, GPA);
            return employeeID + " promoted successfully to Intern.";
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }
    public String promoteToManager(String employeeID, String degree) throws Exception {
        try {
            String employeeName = retrieveEmployee(employeeID).getEmployeeName();
            double grossSalary = retrieveEmployee(employeeID).getRawSalary();
            removeEmployee(employeeID);
            createEmployee(employeeID, employeeName, grossSalary, degree);
            return employeeID + " promoted successfully to Manager.";
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }
    public String promoteToDirector(String employeeID, String degree, String department) throws Exception {
        try {
            String employeeName = retrieveEmployee(employeeID).getEmployeeName();
            double grossSalary = retrieveEmployee(employeeID).getRawSalary();
            removeEmployee(employeeID);
            createEmployee(employeeID, employeeName, grossSalary, degree, department);
            return employeeID + " promoted successfully to Director.";
        } catch (EmployeeMissingException employeeMissingException) {
            throw new Exception(employeeMissingException.getMessage());
        }
    }

}
