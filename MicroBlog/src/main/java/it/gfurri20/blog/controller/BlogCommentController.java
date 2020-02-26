
package it.gfurri20.blog.controller;

import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.service.BlogCommentService;
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
@RequestMapping("comment")
public class BlogCommentController
{
    
    @Autowired
    BlogCommentService blogCommentService;
    
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<List<BlogComment>> getAllComments()
    {
        return new ResponseEntity<>(blogCommentService.getAllComment(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "single/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogComment> getSingleComment(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(blogCommentService.getSingleComment(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ResponseEntity<String> createComment(@RequestBody BlogComment comment)
    {
        blogCommentService.createComment(comment);
        return new ResponseEntity<>("Comment created successfully", HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateComment(@RequestBody BlogComment comment)
    {
        blogCommentService.updateComment(comment);
        return new ResponseEntity<>("Comment updated successfully", HttpStatus.OK);
    }
    
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id)
    {
        blogCommentService.destroyComment(id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
    
}
