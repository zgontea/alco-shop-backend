package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
		RunAtStart runAtStart = new RunAtStart(employeeRepository);
		runAtStart.runAtStart();

//		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
//		Employee employee1 = new Employee();
//		employee1.setFirstName("Jan");
//		employee1.setLastName("Kowalczyk");
//		employee1.setSalary(new BigDecimal(3000));
//		employeeRepository.save(employee1);
	}
}
