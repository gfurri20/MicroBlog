package it.gfurri20.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class MicroBlogApplication
{

    public static void main( String[] args )
    {
        System.setProperty("server.servlet.context-path", "/microblog/v2/api");
        SpringApplication.run(MicroBlogApplication.class, args);
    }

}
