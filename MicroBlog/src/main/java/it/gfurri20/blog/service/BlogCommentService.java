
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.repository.IBlogCommentRepository;
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
    IBlogCommentRepository blogCommentRepository;
    
    @Autowired
    IBlogPostService  blogPostService;

    @Override
    public void createComment( BlogComment comment )
    {
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
    
    @Override
    public List<BlogComment> getCommentsByPost( Long id )
    {
        return (List<BlogComment>) blogCommentRepository.findByCorrelatedPost(blogPostService.getPostById(id));
    }

}
