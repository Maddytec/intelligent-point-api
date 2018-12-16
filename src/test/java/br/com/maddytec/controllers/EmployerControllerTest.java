package br.com.maddytec.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.maddytec.entities.Employer;
import br.com.maddytec.services.EmployerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployerControllerTest implements Cloneable {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployerService employerService;

	private static final String FIND_EMPLOYER_NUMBER_DOCUMENT_URL = "/employer/";
	private static final String NUMBER_DOCUMENT_EMPLOYER = "33200049001380";
	private static final String EMPLOYER_NAME = "Maddytec Tecnologia ao seu alcance";

	@Test
	public void testFindByEmployerByNumeberDocumentInvalid() throws Exception {
		BDDMockito.given(this.employerService.findByNumberDocumentEmployer(Mockito.anyString()))
				.willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.get(FIND_EMPLOYER_NUMBER_DOCUMENT_URL + NUMBER_DOCUMENT_EMPLOYER)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors")
						.value("Employer was are not encountered by number document: " + NUMBER_DOCUMENT_EMPLOYER));
	}

	@Test
	public void testFindByEmployerByNumeberDocumentValid() throws Exception {
		BDDMockito.given(this.employerService.findByNumberDocumentEmployer(Mockito.anyString()))
				.willReturn(Optional.of(this.getDataEmployer()));

		mvc.perform(MockMvcRequestBuilders.get(FIND_EMPLOYER_NUMBER_DOCUMENT_URL + NUMBER_DOCUMENT_EMPLOYER)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.employerName", equalTo(EMPLOYER_NAME)))
				.andExpect(jsonPath("$.data.numberDocumentEmployer", equalTo(NUMBER_DOCUMENT_EMPLOYER)))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	private Employer getDataEmployer() {
		Employer employer = new Employer();
		employer.setEmployerName(EMPLOYER_NAME);
		employer.setNumberDocumentEmployer(NUMBER_DOCUMENT_EMPLOYER);
		return employer;
	}

}
