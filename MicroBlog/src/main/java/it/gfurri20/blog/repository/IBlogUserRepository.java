
package it.gfurri20.blog.repository;

import it.gfurri20.blog.domain.BlogUser;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author gfurri20
 */
public interface IBlogUserRepository extends CrudRepository<BlogUser, Long>
{
    
}
