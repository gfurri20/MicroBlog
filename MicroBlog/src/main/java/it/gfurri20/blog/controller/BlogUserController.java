/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gfurri20.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.gfurri20.blog.domain.BlogUser;
import it.gfurri20.blog.service.IBlogUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/users")
public class BlogUserController
{
    @Autowired
    IBlogUserService blogUserService;
    
    /**
     * Returns all saved users
     * 
     * @return http 200 with the list of users, if there are any users return an empty list
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(
        value = "Returns all saved users",
        httpMethod = "GET"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Users founded"),
    })
    public ResponseEntity<List<BlogUser>> getUsers()
    {
        return new ResponseEntity<>(blogUserService.getUsers(), HttpStatus.OK);
    }
    
    /**
     * Returns a user by its id
     * 
     * @param id of the user
     * @return http 200 with the searched user, 404 if the user does not exist
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(
        value = "Returns a user by its id",
        httpMethod = "GET"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "User founded"),
        @ApiResponse(code = 404, message = "User not found")
    })
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
    
    /**
     * Updates a user
     * 
     * @param id of the user to update
     * @param user updated istance
     * @return http status 204 if it is successful, 404 if the user to update does not exist, 409 if there are erros
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ApiOperation(
        value = "Updates a user",
        httpMethod = "PUT"
    )
    @ApiResponses({
        @ApiResponse(code = 204, message = "User updated"),
        @ApiResponse(code = 404, message = "User not found"),
        @ApiResponse(code = 409, message = "Conflict")
    })
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
    
    /**
     * Deletes a user
     * 
     * @param id of the user to delete
     * @return http status 204 if it is successful, 404 if the user to update does not exist, 409 if there are erros
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(
        value = "Deletes a user",
        httpMethod = "DELETE"
    )
    @ApiResponses({
        @ApiResponse(code = 204, message = "User deleted"),
        @ApiResponse(code = 404, message = "User not found"),
        @ApiResponse(code = 409, message = "Conflict")
    })
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
