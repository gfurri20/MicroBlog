
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogUser;
import java.util.List;


/**
 *
 * @author gfurri20
 */
public interface IBlogUserService
{
    /**
     * Create a new <code>BlogUser</code>
     * 
     * @param user new istance
     */
    public void createUser(BlogUser user);
    /**
     * Update a specific <code>BlogUser</code>
     * 
     * @param id of the user to be update
     * @param user updated
     */
    public void updateUser(Long id, BlogUser user);
    /**
     * Delete a specific <code>BlogUser</code>
     * 
     * @param id of the user to be deleted
     */
    public void destroyUser(Long id);
    /**
     * Search for a specific <code>BlogUser</code>
     * 
     * @param id
     * @return the searched user, if exists
     */
    public BlogUser getSingleUser(Long id);
    /**
     * Search for all users
     * 
     * @return all users which exist
     */
    public List<BlogUser> getAllUsers();
}
