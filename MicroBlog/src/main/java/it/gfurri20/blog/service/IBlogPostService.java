
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogPost;
import java.util.List;


/**
 *
 * @author gfurri20
 */
public interface IBlogPostService
{
    /**
     * Create a new <code>BlogPost</code>
     * 
     * @param post new istance
     */
    public void createPost(BlogPost post);
    /**
     * Update a specific <code>BlogPost</code>
     * 
     * @param post updated
     */
    public void updatePost(BlogPost post);
    /**
     * Delete a specific <code>BlogPost</code>
     * 
     * @param id of the post to be deleted
     */
    public void destroyPost(Long id);
    
    /**
     * Search for a specific <code>BlogPost</code>
     * 
     * @param id
     * @return the searched post, if exists
     */
    public BlogPost getSinglePost(Long id);
    /**
     * Search for all posts
     * 
     * @return all posts which exist
     */
    public List<BlogPost> getAllPosts();
}
