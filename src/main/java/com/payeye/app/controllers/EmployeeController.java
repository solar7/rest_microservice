package com.payeye.app.controllers;

import com.payeye.app.model.Employee;
import com.payeye.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public Iterable<Employee> findAll() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> findById(@PathVariable Integer employeeId) {
        return employeeService.findEmployeeById(employeeId)
                .map(employee -> new ResponseEntity<>(employee, OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping("/employees/new")
    public void addEmployee(@Valid Employee employee,
                            @RequestParam @NotEmpty List<String> address) {
        employeeService.addEmployee(employee, address);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int employeeId) {
        boolean deleted = employeeService.deleteEmployeeIfExists(employeeId);
        return new ResponseEntity<>(deleted ? NO_CONTENT : NOT_FOUND);
    }

    @PutMapping("/employees/{employeeId}/edit")
    public ResponseEntity<Employee> updateEmployee(@Valid Employee employee,
                                                   @PathVariable @NotNull Integer employeeId,
                                                   @RequestParam List<String> address) {
        boolean updated = employeeService.updateEmployeeIfExists(employeeId, employee, address);
        return new ResponseEntity<>(updated ? NO_CONTENT : NOT_FOUND);
    }

}
