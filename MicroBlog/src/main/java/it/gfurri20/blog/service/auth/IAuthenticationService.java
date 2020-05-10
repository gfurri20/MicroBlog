
package it.gfurri20.blog.service.auth;

import it.gfurri20.blog.domain.auth.LoginViewModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;


/**
 *
 * @author gfurri20
 */
public interface IAuthenticationService
{
    /**
     * If the credentials are authenticated this method will create a JWT based
     * on the authentication principal and put it in the response header
     * 
     * @param request http
     * @param response http
     * @param authentication successfully executed 
     */
    void successfulLogin(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
    /**
     * If the user is successfully created proceed with the login
     * 
     * @param request http
     * @param response http
     * @param credentials to login
     * @return true if the user has been succesfully authenticated, false if not
     */
    boolean successfulRegistration(HttpServletRequest request, HttpServletResponse response, LoginViewModel credentials);
}
