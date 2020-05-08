
package it.gfurri20.blog.security;

import it.gfurri20.blog.domain.BlogUser;
import it.gfurri20.blog.repository.IBlogUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 *
 * @author gfurri20
 */
@Service
public class UserPrincipalDetailsService implements UserDetailsService
{
    private IBlogUserRepository userRepository;

    public UserPrincipalDetailsService(IBlogUserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        BlogUser user = this.userRepository.findByUsername(username).orElseThrow();
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
