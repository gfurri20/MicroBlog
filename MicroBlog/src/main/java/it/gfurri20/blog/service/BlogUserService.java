
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogUser;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.gfurri20.blog.repository.IBlogUserRepository;


/**
 *
 * @author gfurri20
 */
@Service
public class BlogUserService implements IBlogUserService
{

    @Autowired
    IBlogUserRepository blogUserRepository;

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
        //TODO
    }
    
    

}
