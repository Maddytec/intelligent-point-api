package br.com.maddytec.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.maddytec.entities.Employee;
import br.com.maddytec.entities.Employer;
import br.com.maddytec.enums.ProfileEnum;
import br.com.maddytec.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	private static final String EMAIL = "maddytec@gmail.com";
	private static final String NUMBER_DOCUMENT_EMPLOYEE = "12345678910";
	
	@Before
	public void setUp() throws Exception {
		Employer employer = this.employerRepository.save(getDataEmployer());
		this.employeeRepository.save(getDataEmployee(employer));
	}
	
	@After
	public void tearDown() {
		this.employerRepository.deleteAll();
	}
	
	@Test
	public void testGetEmployeeByEmail() {
		Employee employee = this.employeeRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, employee.getEmail());
	}
	
	@Test
	public void testGetEmployeeByNumberDocumentEmployee() {
		Employee employee = this.employeeRepository.findByNumberDocumentEmployee(NUMBER_DOCUMENT_EMPLOYEE);
		assertEquals(NUMBER_DOCUMENT_EMPLOYEE, employee.getNumberDocumentEmployee());
	}
	
	@Test
	public void testGetEmployeeByNumberDocumentEmployeeAndEmail() {
		Employee employee = this.employeeRepository.findByNumberDocumentEmployeeOrEmail(NUMBER_DOCUMENT_EMPLOYEE, EMAIL);
		
		assertNotNull(employee);
	}
	
	@Test
	public void testGetEmployeeByNumberDocumentEmployeeAndEmailForEmailInvalid() {
		Employee employee = this.employeeRepository.findByNumberDocumentEmployeeOrEmail(NUMBER_DOCUMENT_EMPLOYEE, "invalid@email.com");
		
		assertNotNull(employee);
	}
	
	@Test
	public void testGetEmployeeByNumberDocumentEmployeeAndEmailForEmployeeByNumberDocumentEmployeeInvalid() {
		Employee employee = this.employeeRepository.findByNumberDocumentEmployeeOrEmail("61749641", EMAIL);
		
		assertNotNull(employee);
	}
	
	private Employee getDataEmployee(Employer employer) {
		Employee employee = new Employee();
		employee.setEmployeeName("Madson Silva");
		employee.setProfileEnum(ProfileEnum.ROLE_USER);
		employee.setPassword(PasswordUtils.generateBCrypt("1234"));
		employee.setNumberDocumentEmployee(NUMBER_DOCUMENT_EMPLOYEE);
		employee.setEmail(EMAIL);
		employee.setEmployer(employer);
		return employee;
	}
	
	private Employer getDataEmployer() {
		Employer employer = new Employer();
		employer.setEmployerName("Maddytec Tecnologia ao seu alcance");
		employer.setNumberDocumentEmployer("112233445566");
		return employer;
	}
}
