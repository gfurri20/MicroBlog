
package it.gfurri20.blog.service.auth.impl;

import com.auth0.jwt.JWT;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import it.gfurri20.blog.domain.auth.LoginViewModel;
import it.gfurri20.blog.security.UserPrincipal;
import it.gfurri20.blog.security.jwt.JwtProperties;
import it.gfurri20.blog.service.auth.IAuthenticationService;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


/**
 *
 * @author gfurri20
 */
@Service
public class AuthenticationService implements IAuthenticationService
{
    @Autowired
    private AuthenticationManager authenticationManager;
    
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

    @Override
    public boolean successfulRegistration( HttpServletRequest request, HttpServletResponse response, LoginViewModel credentials )
    {
        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                new ArrayList<>());
        
        try
        {
            // Authenticate user
            Authentication auth = authenticationManager.authenticate(authenticationToken);
            
            //if pwd and username are correct create and put into response a JWT token
            successfulLogin(request, response, auth);
            return true;
        }
        catch( BadCredentialsException e )
        {
            //if pwd or username are wrong
            return false;
        }
    }
    
}
