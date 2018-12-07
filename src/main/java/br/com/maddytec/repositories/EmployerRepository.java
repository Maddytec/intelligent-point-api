package br.com.maddytec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.maddytec.entities.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {

	@Transactional(readOnly = true)
	Employer findByNumberDocumentEmployer(String numberDocumentEmployer) ;
	
	@Transactional(readOnly = true)
	Employer findByEmployer(String employer) ;
	
}
