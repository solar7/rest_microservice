package com.payeye.app.services;

import com.payeye.app.model.Address;
import com.payeye.app.model.AddressBuilder;
import com.payeye.app.model.Employee;
import com.payeye.app.repositories.AddressRepository;
import com.payeye.app.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public boolean deleteEmployeeIfExists(int employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmployeeIfExists(Integer employeeId, Employee employee, List<String> addresses) {
        if (!employeeRepository.existsById(employeeId)) {
            return false;
        }

        List<Address> addrList = AddressBuilder.buildFrom(addresses, employee);
        employee.setAddresses(addrList);
        employee.setId(employeeId);

        addressRepository.deleteAllEmployeeAddresses(employeeId);
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public void addEmployee(Employee employee, List<String> address) {
        List<Address> addresses = AddressBuilder.buildFrom(address, employee);
        employee.setAddresses(addresses);
        employeeRepository.save(employee);
    }

}
