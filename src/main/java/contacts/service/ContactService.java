package contacts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

	@Autowired
	ContactRepository contactRepository;
	
	@Transactional
	public List<Contact> findAll(int numeroPaginas, int numeroMaxContatosPorPagina){
		final PageRequest pageRequest = new PageRequest(numeroPaginas, numeroMaxContatosPorPagina);
		return contactRepository.findAll(pageRequest).getContent();
	}
	
	@Transactional
	public List<Contact> findAll(){
		 Iterable<Contact> iterable = contactRepository.findAll();
		 List<Contact> contacts = new ArrayList<Contact>();
		 iterable.forEach((contact)->contacts.add(contact));
		 return contacts;
	}
	
	@Transactional
	public Contact findOne(Integer contactId){
		return contactRepository.findOne(contactId);
	}
	
	@Transactional
	@Secured("ROLE_ADMIN")
	public void delete(Integer contactId){
		contactRepository.delete(contactId);
	}
	
	/**
	 * Cria um novo {@link Contact}, caso já exista atualiza.
	 * @param contact
	 * @return {@link Contact} criado ou atualizado
	 */
	@Transactional
	public Contact save(Contact contact){
		return contactRepository.save(contact);
	}
}
