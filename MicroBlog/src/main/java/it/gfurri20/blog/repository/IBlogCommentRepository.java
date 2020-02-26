
package it.gfurri20.blog.repository;

import it.gfurri20.blog.domain.BlogComment;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author gfurri20
 */
public interface IBlogCommentRepository extends CrudRepository<BlogComment, Long>
{
    /**
     * Search for all <code>BlogComment</code>s which are correlated to a specific <code>BlogPost</code>
     * 
     * @param id of the specific post
     * @return comments correlated to the specified post, if exist
     */
    @Query("SELECT c FROM BlogComment c WHERE c.correlatedPost.id=:id")
    public List<BlogComment> findAllByCorrelatedPostId(@Param("id") Long id);
}
