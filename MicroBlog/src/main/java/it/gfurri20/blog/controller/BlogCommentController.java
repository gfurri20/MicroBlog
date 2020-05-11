
package it.gfurri20.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ResponseHeader;
import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.service.IBlogCommentService;
import java.net.URI;
import java.net.URISyntaxException;
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
@Api(tags = {"Comment"})
@RestController
@RequestMapping("api/comments")
public class BlogCommentController
{   
    @Autowired
    IBlogCommentService blogCommentService;
    
    /**
     * Returns all saved comments
     * 
     * @return http 200 with the list of comments, if there are any comments return an empty list
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(
        value = "Returns all saved comments",
        httpMethod = "GET"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Comments founded"),
    })
    public ResponseEntity<List<BlogComment>> getComments()
    {
        return new ResponseEntity<>(blogCommentService.getComments(), HttpStatus.OK);
    }
    
    /**
     * Returns a comment by its id
     * 
     * @param id of the comment
     * @return http 200 with the searched comment, 404 if the comment does not exist
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(
        value = "Returns a comment by its id",
        httpMethod = "GET"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Comment founded"),
        @ApiResponse(code = 404, message = "Comment not found")
    })
    public ResponseEntity getCommentById(@PathVariable("id") Long id)
    {
        if( blogCommentService.getCommentById(id) != null )
        {
            return new ResponseEntity<>(blogCommentService.getCommentById(id), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Creates a new comment
     * 
     * @param comment new istance
     * @return http status code 201 if it is successful, 403 if the request is not authorized, 422 if the input istance is wrong
     * @throws URISyntaxException 
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(
        value = "Creates a new comment",
        httpMethod = "POST",
        consumes = "application/json",
        authorizations = {@Authorization(value = "USER")}
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Comment successfully created", responseHeaders = {@ResponseHeader(name = "Location", response = URI.class, description = "Resource's location")}),
        @ApiResponse(code = 403, message = "Forbidden, not authorized"),
        @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    public ResponseEntity createComment(@RequestBody BlogComment comment) throws URISyntaxException
    {
        if( comment == null )
        {
            return ResponseEntity.unprocessableEntity().build();
        }
        else
        {
            blogCommentService.createComment(comment);
            return ResponseEntity.created(new URI("http://localhost:8081/microblog/v2/api/comments/" + comment.getId())).build();
        }
    }
    
    /**
     * Updates a comment
     * 
     * @param id of the comment to update
     * @param comment updated istance
     * @return http status 204 if it is successful, 404 if the comment to update does not exist, 409 if there are erros
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ApiOperation(
        value = "Updates a comment",
        httpMethod = "PUT"
    )
    @ApiResponses({
        @ApiResponse(code = 204, message = "Comment updated"),
        @ApiResponse(code = 404, message = "Comment not found"),
        @ApiResponse(code = 409, message = "Conflict")
    })
    public ResponseEntity updateComment(@PathVariable("id") Long id, @RequestBody BlogComment comment)
    {
        if( blogCommentService.getCommentById(id) == null )
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else if( blogCommentService.getCommentById(id) != null )
        {
            blogCommentService.updateComment(id, comment);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    
    /**
     * Deletes a comment
     * 
     * @param id of the comment to delete
     * @return http status 204 if it is successful, 404 if the comment to update does not exist, 409 if there are erros
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(
        value = "Deletes a comment",
        httpMethod = "DELETE"
    )
    @ApiResponses({
        @ApiResponse(code = 204, message = "Comment deleted"),
        @ApiResponse(code = 404, message = "Comment not found"),
        @ApiResponse(code = 409, message = "Conflict")
    })
    public ResponseEntity deleteComment(@PathVariable("id") Long id)
    {
        if( blogCommentService.getCommentById(id) == null )
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else if( blogCommentService.getCommentById(id) != null )
        {
            blogCommentService.destroyComment(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    
}
