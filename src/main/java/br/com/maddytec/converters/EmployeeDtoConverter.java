package br.com.maddytec.converters;

import java.util.Optional;

import br.com.maddytec.dtos.EmployeeDto;
import br.com.maddytec.entities.Employee;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeeDtoConverter {

	public static EmployeeDto employeeForEmployeeDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setEmployeeName(employee.getEmployeeName());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setNumberDocumentEmployee(employee.getNumberDocumentEmployee());
		employeeDto.setNumberDocumentEmployer(employee.getEmployer().getNumberDocumentEmployer());
		employee.getCountHoursLunchOpt().ifPresent(
				countHoursLunchOpt -> employeeDto.setCountHoursLunch(Optional.of(Float.toString(countHoursLunchOpt))));
		employee.getCountHoursWorkDayOpt().ifPresent(countHoursWorkDayOpt -> employeeDto
				.setCountHoursWorkDay(Optional.of(Float.toString(countHoursWorkDayOpt))));
		employee.getValueHourOpt().ifPresent(valueHourOpt -> employeeDto.setValueHour(Optional.of(valueHourOpt.toString())));
		
		return employeeDto;
	}

}
