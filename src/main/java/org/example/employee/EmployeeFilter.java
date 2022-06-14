package org.example.employee;

import org.example.logger.EmployeeLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class EmployeeFilter {

    private static List<EmployeeDTO> employees = new ArrayList<>();

    private static List<EmployeeDTO> duplicates = new ArrayList<>();


    public static List<EmployeeDTO> removeDuplicates(List<EmployeeDTO> listEmployees) {
        EmployeeLogger logger = new EmployeeLogger();
        logger.setupLogger();
        logger.getLogger().log(Level.INFO, "Removing duplicates from list employees...");

        employees = new ArrayList<>(listEmployees);

        for (int i = 0; i < listEmployees.size(); i++) {
            for (int j = i + 1; j < listEmployees.size(); j++) {
                if (listEmployees.get(i).getEmpID() == listEmployees.get(j).getEmpID()) {
                    duplicates.add(listEmployees.get(i));
                    employees.remove(listEmployees.get(i));
                }
            }
        }

        for (int i = 0; i < employees.size(); i ++) {
            for (int j = 0; j < duplicates.size(); j ++) {
                if (employees.get(i).getEmpID() == duplicates.get(j).getEmpID()) {
                    employees.remove(employees.get(i));
                    i --;
                }
            }
        }

        logger.getLogger().log(Level.INFO, "Returning filtered list of " + employees.size() + " employees");
        logger.closeHandler();
        return employees;
    }

    public static List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static List<EmployeeDTO> getDuplicates() {
        return duplicates;
    }

}

