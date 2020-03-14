
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogPost;
import it.gfurri20.blog.repository.IBlogPostRepository;
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
    IBlogPostRepository blogPostRepository;

    @Override
    public void createPost( BlogPost post )
    {
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
    
}
