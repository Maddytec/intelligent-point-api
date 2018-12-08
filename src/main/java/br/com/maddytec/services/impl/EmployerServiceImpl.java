package br.com.maddytec.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maddytec.entities.Employer;
import br.com.maddytec.repositories.EmployerRepository;
import br.com.maddytec.services.EmployerService;

@Service
public class EmployerServiceImpl implements EmployerService {

	private static final Logger log = LoggerFactory.getLogger(EmployerServiceImpl.class);
	
	@Autowired
	private EmployerRepository employerRepository;
		
	@Override
	public Optional<Employer> findByNumberDocumentEmployer(String numberDocumentEmployer) {
		log.info("Find employer by number document {}",numberDocumentEmployer);
		return Optional.ofNullable(employerRepository.findByNumberDocumentEmployer(numberDocumentEmployer));
	}

	@Override
	public Employer save(Employer employer) {
		log.info("Save employer: {}",employer);
		return this.employerRepository.save(employer);
	}

}
