package contacts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contacts.model.User;
import contacts.repository.UserRepository;

/**
 * Camada de servico para um {@link User}
 * 
 * @author Lucas
 */
@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	/**
	 * Recupera um {@link User} a partir do email
	 * 
	 * @param email user@email.com
	 * @return usuario
	 */
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
