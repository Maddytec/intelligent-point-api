package br.com.maddytec.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maddytec.security.entities.User;
import br.com.maddytec.security.repositories.UserRepository;
import br.com.maddytec.security.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> buscarPorEmail(String email) {
		return Optional.ofNullable(this.userRepository.findByEmail(email));
	}
}
