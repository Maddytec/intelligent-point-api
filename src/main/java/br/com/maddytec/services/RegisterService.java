package br.com.maddytec.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.maddytec.entities.Register;

public interface RegisterService {

	/**
	 * Return a list pageded of employee by Id
	 * 
	 * @param employeeId
	 * @param pageRequest
	 * @return Page<Register>
	 */
	Page<Register> findEmployeeById(Long employeeId, PageRequest pageRequest);

	/**
	 * Find register by Id
	 * 
	 * @param registerId
	 * @return Optional<Register>
	 */
	Optional<Register> findById(Long registerId);

	/**
	 * Saving register in the database
	 * 
	 * @param register
	 * @return Register
	 */
	Register save(Register register);

	/**
	 * Delete register by Id
	 * 
	 * @param registerId
	 */
	void delete(Long registerId);

}