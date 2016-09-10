package contacts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import contacts.model.Contact;

/**
 * Interface que prover as operacoes que podem ser executadas a um {@link Contact}
 * 
 * @author Lucas
 */
public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {
	
	/**
	 * Recupera contatos a partir do nome 
	 * 
	 * @param pageable {@link Pageable} new Pageable(numeroDePaginas, ElementosPorPagina)
	 * @param name		nome do contato
	 * @return contato
	 */
	Page<Contact> findByNameLike(Pageable pageable, String name);
	
}
