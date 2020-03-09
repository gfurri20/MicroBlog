
package it.gfurri20.blog.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author gfurri20
 */
@Entity
@Data
@Table(name = "comments")
public class BlogComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Basic
    private String content;
    @Basic
    private String pubblicationDate;
    @Basic
    private String author;
    @ManyToOne
    private BlogPost correlatedPost;

}
