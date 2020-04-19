
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
     * @param id of the comment to be updated
     * @param comment updated
     */
    public void updateComment(Long id, BlogComment comment);
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
     * @return the searched comment, if it not exists <code>null</code>
     */
    public BlogComment getCommentById(Long id);
    /**
     * Search for all comments
     * 
     * @return all comments which exist
     */
    public List<BlogComment> getComments();
}
