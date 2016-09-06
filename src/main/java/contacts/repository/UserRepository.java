package contacts.repository;

import org.springframework.data.repository.CrudRepository;

import contacts.model.User;

/**
 * Interface que prover as operacoes que podem ser executadas a um {@link User}
 * 
 * @author Lucas
 */
public interface UserRepository extends CrudRepository<User, Integer>{
	
	/**
	 * Recupera um {@link User} a partir do email
	 * 
	 * @param email user@email.com
	 * @return usuario
	 */
	User findByEmail(String email);
}
