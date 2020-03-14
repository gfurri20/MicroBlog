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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author gfurri20
 */
@Api(tags = {"User"})
@Controller
@RequestMapping("users")
public class BlogUserController
{
    @Autowired
    IBlogUserService blogUserService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Returns all saved users")
    public ResponseEntity<List<BlogUser>> getUsers()
    {
        return new ResponseEntity<>(blogUserService.getUsers(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns a user by its id")
    public ResponseEntity<BlogUser> getUserById(@PathVariable Long id)
    {
        if( blogUserService.getUserById(id) != null )
        {
            return new ResponseEntity<BlogUser>(blogUserService.getUserById(id), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Creates a new user")
    public ResponseEntity createUser(@RequestBody BlogUser user)
    {
        if( user == null )
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
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
    @ApiOperation(value = "Updates an user")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody BlogUser user)
    {
        if( blogUserService.getUserById(id) == null )
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else if( blogUserService.getUserById(id) != null )
        {
            blogUserService.updateUser(id, user);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Deletes an user")
    public ResponseEntity deleteUser(@PathVariable Long id)
    {
        if( blogUserService.getUserById(id) == null )
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else if( blogUserService.getUserById(id) != null )
        {
            blogUserService.destroyUser(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
