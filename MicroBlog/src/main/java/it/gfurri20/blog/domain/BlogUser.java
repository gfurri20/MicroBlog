
package it.gfurri20.blog.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gfurri20
 */
@Entity
@Data
@Table(name = "users")
public class BlogUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;
    
    @Basic
    @Getter @Setter
    private String username;
    @Basic
    @Getter @Setter
    private String password;
    @Basic
    @Getter @Setter
    private String salt;
    
}
