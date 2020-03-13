
package it.gfurri20.blog.controller;

import it.gfurri20.blog.domain.BlogPost;
import it.gfurri20.blog.service.BlogPostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author gfurri20
 */
@Controller
@RequestMapping("posts")
public class BlogPostController
{
    @Autowired
    BlogPostService blogPostService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BlogPost>> getAllPosts()
    {
        return new ResponseEntity<>(blogPostService.getAllPosts(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogPost> getSinglePost(@PathVariable("id") Long id, Model model)
    {
        return new ResponseEntity<>(blogPostService.getSinglePost(id), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createPost(@RequestBody BlogPost post)
    {
        blogPostService.createPost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity updatePost(@PathVariable("id") Long id, @RequestBody BlogPost post)
    {
        blogPostService.updatePost(id, post);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable("id") Long id)
    {
        blogPostService.destroyPost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
