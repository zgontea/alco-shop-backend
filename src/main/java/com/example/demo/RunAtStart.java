package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        super();
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @PostConstruct
    public void runAtStart(){
        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setLastName("Banan");
        employee.setSalary(new BigDecimal("4000"));

        // employeeRepository.save(employee);

        Set<Employee> employees = new HashSet<Employee>();

        employees.add(employee);

        Department dep = new Department();
        dep.setName("JakisTam");
        dep.setEmployees(employees);

        departmentRepository.save(dep);

        Iterable<Employee> jansIterable = employeeRepository.findAllWhereName("Banan");

        jansIterable.forEach(e -> {
            System.out.println(e);
        });

    }
}
