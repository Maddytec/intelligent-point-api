package br.com.maddytec.services;

import java.util.Optional;

import br.com.maddytec.entities.Employer;

/**
 * @author Madson Silva
 *
 */
public interface EmployerService {
/**
 * Return Employer by number of document 
 * 	
 * @param numberDocumentEmployer
 * @return Optional
 */
	Optional<Employer> findByNumberDocumentEmployer(String numberDocumentEmployer); 

/**
 * Rigister new Employer of database 
 * 	
 * @param employer
 * @return Employer
 */
	Employer save(Employer employer);
}
