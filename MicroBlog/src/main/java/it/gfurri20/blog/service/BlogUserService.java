
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogUser;
import it.gfurri20.blog.repository.IBlogUserRepository;
import java.util.ArrayList;
import java.util.List;
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
    IBlogUserRepository blogUserRepository;
    
    @Override
    public void createUser( BlogUser user )
    {
        blogUserRepository.save(user);
    }
    
    @Override
    public void updateUser( BlogUser user )
    {
        blogUserRepository.save(user);
    }  

    @Override
    public void destroyUser( Long id )
    {
        blogUserRepository.delete(getSingleUser(id));
    }

    @Override
    public BlogUser getSingleUser( Long id )
    {
        return blogUserRepository.findById(id).get();
    }
    
    @Override
    public List<BlogUser> getAllUsers()
    {
        List<BlogUser> list = new ArrayList<>();
        blogUserRepository.findAll().forEach(user -> list.add(user));
        return list;
    }

}
