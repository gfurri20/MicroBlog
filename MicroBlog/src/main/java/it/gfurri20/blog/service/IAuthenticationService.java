
package it.gfurri20.blog.service;

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
}
