
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogPost;
import it.gfurri20.blog.repository.IBlogPostRepository;
import java.util.ArrayList;
import java.util.List;
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
    public void updatePost( BlogPost post )
    {
        blogPostRepository.save(post);
    }

    @Override
    public void destroyPost( Long id )
    {
        blogPostRepository.delete(getSinglePost(id));
    }


    @Override
    public BlogPost getSinglePost( Long id )
    {
        return blogPostRepository.findById(id).get();
    }

    @Override
    public List<BlogPost> getAllPosts()
    {
        List<BlogPost> list = new ArrayList<>();
        blogPostRepository.findAll().forEach(post -> list.add(post));
        return list;
    }
    
}
