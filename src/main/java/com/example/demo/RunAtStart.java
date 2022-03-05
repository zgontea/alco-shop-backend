package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    public void runAtStart(){
        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setLastName("Kowalski");
        employee.setSalary(new BigDecimal("3000"));

        employeeRepository.save(employee);

    }
}
