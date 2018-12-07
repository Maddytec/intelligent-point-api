package br.com.maddytec.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.maddytec.entities.Employee;
import br.com.maddytec.entities.Employer;
import br.com.maddytec.entities.Register;
import br.com.maddytec.enums.ProfileEnum;
import br.com.maddytec.enums.TypeEnum;
import br.com.maddytec.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RegisterRepositoryTest {

	@Autowired
	RegisterRepository registerRepository;

	@Autowired
	EmployerRepository employerRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	private Long employeeId;

	private static final String EMAIL = "maddytec@gmail.com";
	private static final String NUMBER_DOCUMENT_EMPLOYEE = "12345678910";

	@Before
	public void setUp() throws Exception {
		Employer employer = this.employerRepository.save(getDataEmployer());

		Employee employee = this.employeeRepository.save(getDataEmployee(employer));
		this.employeeId = employee.getId();

		this.registerRepository.save(getDataRegisters(employee));
		this.registerRepository.save(getDataRegisters(employee));
		
	}

	@After
	public void tearDown() {
		this.employerRepository.deleteAll();
	}

	@Test
	public void testGetRegistersByEmployeeId() {
		List<Register> registers = this.registerRepository.findByEmployeeId(employeeId);

		assertEquals(2, registers.size());
	}

	@Test
	public void testGetRegistersByEmployeeIdPageable() {
		PageRequest page = PageRequest.of(0, 10);
		Page<Register> registers = this.registerRepository.findByEmployeeId(employeeId, page);
		
		assertEquals(2, registers.getTotalElements());
	}

	private Register getDataRegisters(Employee employee) {
		Register register = new Register();
		register.setDate(new Date());
		register.setType(TypeEnum.START_LUNCH);
		register.setEmployee(employee);
		return register;
	}

	private Employee getDataEmployee(Employer employer) throws NoSuchAlgorithmException {
		Employee employee = new Employee(); 
		employee.setName("Madson Silva");
		employee.setProfileEnum(ProfileEnum.ROLE_USER);
		employee.setPassword(PasswordUtils.generateBCrypt("1234"));
		employee.setNumberDocumentEmployee(NUMBER_DOCUMENT_EMPLOYEE);
		employee.setEmail(EMAIL);
		employee.setEmployer(employer);
		return employee;
	}

	private Employer getDataEmployer() {
		Employer employer = new Employer();
		employer.setEmployer("Maddytec Tecnologia ao seu alcance");
		employer.setNumberDocumentEmployer("112233445566");
		return employer;
	}

}
