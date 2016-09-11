package contacts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * This controller will be invoked when a user types the URL contacts.com/. 
 * The user will be redirect to a secure area that the SpringSecurity is protecting, 
 * and the user will be asked to do the login. 
 * The redirect action happens because of the text “redirect” in the returned string.
 * {@linkplain http://uaihebert.com/complete-web-application-angular-twitter-bootstrap-spring-mvc-data-and-security/10/}
 */
@Controller
@RequestMapping(value="/")
public class HomeController {
	
	@RequestMapping(method={RequestMethod.GET,RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE })
	public String redirect(){
		return "redirect:/protected/home";
	}
}
