
package it.gfurri20.blog.domain.mockup;

import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.domain.BlogPost;
import it.gfurri20.blog.domain.BlogUser;
import it.gfurri20.blog.repository.IBlogCommentRepository;
import it.gfurri20.blog.repository.IBlogPostRepository;
import it.gfurri20.blog.repository.IBlogUserRepository;
import java.util.Arrays;
import java.util.Date;
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
    private IBlogPostRepository postRepository;
    private IBlogCommentRepository commentRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(IBlogUserRepository userRepository, IBlogPostRepository postRepository, IBlogCommentRepository commentRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        BlogUser pippo = new BlogUser("pippo",passwordEncoder.encode("pippo"),"USER","");
        BlogUser admin = new BlogUser("admin",passwordEncoder.encode("admin"),"ADMIN","");

        List<BlogUser> users = Arrays.asList(pippo,admin);
        
        //Create posts
        BlogPost post1 = new BlogPost("Lorem ipsum", "Lorem ipsum dolor sit amet exercitation laborum nisi culpa adipisicing et elit. Reprehenderit tempor dolor amet ut sit occaecat quis dolor excepteur quis deserunt exercitation excepteur amet. Cupidatat labore id cillum qui sunt ex esse fugiat. Consectetur reprehenderit non, dolore sit veniam voluptate officia ut sit mollit proident incididunt.", new Date(), admin);
        
        //Create comments
        BlogComment comment1 = new BlogComment("Che è sta roba!?", new Date(), pippo, post1);
        BlogComment comment2 = new BlogComment("Solo se sei un programmatore puoi capire :)", new Date(), admin, post1);

        // Save to db
        this.userRepository.saveAll(users);
        this.postRepository.save(post1);
        this.commentRepository.saveAll(Arrays.asList(comment1, comment2));
    }
}
