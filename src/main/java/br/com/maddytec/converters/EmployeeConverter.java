package br.com.maddytec.converters;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import br.com.maddytec.dtos.EmployeeDto;
import br.com.maddytec.dtos.EmployerDto;
import br.com.maddytec.entities.Employee;
import br.com.maddytec.enums.ProfileEnum;

public class EmployeeConverter {

	public static Employee employerDtoForEmployee(EmployerDto employerDto) {
		Employee employee = new Employee();
		employee.setEmployeeName(employerDto.getEmployeeName());
		employee.setEmail(employerDto.getEmail());
		employee.setNumberDocumentEmployee(employerDto.getNumberDocumentEmployee());
		employee.setProfileEnum(ProfileEnum.ROLE_ADMIN);
		employee.setPassword(employerDto.getPassword());
		return employee;
	}

	public static Employee employeeDtoForEmployee(EmployeeDto employeeDto) throws NoSuchAlgorithmException {
		Employee employee = new Employee();
		employee.setEmployeeName(employeeDto.getEmployeeName());
		employee.setEmail(employeeDto.getEmail());
		employee.setNumberDocumentEmployee(employeeDto.getNumberDocumentEmployee());
		employee.setProfileEnum(ProfileEnum.ROLE_USER);
		employee.setPassword(employeeDto.getPassword());
		employeeDto.getCountHoursLunch()
				.ifPresent(countHoursLunch -> employee.setCountHoursLunch(Float.valueOf(countHoursLunch)));
		employeeDto.getCountHoursWorkDay()
				.ifPresent(countHoursWorkDay -> employee.setCountHoursWorkDay(Float.valueOf(countHoursWorkDay)));
		employeeDto.getValueHour().ifPresent(valueHour -> employee.setValueHour(new BigDecimal(valueHour)));
		return employee;
	}

}
