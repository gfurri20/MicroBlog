
package it.gfurri20.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gfurri20
 */
@Entity
@Table(name = "posts")
public class BlogPost implements Serializable
{
    public BlogPost() {}

    public BlogPost( String title, String content, Date pubblicationDate, BlogUser author )
    {
        this.title = title;
        this.content = content;
        this.pubblicationDate = pubblicationDate;
        this.author = author;
    }
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    
    @Basic
    @Getter @Setter
    private String title;
    @Lob @Basic
    @Getter @Setter
    private String content;
    @Temporal( TemporalType.TIMESTAMP )
    @Getter @Setter
    private Date pubblicationDate;
    
    @ManyToOne
    @Getter @Setter
    private BlogUser author;
    
}
