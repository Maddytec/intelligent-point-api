package br.com.maddytec.dtos;

import java.util.Optional;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {

	private Long id;
	
	@Length(min = 3, max = 200, message = "Name must is between 3 and 200 characters.")
	private String employeeName;
	
	@Length(min = 5, max = 200, message = "Email must is between 5 and 200 characters.")
	@Email(message = "Email invalid")
	private String email;
	
	private String password;
	
	@CPF(message = "Document number of employee invalid.")
	private String numberDocumentEmployee;
	
	private Optional<String> valueHour = Optional.empty();
	private Optional<String> countHoursWorkDay = Optional.empty();
	private Optional<String> countHoursLunch = Optional.empty();

	//@NotEmpty(message = "Document number of the employer can't is empty.")
	@CNPJ(message = "Document number of employer invalid.")
	private String numberDocumentEmployer;
}
