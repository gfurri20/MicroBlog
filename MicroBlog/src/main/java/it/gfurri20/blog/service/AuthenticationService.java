
package it.gfurri20.blog.service;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import it.gfurri20.blog.security.UserPrincipal;
import it.gfurri20.blog.security.jwt.JwtProperties;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


/**
 *
 * @author gfurri20
 */
@Service
public class AuthenticationService implements IAuthenticationService
{
    @Override
    public void successfulLogin( HttpServletRequest request, HttpServletResponse response, Authentication authentication )
    {
        // Grab principal
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));

        // Add token in response
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
    }
    
}
