package br.com.maddytec.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maddytec.entities.Employee;
import br.com.maddytec.repositories.EmployeeRepository;
import br.com.maddytec.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee save(Employee employee) {
		log.info("Saving in database the employee: {}", employee);
		return this.employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> findByNumberDocumentEmployee(String numberDocumentEmployee) {
		log.info("Find employee by document number: {}", numberDocumentEmployee);
		return Optional.ofNullable(this.employeeRepository.findByNumberDocumentEmployee(numberDocumentEmployee));
	}

	@Override
	public Optional<Employee> findByEmail(String email) {
		log.info("Find employee by email: {}", email);
		return Optional.ofNullable(this.employeeRepository.findByEmail(email));
	}

	@Override
	public Optional<Employee> findById(Long id) {
		log.info("Find employee by Id: {}", id);
		return this.employeeRepository.findById(id);
	}

}
