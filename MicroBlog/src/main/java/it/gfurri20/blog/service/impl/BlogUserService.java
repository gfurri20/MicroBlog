
package it.gfurri20.blog.service.impl;

import it.gfurri20.blog.domain.BlogUser;
import it.gfurri20.blog.domain.auth.RegistrationModelView;
import it.gfurri20.blog.repository.IBlogUserRepository;
import it.gfurri20.blog.service.IBlogUserService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 *
 * @author gfurri20
 */
@Service
public class BlogUserService implements IBlogUserService
{

    @Autowired
    IBlogUserRepository blogUserRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public BlogUser registerBasicUser( RegistrationModelView credentials )
    {
        //check if there's not another user with this username and if the passwords are the same
        if(this.getUserByUsername(credentials.getUsername()) == null && credentials.getPassword().equals(credentials.getRepeatPassword()))
        {
            //save the user
            return blogUserRepository.save(new BlogUser
                    (
                        credentials.getUsername(),
                        passwordEncoder.encode(credentials.getPassword()),
                        "USER",
                        ""
                    )
            );
        }
        else
        {
            return null;
        }
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
