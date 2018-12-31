package br.com.maddytec.security.services;

import java.util.Optional;

import br.com.maddytec.security.entities.User;

public interface UserService {

	/**
	 * Busca e retorna um usuário dado um email.
	 * 
	 * @param email
	 * @return Optional<Usuario>
	 */
	Optional<User> buscarPorEmail(String email);

}
