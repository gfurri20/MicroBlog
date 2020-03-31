
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.domain.BlogPost;
import it.gfurri20.blog.repository.IBlogCommentRepository;
import it.gfurri20.blog.repository.IBlogPostRepository;
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
public class BlogPostService implements IBlogPostService
{
    @Autowired
    IBlogUserService blogUserService;
    
    @Autowired
    IBlogPostRepository blogPostRepository;
    
    @Autowired
    IBlogCommentRepository blogCommentRepository;

    @Override
    public void createPostByUsername( String username, BlogPost post )
    {
        post.setPubblicationDate(new Date());
        post.setAuthor(blogUserService.getUserByUsername(username));
        blogPostRepository.save(post);
    }
    
    @Override
    public void updatePost( Long id, BlogPost post )
    {
        BlogPost oldPost = blogPostRepository.findById(id).get();
        oldPost.setAuthor(post.getAuthor());
        oldPost.setContent(post.getContent());
        oldPost.setPubblicationDate(post.getPubblicationDate());
        oldPost.setTitle(post.getTitle());

        blogPostRepository.save(oldPost);
    }

    @Override
    public void destroyPost( Long id )
    {
        blogPostRepository.delete(getPostById(id));
    }


    @Override
    public BlogPost getPostById( Long id )
    {
        try
        {
            return blogPostRepository.findById(id).orElseThrow();
        }
        catch( NoSuchElementException e )
        {
            return null;
        }
    }

    @Override
    public List<BlogPost> getPosts()
    {
        return (List<BlogPost>) blogPostRepository.findAll();
    }
    
    @Override
    public List<BlogComment> getCommentsByPost( Long id )
    {
        return (List<BlogComment>) blogCommentRepository.findByCorrelatedPost(getPostById(id));
    }
}
