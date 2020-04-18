
package it.gfurri20.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.gfurri20.blog.domain.BlogPost;
import it.gfurri20.blog.service.IBlogPostService;
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
@Api(tags = {"Post"})
@RestController
@RequestMapping("posts")
public class BlogPostController
{
    @Autowired
    IBlogPostService blogPostService;
    
    @Autowired
    IBlogUserService blogUserService;
    
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Returns all saved posts")
    public ResponseEntity<List<BlogPost>> getPosts()
    {
        return new ResponseEntity<>(blogPostService.getPosts(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Returns a post by its id")
    public ResponseEntity<BlogPost> getPostById(@PathVariable("id") Long id)
    {
        if( blogPostService.getPostById(id) != null )
        {
            return new ResponseEntity<>(blogPostService.getPostById(id), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Creates a new post")
    public ResponseEntity createPostByUsername(@RequestBody BlogPost post)
    {
        blogPostService.createPost(post);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Updates a post")
    public ResponseEntity updatePost(@PathVariable("id") Long id, @RequestBody BlogPost post)
    {
        if( blogPostService.getPostById(id) == null )
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
        {
            blogPostService.updatePost(id, post);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Deletes a post")
    public ResponseEntity deletePost(@PathVariable("id") Long id)
    {
        if( blogPostService.getPostById(id) == null )
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else if( blogPostService.getPostById(id) != null )
        {
            blogPostService.destroyPost(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
    
    @RequestMapping(value = "{id}/comments", method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ApiOperation(value = "Returns all comments refer to a specific post")
    public ResponseEntity getCommentsByPost(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(blogPostService.getCommentsByPost(id), HttpStatus.OK);
    }
    
}
