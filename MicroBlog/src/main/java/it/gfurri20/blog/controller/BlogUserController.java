/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gfurri20.blog.controller;

import it.gfurri20.blog.domain.BlogUser;
import it.gfurri20.blog.service.BlogUserService;
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
@Controller
@RequestMapping("user")
public class BlogUserController
{
    
    @Autowired
    BlogUserService blogUserService;
    
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<List<BlogUser>> getAllUsers()
    {
        return new ResponseEntity<>(blogUserService.getAllUsers(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "single/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogUser> getSingleUser(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(blogUserService.getSingleUser(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody BlogUser user)
    {
        blogUserService.createUser(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody BlogUser user)
    {
        blogUserService.updateUser(user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }
    
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id)
    {
        blogUserService.destroyUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
    
}
