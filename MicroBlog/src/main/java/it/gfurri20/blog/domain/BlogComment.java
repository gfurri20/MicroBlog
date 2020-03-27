
package it.gfurri20.blog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "comments")
public class BlogComment implements Serializable
{

    public BlogComment() {}

    public BlogComment( String content, Date pubblicationDate, BlogUser author, BlogPost correlatedPost )
    {
        this.content = content;
        this.pubblicationDate = pubblicationDate;
        this.author = author;
        this.correlatedPost = correlatedPost;
    }

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    
    @Basic
    @Getter @Setter
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date pubblicationDate;
    
    @ManyToOne
    @Getter @Setter
    private BlogUser author;
    
    @ManyToOne
    @Getter @Setter
    private BlogPost correlatedPost;

}
