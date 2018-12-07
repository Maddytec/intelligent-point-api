package br.com.maddytec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.maddytec.entities.Employee;

@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Employee findByNumberDocumentEmployee(String numberDocumentEmployee);
	
	Employee findByEmail(String email);
	
	Employee findByNumberDocumentEmployeeOrEmail(String NumberDocumentEmployee, String email);
}
