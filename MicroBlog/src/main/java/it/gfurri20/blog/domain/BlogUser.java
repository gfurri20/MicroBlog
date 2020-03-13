

package it.gfurri20.blog.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author gfurri20
 */
@Entity
@Data
@Table( name = "users" )
public class BlogUser implements Serializable
{
    public BlogUser( String username, String email)
    {
        this.username = username;
        this.email = email;
    }
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String username;
    
    @Basic
    private String email;
    
}
