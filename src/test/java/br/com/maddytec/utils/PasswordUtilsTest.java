package br.com.maddytec.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {
	private static final String PASSWORD = "12345";
	//private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Test
	public void testPasswordNull() throws Exception {
		assertNull(PasswordUtils.generateBCrypt(null));
	}
	
	@Test
	public void testGenerateHashPassword() throws Exception {
		String hash = PasswordUtils.generateBCrypt(PASSWORD);
		
		//assertTrue(bCryptPasswordEncoder.matches(PASSWORD, hash));
	}
}