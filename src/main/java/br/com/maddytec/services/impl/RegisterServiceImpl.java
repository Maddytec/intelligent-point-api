package br.com.maddytec.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.maddytec.entities.Register;
import br.com.maddytec.repositories.RegisterRepository;
import br.com.maddytec.services.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

	private static final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);
	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Override
	public Page<Register> findEmployeeById(Long employeeId, PageRequest pageRequest) {
		log.info("Find register of employee by Id: {}", employeeId);
		return this.registerRepository.findByEmployeeId(employeeId, pageRequest);
	}

	@Override
	@Cacheable("registerById")
	public Optional<Register> findById(Long registerId) {
		log.info("Find register by Id: {}", registerId);
		return this.registerRepository.findById(registerId);
	}

	@Override
	@CachePut("registerById")
	public Register save(Register register) {
		log.info("Saving register: {} ", register);
		return this.registerRepository.save(register);
	}

	@Override
	public void delete(Long registerId) {
		log.info("Deleting register by Id: {}", registerId);
		this.registerRepository.deleteById(registerId);
	}

}