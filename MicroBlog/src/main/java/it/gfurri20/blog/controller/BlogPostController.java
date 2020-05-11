
package it.gfurri20.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ResponseHeader;
import it.gfurri20.blog.domain.BlogPost;
import it.gfurri20.blog.service.IBlogPostService;
import it.gfurri20.blog.service.IBlogUserService;
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
@Api(tags = {"Post"})
@RestController
@RequestMapping("api/posts")
public class BlogPostController
{
    @Autowired
    IBlogPostService blogPostService;
    
    @Autowired
    IBlogUserService blogUserService;
    
    /**
     * Returns all saved posts
     * 
     * @return http 200 with the list of posts, if there are any posts return an empty list
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(
        value = "Returns all saved posts",
        httpMethod = "GET"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Posts founded"),
    })
    public ResponseEntity<List<BlogPost>> getPosts()
    {
        return new ResponseEntity<>(blogPostService.getPosts(), HttpStatus.OK);
    }
    
    /**
     * Returns a post by its id
     * 
     * @param id of the post
     * @return http 200 with the searched post, 404 if the post does not exist
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(
        value = "Returns a post by its id",
        httpMethod = "GET"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Post founded"),
        @ApiResponse(code = 404, message = "Post not found")
    })
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
    
    /**
     * Creates a new post
     * 
     * @param post new istance
     * @return http status code 201 if it is successful, 403 if the request is not authorized, 422 if the input istance is wrong
     * @throws URISyntaxException 
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(
        value = "Creates a new post",
        httpMethod = "POST",
        consumes = "application/json",
        authorizations = {@Authorization(value = "ADMIN")}
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Post successfully created", responseHeaders = {@ResponseHeader(name = "Location", response = URI.class, description = "Resource's location")}),
        @ApiResponse(code = 403, message = "Forbidden, not authorized"),
        @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    public ResponseEntity createPost(@RequestBody BlogPost post) throws URISyntaxException
    {
        if( post == null )
        {
            return ResponseEntity.badRequest().build(); //replace with 422
        } else
        {
            blogPostService.createPost(post);
            return ResponseEntity.created(new URI("http://localhost:8081/microblog/v2/api/posts/" + post.getId())).build();
        }
    }
    
    /**
     * Updates a post
     * 
     * @param id of the post to update
     * @param post updated istance
     * @return http status 204 if it is successful, 404 if the post to update does not exist, 409 if there are erros
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ApiOperation(
        value = "Updates a post",
        httpMethod = "PUT"
    )
    @ApiResponses({
        @ApiResponse(code = 204, message = "Post updated"),
        @ApiResponse(code = 404, message = "Post not found"),
        @ApiResponse(code = 409, message = "Conflict")
    })
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
    
    /**
     * Deletes a post
     * 
     * @param id of the post to delete
     * @return http status 204 if it is successful, 404 if the post to update does not exist, 409 if there are erros
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(
        value = "Deletes a post",
        httpMethod = "DELETE"
    )
    @ApiResponses({
        @ApiResponse(code = 204, message = "Post deleted"),
        @ApiResponse(code = 404, message = "Post not found"),
        @ApiResponse(code = 409, message = "Conflict")
    })
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
    
    /**
     * Returns all comments refer to a specific post
     * 
     * @param id of the post
     * @return http 200 with the list of comments, if there are any comments return an empty list
     * 
     */
    @RequestMapping(value = "{id}/comments", method = RequestMethod.GET)
    @ApiOperation(
        value = "Returns all comments refer to a specific post",
        httpMethod = "GET"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "Comments founded"),
    })
    public ResponseEntity getCommentsByPost(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(blogPostService.getCommentsByPost(id), HttpStatus.OK);
    }
    
}
