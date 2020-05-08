/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gfurri20.blog.repository;

import it.gfurri20.blog.domain.BlogUser;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author gfurri20
 */
public interface IBlogUserRepository extends CrudRepository<BlogUser, Long>
{
    /**
     * Search an user by its username
     * 
     * @param username
     * @return the registered user
     */
    public Optional<BlogUser> findByUsername(String username);
}
