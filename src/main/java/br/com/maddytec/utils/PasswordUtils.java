package br.com.maddytec.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordUtils {
	
	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
	
	public static String generateBCrypt(String password) {
		if(password == null) {
			return password;
		}
		
		log.info("Generate hash wich BCrypt.");
		//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return null ; //bCryptPasswordEncoder.encode(password);
	}
}
