
package it.gfurri20.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.service.IBlogCommentService;
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
@RequestMapping("comments")
public class BlogCommentController
{
    
    @Autowired
    IBlogCommentService blogCommentService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Returns all saved comments")
    public ResponseEntity<List<BlogComment>> getComments()
    {
        return new ResponseEntity<>(blogCommentService.getComments(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns a comment by its id")
    public ResponseEntity getCommentById(@PathVariable("id") Long id)
    {
        if( blogCommentService.getCommentById(id) != null )
        {
            return new ResponseEntity<BlogComment>(blogCommentService.getCommentById(id), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Creates a new comment")
    public ResponseEntity createComment(@RequestBody BlogComment comment)
    {
        if( comment == null )
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else
        {
            blogCommentService.createComment(comment);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Updates a comment")
    public ResponseEntity updateComment(@PathVariable("id") Long id, @RequestBody BlogComment comment)
    {
        if( blogCommentService.getCommentById(id) == null )
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else if( blogCommentService.getCommentById(id) != null )
        {
            blogCommentService.updateComment(id, comment);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Deletes a comment")
    public ResponseEntity deleteComment(@PathVariable("id") Long id)
    {
        if( blogCommentService.getCommentById(id) == null )
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else if( blogCommentService.getCommentById(id) != null )
        {
            blogCommentService.destroyComment(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    
    @RequestMapping(value = "/post/{postId}")
    @ApiOperation(value = "Returns all comments related to one post")
    public ResponseEntity<List<BlogComment>> getCommentsByPost(@PathVariable("postId") Long id)
    {
        return new ResponseEntity<>(blogCommentService.getCommentsByPost(id), HttpStatus.OK);
    }
    
}
