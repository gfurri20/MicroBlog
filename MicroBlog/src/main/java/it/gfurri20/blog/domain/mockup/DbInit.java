
package it.gfurri20.blog.domain.mockup;

import it.gfurri20.blog.domain.BlogUser;
import it.gfurri20.blog.repository.IBlogUserRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 *
 * @author gfurri20
 */
@Service
public class DbInit implements CommandLineRunner
{
    private IBlogUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(IBlogUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        BlogUser pippo = new BlogUser("pippo",passwordEncoder.encode("pippo"),"USER","");
        BlogUser admin = new BlogUser("admin",passwordEncoder.encode("admin"),"ADMIN","ACCESS_USERS");

        List<BlogUser> users = Arrays.asList(pippo,admin);

        // Save to db
        this.userRepository.saveAll(users);
    }
}
