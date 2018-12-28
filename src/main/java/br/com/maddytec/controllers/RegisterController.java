package br.com.maddytec.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.expression.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.maddytec.converters.RegisterConverter;
import br.com.maddytec.dtos.RegisterDto;
import br.com.maddytec.entities.Employee;
import br.com.maddytec.entities.Register;
import br.com.maddytec.response.Response;
import br.com.maddytec.services.EmployeeService;
import br.com.maddytec.services.RegisterService;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/registers")
@CrossOrigin(origins = "*")
@NoArgsConstructor
public class RegisterController {

	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private RegisterService registerService;

	@Autowired
	private EmployeeService employeeService;

	@Value("${pagination.count_by_page}")
	private int countByPage;

	@GetMapping(value = "/employee/{employeeId}")
	public ResponseEntity<Response<Page<RegisterDto>>> registersfindByEmployeeId(
			@PathVariable("employeeId") Long employeeId, @RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {

		log.info("Searching register by Id of the employee: {}", employeeId);
		Response<Page<RegisterDto>> response = new Response<Page<RegisterDto>>();

		PageRequest pageRequest = PageRequest.of(pag, this.countByPage, Direction.valueOf(dir), ord);
		Page<Register> registers = this.registerService.findEmployeeById(employeeId, pageRequest);
		Page<RegisterDto> registerDto = registers.map(register -> RegisterConverter.registerForRegisterDto(register));

		response.setData(registerDto);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<RegisterDto>> registerById(@PathVariable("id") Long id) {
		log.info("Searching register by Id: {}", id);

		Response<RegisterDto> response = new Response<RegisterDto>();

		Optional<Register> register = this.registerService.findById(id);

		if (!register.isPresent()) {
			log.info("Register was are not find by id: {}", id);
			response.getErrors().add("Register was are not find by id: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(RegisterConverter.registerForRegisterDto(register.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<RegisterDto>> save(@Valid @RequestBody RegisterDto registerDto,
			BindingResult bindingResult) throws ParseException {
		log.info("Adding register of the employee: {}", registerDto.toString());

		Response<RegisterDto> response = new Response<RegisterDto>();

		validDataEmployee(registerDto, bindingResult);

		Register register = RegisterConverter.registerDtoForRegister(registerDto, null, bindingResult);

		if (bindingResult.hasErrors()) {
			log.error("Data of Register is not valid: {}", bindingResult.getAllErrors());
			bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		register = this.registerService.save(register);
		response.setData(RegisterConverter.registerForRegisterDto(register));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<RegisterDto>> update(@PathVariable("id") Long id,
			@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult) throws ParseException {
		log.info("Updating register: {}", registerDto.toString());
		Response<RegisterDto> response = new Response<>();
		validDataEmployee(registerDto, bindingResult);
		registerDto.setId(Optional.of(id));

		Register register = new Register();
		if (registerDto.getId().isPresent()) {
			Optional<Register> registerOptional = registerService.findById(registerDto.getId().get());
			if (registerOptional.isPresent()) {
				register = registerOptional.get();
				register = RegisterConverter.registerDtoForRegister(registerDto, register, bindingResult);
			} else {
				bindingResult
						.addError(new ObjectError("Register", "Invalid register ID: " + registerDto.getId().get()));
			}
		}
		
		if (bindingResult.hasErrors()) {
			log.error("Data of Register is not valid: {}", bindingResult.getAllErrors());
			bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		register = this.registerService.save(register);
		response.setData(RegisterConverter.registerForRegisterDto(register));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
		log.info("Deleting register: {}", id);

		Response<String> response = new Response<String>();
		Optional<Register> register = this.registerService.findById(id);

		if (!register.isPresent()) {
			log.info("Error in delete register with Id: {}", id);
			response.getErrors().add("Error in delete register, Not found register ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.registerService.delete(id);
		return ResponseEntity.ok(response);
	}

	private void validDataEmployee(RegisterDto registerDto, BindingResult bindingResult) {
		if (registerDto.getEmployeeId() == null) {
			bindingResult.addError(new ObjectError("Employee", "Employee not informed."));
			return;
		}

		log.info("Validation of the employee by Id: {}", registerDto.getId());
		Optional<Employee> employee = this.employeeService.findById(registerDto.getEmployeeId());
		if (!employee.isPresent()) {
			bindingResult.addError(new ObjectError("Employee", "Employee is not found."));
		}
	}

}
