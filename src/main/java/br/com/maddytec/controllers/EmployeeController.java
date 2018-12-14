package br.com.maddytec.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maddytec.converters.EmployeeConverter;
import br.com.maddytec.converters.EmployeeDtoConverter;
import br.com.maddytec.dtos.EmployeeDto;
import br.com.maddytec.entities.Employee;
import br.com.maddytec.entities.Employer;
import br.com.maddytec.response.Response;
import br.com.maddytec.services.EmployeeService;
import br.com.maddytec.services.EmployerService;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
@NoArgsConstructor
public class EmployeeController {

	private Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployerService employerService;

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Response<EmployeeDto>> register(@Valid @RequestBody EmployeeDto employeeDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("EmployeeDto: {}", employeeDto);
		Response<EmployeeDto> response = new Response<EmployeeDto>();

		validationDataEmployee(employeeDto, result);
		Employee employee = EmployeeConverter.employeeDtoForEmployee(employeeDto);

		if (result.hasErrors()) {
			log.error("Error in the validation of data registering employee: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<Employer> employer = this.employerService
				.findByNumberDocumentEmployer(employeeDto.getNumberDocumentEmployer());
		employer.ifPresent(emp -> employee.setEmployer(emp));
		this.employeeService.save(employee);

		response.setData(EmployeeDtoConverter.employeeForEmployeeDto(employee));
		return ResponseEntity.ok(response);
	}

	/**
	 * Validate if employer already exist and if employee already registered in the
	 * database
	 * 
	 * @param employeeDto
	 * @param result
	 */
	private void validationDataEmployee(@Valid EmployeeDto employeeDto, BindingResult result) {
		Optional<Employer> employer = this.employerService
				.findByNumberDocumentEmployer(employeeDto.getNumberDocumentEmployer());
		if (!employer.isPresent()) {
			result.addError(new ObjectError("Employer", "Employer already exist."));
		}

		this.employeeService.findByEmail(employeeDto.getEmail()).ifPresent(
				employee -> result.addError(new ObjectError("Employee", "Email of the employee already exist.")));

		this.employeeService.findByNumberDocumentEmployee(employeeDto.getNumberDocumentEmployee())
				.ifPresent(employee -> result
						.addError(new ObjectError("Employee", "Document number of the employee already exist.")));

	}
}
