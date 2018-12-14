package br.com.maddytec.converters;

import br.com.maddytec.dtos.EmployerDto;
import br.com.maddytec.entities.Employee;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployerDtoConverter {

	public static EmployerDto employeeForEmployerDto(Employee employee) {
		EmployerDto employerDto = new EmployerDto();
		employerDto.setId(employee.getId());
		employerDto.setEmployeeName(employee.getEmployeeName());
		employerDto.setEmail(employee.getEmail());
		employerDto.setNumberDocumentEmployee(employee.getNumberDocumentEmployee());
		employerDto.setEmployerName(employee.getEmployer().getEmployerName());
		employerDto.setNumberDocumentEmployer(employee.getEmployer().getNumberDocumentEmployer());

		return employerDto;
	}
	
}
