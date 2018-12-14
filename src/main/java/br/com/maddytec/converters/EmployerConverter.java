package br.com.maddytec.converters;

import br.com.maddytec.dtos.EmployerDto;
import br.com.maddytec.entities.Employer;

public class EmployerConverter {

	public static Employer employerDtoForEmployer(EmployerDto employerDto) {
		Employer employer = new Employer();
		employer.setEmployerName(employerDto.getEmployerName());
		employer.setNumberDocumentEmployer(employerDto.getNumberDocumentEmployer());
		return employer;
	}
}
