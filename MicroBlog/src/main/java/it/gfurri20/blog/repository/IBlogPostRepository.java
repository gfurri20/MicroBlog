
package it.gfurri20.blog.repository;

import it.gfurri20.blog.domain.BlogPost;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author gfurri20
 */
public interface IBlogPostRepository extends CrudRepository<BlogPost, Long>
{
    
}
