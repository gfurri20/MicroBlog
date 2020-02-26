
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogComment;
import java.util.List;


/**
 *
 * @author gfurri20
 */
public interface IBlogCommentService
{
    /**
     * Create a new <code>BlogComment</code>
     * 
     * @param comment new istance
     */
    public void createComment(BlogComment comment);
    /**
     * Update a specific <code>BlogComment</code>
     * 
     * @param comment updated
     */
    public void updateComment(BlogComment comment);
    /**
     * Delete a specific <code>BlogComment</code>
     * 
     * @param id of the comment to be deleted
     */
    public void destroyComment(Long id);
    
    /**
     * Search for a specific <code>BlogComment</code>
     * 
     * @param id
     * @return the searched comment, if exists
     */
    public BlogComment getSingleComment(Long id);
    /**
     * Search for all comments
     * 
     * @return all comments which exist
     */
    public List<BlogComment> getAllComment();
    /**
     * Search for all <code>BlogComment</code>s which are correlated to a specific <code>BlogPost</code>
     * 
     * @param id of the specific post
     * @return comments correlated to the specified post, if exist
     */
    public List<BlogComment> getCommentsByPost(Long id);
}
