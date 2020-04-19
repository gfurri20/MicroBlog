
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogUser;
import it.gfurri20.blog.repository.BlogUserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author gfurri20
 */
@Service
public class BlogUserService implements IBlogUserService
{

    @Autowired
    BlogUserRepository blogUserRepository;

    @Override
    public void createUser( BlogUser user )
    {
        blogUserRepository.save(user);
    }

    @Override
    public void destroyUser( Long id )
    {
        blogUserRepository.delete(this.getUserById(id));
    }

    @Override
    public BlogUser getUserById( Long id )
    {
        try
        {
            return blogUserRepository.findById(id).orElseThrow();
        }
        catch( NoSuchElementException e )
        {
            return null;
        }
    }

    @Override
    public BlogUser getUserByUsername( String username )
    {
        try
        {
            return blogUserRepository.findByUsername(username).orElseThrow();
        }
        catch( NoSuchElementException e )
        {
            return null;
        }
    }

    @Override
    public List<BlogUser> getUsers()
    {
        return (List<BlogUser>) blogUserRepository.findAll();
    }

    @Override
    public void updateUser( Long id, BlogUser user )
    {
        BlogUser oldUser = this.getUserById(id);
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());

        blogUserRepository.save(oldUser);
    }
    
    

}
