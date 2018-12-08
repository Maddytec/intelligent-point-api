package br.com.maddytec.services;

import java.util.Optional;

import br.com.maddytec.entities.Employee;

public interface EmployeeService {
	
	/**
	 *Save Employee of database
	 * 
	 * @param employee
	 * @return Employee
	 */
	Employee save(Employee employee);
	
	/**
	 * Find and return Employee by number document the employee 
	 * 
	 * @param numberDocumentEmployee
	 * @return Optional<Employee>
	 */
	Optional<Employee> findByNumberDocumentEmployee(String numberDocumentEmployee);
	
	/**
	 * Find Employee by email
	 * 
	 * @param email
	 * @return Optional<Employee>
	 */
	Optional<Employee> findByEmail(String email);
	
	/**
	 * Find Employee by Id
	 * 
	 * @param id
	 * @return Optional<Employee>
	 */
	Optional<Employee> findById(Long id);
}
