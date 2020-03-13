
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
@RequestMapping("comments")
public class BlogCommentController
{
    
    @Autowired
    BlogCommentService blogCommentService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BlogComment>> getAllComments()
    {
        return new ResponseEntity<>(blogCommentService.getAllComment(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogComment> getSingleComment(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(blogCommentService.getSingleComment(id), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createComment(@RequestBody BlogComment comment)
    {
        blogCommentService.createComment(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity updateComment(@PathVariable("id") Long id, @RequestBody BlogComment comment)
    {
        blogCommentService.updateComment(id, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable("id") Long id)
    {
        blogCommentService.destroyComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
