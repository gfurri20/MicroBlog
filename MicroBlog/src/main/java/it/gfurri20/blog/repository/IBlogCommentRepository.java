
package it.gfurri20.blog.repository;

import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.domain.BlogPost;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author gfurri20
 */
public interface IBlogCommentRepository extends CrudRepository<BlogComment, Long>
{
    /**
     * Search for all <code>BlogComment</code>s which are correlated to a specific <code>BlogPost</code>
     * 
     * @param post
     * @return comments correlated to the specified post, if exist
     */
    public Iterable<BlogComment> findByCorrelatedPost(BlogPost post);
}
