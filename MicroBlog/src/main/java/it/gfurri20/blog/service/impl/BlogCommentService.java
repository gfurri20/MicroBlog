
package it.gfurri20.blog.service.impl;

import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.repository.IBlogCommentRepository;
import it.gfurri20.blog.service.IBlogCommentService;
import it.gfurri20.blog.service.IBlogPostService;
import it.gfurri20.blog.service.IBlogUserService;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author gfurri20
 */
@Service
public class BlogCommentService implements IBlogCommentService
{
    @Autowired
    IBlogUserService blogUserService;
    
    @Autowired
    IBlogCommentRepository blogCommentRepository;
    
    @Autowired
    IBlogPostService  blogPostService;

    @Override
    public void createComment( BlogComment comment )
    {
        comment.setPubblicationDate(new Date());
        comment.setAuthor(blogUserService.getUserByUsername(comment.getAuthor().getUsername()));
        blogCommentRepository.save(comment);
    }
    
    @Override
    public void updateComment( Long id, BlogComment comment )
    {
        BlogComment oldComment = blogCommentRepository.findById(id).get();
        oldComment.setAuthor(comment.getAuthor());
        oldComment.setContent(comment.getContent());
        oldComment.setCorrelatedPost(comment.getCorrelatedPost());
        oldComment.setPubblicationDate(comment.getPubblicationDate());
        
        blogCommentRepository.save(oldComment);
    }

    @Override
    public void destroyComment( Long id )
    {
        blogCommentRepository.delete(getCommentById(id));
    }

    
    @Override
    public BlogComment getCommentById( Long id )
    {
        try
        {
            return blogCommentRepository.findById(id).orElseThrow();
        }
        catch( NoSuchElementException e )
        {
            return null;
        }
    }
    
    @Override
    public List<BlogComment> getComments()
    {
        return (List<BlogComment>) blogCommentRepository.findAll();
    }

}
