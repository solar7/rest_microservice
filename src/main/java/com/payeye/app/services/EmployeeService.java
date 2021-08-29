package com.payeye.app.services;

import com.payeye.app.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Iterable<Employee> findAllEmployees();

    Optional<Employee> findEmployeeById(Integer employeeId);

    boolean deleteEmployeeIfExists(int employeeId);

    boolean updateEmployeeIfExists(Integer employeeId, Employee employee, List<String> address);

    void addEmployee(Employee employee, List<String> address);

}
