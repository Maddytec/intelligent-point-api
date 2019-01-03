package br.com.maddytec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.maddytec.security.repositories.UserRepository;

@SpringBootApplication
@EnableCaching
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
	/*	User admin = new User();
		admin.setEmail("madson.silva@maddytec.com.br");
		admin.setPassword(passwordEncoder.encode("12345678"));
		admin.setProfileEnum(ProfileEnum.ROLE_ADMIN);
		
		User find = userRepository.findByEmail("madson.silva@maddytec.com.br");
		if(find == null) {
			userRepository.save(admin);
		}
		*/
	}
}
