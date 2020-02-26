
package it.gfurri20.blog.repository;

import it.gfurri20.blog.domain.BlogComment;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author gfurri20
 */
public interface IBlogCommentRepository extends CrudRepository<BlogComment, Long>
{
    
}
