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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maddytec.converters.EmployeeConverter;
import br.com.maddytec.converters.EmployerConverter;
import br.com.maddytec.converters.EmployerDtoConverter;
import br.com.maddytec.dtos.EmployerDto;
import br.com.maddytec.entities.Employee;
import br.com.maddytec.entities.Employer;
import br.com.maddytec.response.Response;
import br.com.maddytec.services.EmployeeService;
import br.com.maddytec.services.EmployerService;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/employer")
@CrossOrigin(origins = "*")
@NoArgsConstructor
public class EmployerController {

	private static final Logger log = LoggerFactory.getLogger(EmployerController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployerService employerService;

	@PostMapping
	public ResponseEntity<Response<EmployerDto>> register(@Valid @RequestBody EmployerDto employerDto,
			BindingResult bindingResult) throws NoSuchAlgorithmException {
		log.info("Registering employer: {}", employerDto);

		Response<EmployerDto> response = new Response<EmployerDto>();

		validationDataEmployer(employerDto, bindingResult);
		Employer employer = EmployerConverter.employerDtoForEmployer(employerDto);
		Employee employee = EmployeeConverter.employerDtoForEmployee(employerDto);

		if (bindingResult.hasErrors()) {
			log.error("Error in the validation of data registering employer: {}", bindingResult.getAllErrors());
			bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.employerService.save(employer);
		employee.setEmployer(employer);
		this.employeeService.save(employee);

		response.setData(EmployerDtoConverter.employeeForEmployerDto(employee));
		return ResponseEntity.ok(response);
	}

	private void validationDataEmployer(EmployerDto employerDto, BindingResult result) {
		this.employerService.findByNumberDocumentEmployer(employerDto.getNumberDocumentEmployer())
				.ifPresent(employer -> result.addError(new ObjectError("Employer", "Employer already exist.")));

		this.employeeService.findByEmail(employerDto.getEmail()).ifPresent(
				employee -> result.addError(new ObjectError("Employee", "Email of the employee already exist.")));

		this.employeeService.findByNumberDocumentEmployee(employerDto.getNumberDocumentEmployee())
				.ifPresent(employee -> result
						.addError(new ObjectError("Employee", "Document number of the employee already exist.")));
	}

	@GetMapping(value = "/{numberDocumentEmployer}")
	public ResponseEntity<Response<EmployerDto>> findByNumberDocumentEmployer(
			@PathVariable("numberDocumentEmployer") String numberDocumentEmployer) {
		log.info("Find employer by number Document: {}", numberDocumentEmployer);

		Response<EmployerDto> response = new Response<EmployerDto>();
		Optional<Employer> employer = this.employerService.findByNumberDocumentEmployer(numberDocumentEmployer);

		if (!employer.isPresent()) {
			log.info("Employer  was are not encountered by number document: {}", numberDocumentEmployer);
			response.getErrors().add("Employer was are not encountered by number document: " + numberDocumentEmployer);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(EmployerConverter.employerForEmployerDto(employer.get()));
		return ResponseEntity.ok(response);
	}

}
