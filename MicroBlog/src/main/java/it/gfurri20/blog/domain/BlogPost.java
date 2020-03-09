
package it.gfurri20.blog.domain;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "posts")
public class BlogPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Basic
    private String title;
    @Basic
    private String content;
    @Basic
    private Date pubblicationDate;
    @Basic
    private String author;
    
}
