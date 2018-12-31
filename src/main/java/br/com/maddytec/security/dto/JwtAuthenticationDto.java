package br.com.maddytec.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString
public class JwtAuthenticationDto {
	
	@NotEmpty(message = "Email is not empty.")
	@Email(message = "Email invalid.")
	private String email;
	
	@NotEmpty(message = "Password is not empty.")
	private String password;

}
