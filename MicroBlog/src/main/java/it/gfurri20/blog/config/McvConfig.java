/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gfurri20.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *
 * @author gfurri20
 */
@Configuration
public class McvConfig implements WebMvcConfigurer
{
    @Override
    public void addViewControllers( ViewControllerRegistry registry )
    {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/posts").setViewName("view-posts");
        registry.addViewController("/posts/{id}").setViewName("view-post");
    }
}
