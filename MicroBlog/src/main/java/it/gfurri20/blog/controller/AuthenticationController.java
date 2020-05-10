/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gfurri20.blog.controller;

import it.gfurri20.blog.domain.LoginViewModel;
import it.gfurri20.blog.service.IAuthenticationService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author gfurri20
 */
@RestController
public class AuthenticationController
{
    @Autowired
    IAuthenticationService authenticationService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/login")
    public ResponseEntity login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginViewModel credentials)
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
            authenticationService.successfulLogin(request, response, auth);
            return ResponseEntity.ok().build();
        }
        catch( BadCredentialsException e )
        {
            //if pwd or username is wrong return an unauthorized http status
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
