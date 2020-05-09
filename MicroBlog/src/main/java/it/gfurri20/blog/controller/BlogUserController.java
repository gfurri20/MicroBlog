/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gfurri20.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.gfurri20.blog.domain.BlogUser;
import it.gfurri20.blog.service.IBlogUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author gfurri20
 */
@Api(tags = {"User"})
@RestController
@RequestMapping("users")
public class BlogUserController
{
    @Autowired
    IBlogUserService blogUserService;
    
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Returns all saved users")
    public ResponseEntity<List<BlogUser>> getUsers()
    {
        if( !(blogUserService.getUsers().isEmpty()) )
        {
            return new ResponseEntity<>(blogUserService.getUsers(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Returns a user by its id")
    public ResponseEntity<BlogUser> getUserById(@PathVariable Long id)
    {
        if( blogUserService.getUserById(id) != null )
        {
            return new ResponseEntity<>(blogUserService.getUserById(id), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Creates a new user")
    public ResponseEntity createUser(@RequestBody BlogUser user)
    {
        if( user == null )
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else if( blogUserService.getUserByUsername(user.getUsername()) == null )
        {
            blogUserService.createUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Updates an user")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody BlogUser user)
    {
        if( blogUserService.getUserById(id) == null )
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else if( blogUserService.getUserById(id) != null )
        {
            blogUserService.updateUser(id, user);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Deletes an user")
    public ResponseEntity deleteUser(@PathVariable Long id)
    {
        if( blogUserService.getUserById(id) == null )
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else if( blogUserService.getUserById(id) != null )
        {
            blogUserService.destroyUser(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
