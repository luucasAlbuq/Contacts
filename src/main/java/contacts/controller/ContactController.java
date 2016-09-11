package contacts.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import contacts.model.Contact;
import contacts.service.ContactService;

import java.util.List;
import java.util.Locale;
 
@Controller
@RequestMapping(value = "/protected/contacts")
public class ContactController {
 
    private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";
 
    @Autowired
    private ContactService contactService;
    
    /**
     * Procura por valores que podem ser encontrados no arquico i18n_en.properties
     */
    @Autowired
    private MessageSource messageSource;
 
    @Value("5")
    private int maxResults;
 
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("contactsList");
    }
 
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
    	List<Contact> contacts = contactService.findAll();
    	if(contacts.isEmpty()){
    		return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }
 
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("contact") Contact contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        
    	//TODO implementar metodo que verifique se o obecto que ja existe
    	
    	Contact storedContact = contactService.save(contact);
    	return new ResponseEntity<Contact>(storedContact, HttpStatus.OK);
 
    }
 
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") int contactId,
                                    @RequestBody Contact contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        
    	if (contactId != contact.getId()) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }
 
        Contact updatedContacr = contactService.save(contact);
        return new ResponseEntity<Contact>(updatedContacr, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/{contactId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("contactId") int contactId,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
 
        try {
            contactService.delete(contactId);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
        }
 
        return new ResponseEntity<Contact>(HttpStatus.OK);
    }
 
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> search(@PathVariable("name") String name,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        
    	List<Contact> contacts = contactService.findByName(name);
        
        if(contacts.isEmpty()){
        	return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }
 
//    private ResponseEntity<?> search(String name, int page, Locale locale, String actionMessageKey) {
//        ContactListVO contactListVO = contactService.findByNameLike(page, maxResults, name);
// 
//        if (!StringUtils.isEmpty(actionMessageKey)) {
//            addActionMessageToVO(contactListVO, locale, actionMessageKey, null);
//        }
// 
//        Object[] args = {name};
// 
//        addSearchMessageToVO(contactListVO, locale, "message.search.for.active", args);
// 
//        return new ResponseEntity<ContactListVO>(contactListVO, HttpStatus.OK);
//    }
 
}