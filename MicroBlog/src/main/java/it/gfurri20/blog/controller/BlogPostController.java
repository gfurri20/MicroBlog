
package it.gfurri20.blog.controller;

import it.gfurri20.blog.domain.BlogPost;
import it.gfurri20.blog.service.BlogPostService;
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
    public String getAllPosts(Model model)
    {
        model.addAttribute("posts", blogPostService.getAllPosts());
        return "view-posts";
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getSinglePost(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("post", blogPostService.getSinglePost(id));
        return "view-post";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createPost(@RequestBody BlogPost post)
    {
        blogPostService.createPost(post);
        return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePost(@PathVariable("id") Long id, @RequestBody BlogPost post)
    {
        blogPostService.updatePost(id, post);
        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id)
    {
        blogPostService.destroyPost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
    
}
