package br.com.maddytec.converters;

import java.util.Optional;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.expression.ParseException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import br.com.maddytec.dtos.RegisterDto;
import br.com.maddytec.entities.Employee;
import br.com.maddytec.entities.Register;
import br.com.maddytec.enums.TypeEnum;
import br.com.maddytec.utils.UtilDate;

public class RegisterConverter {

	public static RegisterDto registerForRegisterDto(Register register) {
		RegisterDto registerDto = new RegisterDto();

		if (register.getId() != null) {
			registerDto.setId(Optional.of(register.getId()));
		}
		registerDto.setDate(UtilDate.getDate(register.getDate()));
		registerDto.setType(register.getType().toString());
		registerDto.setDescription(register.getDescription());
		registerDto.setLocation(register.getLocation());
		registerDto.setEmployeeId(register.getEmployee().getId());

		return registerDto;
	}

	public static Register registerDtoForRegister(RegisterDto registerDto, Register register,
			BindingResult bindingResult) throws ParseException {
		if (register == null) {
			register = new Register();
		}
		
		if (!registerDto.getId().isPresent()) {
			register.setEmployee(new Employee());
			register.getEmployee().setId(registerDto.getEmployeeId());
		}

		register.setDescription(registerDto.getDescription());
		register.setLocation(registerDto.getLocation());
		register.setDate(UtilDate.getDate(registerDto.getDate()));

		if (EnumUtils.isValidEnum(TypeEnum.class, registerDto.getType())) {
			register.setType(TypeEnum.valueOf(registerDto.getType()));
		} else {
			bindingResult.addError(new ObjectError("Type", "Type invalid"));
		}

		return register;
	}
}
