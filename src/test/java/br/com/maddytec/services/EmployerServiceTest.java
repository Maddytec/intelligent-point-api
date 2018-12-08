package br.com.maddytec.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.maddytec.entities.Employer;
import br.com.maddytec.repositories.EmployerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployerServiceTest {

	@MockBean
	private EmployerRepository employerRepository;

	@Autowired
	private EmployerService employerService;

	private static final String NUMBER_DOCUMENT_EMPLOYER = "1234567891011";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.employerRepository.findByNumberDocumentEmployer(Mockito.anyString()))
				.willReturn(new Employer());
		BDDMockito.given(this.employerRepository.save(Mockito.any(Employer.class))).willReturn(new Employer());
	}
	
	@Test
	public void testGetEmployerByNumberDocumentEmployer() {
		Optional <Employer> employer = this.employerService.findByNumberDocumentEmployer(NUMBER_DOCUMENT_EMPLOYER); 
		
		assertTrue(employer.isPresent());
	}
	
	@Test
	public void testSaveEmployer() {
		Employer employer = this.employerService.save(new Employer());
		
		assertNotNull(employer);
	}

}
