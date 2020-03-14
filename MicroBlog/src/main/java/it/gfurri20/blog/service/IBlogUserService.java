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
     * @param id of the user to be updated
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
     * Search for a specific <code>BlogUser</code> by its id
     * 
     * @param id
     * @return the searched user, if it not exists return <code>null</code>
     */
    public BlogUser getUserById(Long id);
    /**
     * Search for all users
     * 
     * @return all users which exist
     */
    public List<BlogUser> getUsers();
    /**
     * Search for a specific <code>BlogUser</code> by its username
     *
     * @param username
     * @return the searched user, if it not exists return <code>null</code>
     */
    public BlogUser getUserByUsername(String username);
}
