package br.com.maddytec.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.maddytec.entities.Employer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployerRepositoryTest {

	@Autowired
	private EmployerRepository employerRepository;
	
	private static final String NUMBER_DOCUMENT_EMPLOYER = "123456789101112";
	private static final String EMPLOYER = "Maddytec Tecnologia ao seu alcance";
	
	@Before
	public void setUp() throws Exception {
		Employer employer = new Employer();
		employer.setEmployerName(EMPLOYER);
		employer.setNumberDocumentEmployer(NUMBER_DOCUMENT_EMPLOYER);
		this.employerRepository.save(employer);
	}
	
	@After
	public final void tearDown() {
		this.employerRepository.deleteAll();
	}
	
	@Test
	public void testFindByNumberDocumentEmployer() {
		Employer employer = this.employerRepository.findByNumberDocumentEmployer(NUMBER_DOCUMENT_EMPLOYER);
		
		assertEquals(NUMBER_DOCUMENT_EMPLOYER, employer.getNumberDocumentEmployer());
	}
	
	@Test
	public void testFindByEmployer() {
		Employer employer = this.employerRepository.findByEmployerName(EMPLOYER);
		
		assertEquals(EMPLOYER, employer.getEmployerName());
	}
}
