package contacts.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    
    public List<User> findAll(){
    	Iterable<User> iterable = userRepository.findAll();
    	List<User> users = new ArrayList<>();
    	iterable.forEach((user) -> users.add(user));
    	return users;
    }
    
    public User findOne(Integer userId){
    	return userRepository.findOne(userId);
    }
    
    public User save(User user){
    	return userRepository.save(user);
    }
    
    @Secured("ROLE_ADMIN")
    public void delete(User user){
    	userRepository.delete(user);
    }
    
    @Secured("ROLE_ADMIN")
    public void delete(Integer userId){
    	userRepository.delete(userId);
    }
}
