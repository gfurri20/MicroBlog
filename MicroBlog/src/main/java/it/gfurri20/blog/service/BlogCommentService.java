
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.repository.IBlogCommentRepository;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void createComment( BlogComment comment )
    {
        blogCommentRepository.save(comment);
    }
    
    @Override
    public void updateComment( BlogComment comment )
    {
        blogCommentRepository.save(comment);
    }

    @Override
    public void destroyComment( Long id )
    {
        blogCommentRepository.delete(getSingleComment(id));
    }

    
    @Override
    public BlogComment getSingleComment( Long id )
    {
        return blogCommentRepository.findById(id).get();
    }
    
    @Override
    public List<BlogComment> getAllComment()
    {
        List<BlogComment> list = new ArrayList<>();
        blogCommentRepository.findAll().forEach(comment -> list.add(comment));
        return list;
    }
    
    @Override
    public List<BlogComment> getCommentsByPost( Long id )
    {
        List<BlogComment> list = new ArrayList<>();
        blogCommentRepository.findAllByCorrelatedPostId(id).forEach(comment -> list.add(comment));
        return list;
    }

}
