

package it.gfurri20.blog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gfurri20
 */
@Entity
@Table( name = "users" )
public class BlogUser implements Serializable
{
    public BlogUser() {}
    
    public BlogUser( String username, String password, String roles, String permissions )
    {
        this.username = username;
    }
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Basic
    @Getter @Setter
    @Column(nullable = false)
    private String username;
    
    @Basic
    @Getter @Setter
    @Column(nullable = false)
    private String password;
    
    @Basic
    @Getter @Setter
    private int active;
    
    @Basic
    @Getter @Setter
    private String roles = "";
    
    @Basic
    @Getter @Setter
    private String permissions = "";
    
    public List<String> getRolesList() {
        if(this.roles.length() > 0)
            return Arrays.asList(this.roles.split(","));
        
        return new ArrayList<>();
    }
    
    public List<String> getPermissionsList() {
        if(this.permissions.length() > 0)
            return Arrays.asList(this.permissions.split(","));
        
        return new ArrayList<>();
    }
}
