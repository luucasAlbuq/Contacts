package contacts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import contacts.model.Contact;
import contacts.repository.ContactRepository;

/**
 * Camada de servico para um {@link Contact}
 * 
 * @author Lucas
 */
@Service
@Transactional
public class ContactService {

	//TODO remover quando implementacao do repository estiver pronta		

	@Autowired
	ContactRepository contactRepository;
	
	List<Contact> mock = getMock();
	
	@Transactional(readOnly = true)
	public List<Contact> findByName(String name){

//		Sort sortByName = new Sort(Sort.Direction.ASC,"name");
//		final PageRequest pageResquest = new PageRequest(5,10,sortByName);
//		return contactRepository.findByNameLike(pageResquest, "%"+name+"%").getContent();
		
		return mock;
	}
	
	@Transactional
	public List<Contact> findAll(int numeroPaginas, int numeroMaxContatosPorPagina){
//		final PageRequest pageRequest = new PageRequest(numeroPaginas, numeroMaxContatosPorPagina);
//		return contactRepository.findAll(pageRequest).getContent();
		return mock;
	}
	
	@Transactional
	public List<Contact> findAll(){
//		 Iterable<Contact> iterable = contactRepository.findAll();
//		 List<Contact> contacts = new ArrayList<Contact>();
//		 iterable.forEach((contact)->contacts.add(contact));
//		 return contacts;
		 return mock;
	}
	
	@Transactional
	public Contact findOne(Integer contactId){
		//return contactRepository.findOne(contactId);
		return mock.get(0);
	}
	
	@Transactional
	@Secured("ROLE_ADMIN")
	public void delete(Integer contactId){
		//contactRepository.delete(contactId);
		mock.remove(0);
	}
	
	/**
	 * Cria um novo {@link Contact}, caso já exista atualiza.
	 * @param contact
	 * @return {@link Contact} criado ou atualizado
	 */
	@Transactional
	public Contact save(Contact contact){
		//return contactRepository.save(contact);
		mock.add(contact);
		return mock.get(mock.indexOf(contact));
	}
	
	private List<Contact> getMock(){
		List<Contact> contacts = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			contacts.add(creatMock());
		}
		return contacts;
	}
	
	private Contact creatMock() {
		return new Contact(RandomStringUtils.randomAlphabetic(5), 
				RandomStringUtils.randomAlphanumeric(10) ,
				RandomStringUtils.randomAlphanumeric(5)+"@email.com");
	}
	
}
