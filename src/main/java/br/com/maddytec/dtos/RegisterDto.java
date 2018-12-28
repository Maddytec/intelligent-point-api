package br.com.maddytec.dtos;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterDto {
	private Optional<Long> id = Optional.empty();
	
	@NotEmpty(message = "Date can not be empty")
	private String date;
	
	private String type;
	private String description;
	private String location;
	private Long employeeId;

}
