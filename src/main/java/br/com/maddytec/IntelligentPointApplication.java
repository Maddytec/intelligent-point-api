package br.com.maddytec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.maddytec.enums.ProfileEnum;
import br.com.maddytec.security.entities.User;
import br.com.maddytec.security.repositories.UserRepository;

@SpringBootApplication
public class IntelligentPointApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelligentPointApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userRepository, passwordEncoder);
		};
	}
	
	private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		User admin = new User();
	/*	admin.setEmail("maddytec@gmail.com");
		admin.setPassword(passwordEncoder.encode("12345678"));
		admin.setProfileEnum(ProfileEnum.ROLE_ADMIN);
		
		User find = userRepository.findByEmail("maddytec@gmail.com");
		if(find == null) {
			userRepository.save(admin);
		}
		*/
	}
}
