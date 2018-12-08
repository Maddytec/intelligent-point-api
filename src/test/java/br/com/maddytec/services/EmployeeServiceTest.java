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

import br.com.maddytec.entities.Employee;
import br.com.maddytec.repositories.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTest {

	@MockBean
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employerService;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.employeeRepository.save(Mockito.any(Employee.class))).willReturn(new Employee());
		BDDMockito.given(this.employeeRepository.findById(Mockito.anyLong()))
				.willReturn(Optional.ofNullable(new Employee()));
		BDDMockito.given(this.employeeRepository.findByEmail(Mockito.anyString())).willReturn(new Employee());
		BDDMockito.given(this.employeeRepository.findByNumberDocumentEmployee(Mockito.anyString()))
				.willReturn(new Employee());
	}

	@Test
	public void testSavingEmployee() {
		Employee employee = this.employerService.save(new Employee());
		assertNotNull(employee);
	}

	@Test
	public void testFindEmployeeById() {
		Optional<Employee> employee = this.employerService.findById(1L);

		assertTrue(employee.isPresent());
	}

	@Test
	public void testFindEmployeeByEmail() {
		Optional<Employee> employee = this.employerService.findByEmail("maddytec@gmail.com");

		assertTrue(employee.isPresent());
	}

	@Test
	public void testFindEmployeeByNumberDocument() {
		Optional<Employee> employee = this.employerService.findByNumberDocumentEmployee("218198498414");

		assertTrue(employee.isPresent());
	}

}
