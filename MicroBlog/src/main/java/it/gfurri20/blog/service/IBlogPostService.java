
package it.gfurri20.blog.service;

import it.gfurri20.blog.domain.BlogComment;
import it.gfurri20.blog.domain.BlogPost;
import java.util.List;


/**
 *
 * @author gfurri20
 */
public interface IBlogPostService
{
    /**
     * Create a new <code>BlogPost</code> by username
     * 
     * @param username of the author
     * @param post new istance
     */
    public void createPostByUsername( String username, BlogPost post);
    /**
     * Update a specific <code>BlogPost</code>
     * 
     * @param id of the post to be updated
     * @param post updated
     */
    public void updatePost(Long id, BlogPost post);
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
     * @return the searched post, if it not exists <code>null</code>
     */
    public BlogPost getPostById(Long id);
    /**
     * Search for all posts
     * 
     * @return all posts which exist
     */
    public List<BlogPost> getPosts();
    /**
     * Search for all <code>BlogComment</code>s which are correlated to a specific <code>BlogPost</code>
     * 
     * @param id of the specific post
     * @return comments correlated to the specified post, if exist
     */
    public List<BlogComment> getCommentsByPost(Long id);
}
