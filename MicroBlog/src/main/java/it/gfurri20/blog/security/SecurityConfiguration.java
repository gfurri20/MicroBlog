package it.gfurri20.blog.security;

import it.gfurri20.blog.repository.IBlogUserRepository;
import it.gfurri20.blog.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *
 * @author gfurri20
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @Autowired
    private IBlogUserRepository userRepository;

    @Override
    protected void configure( AuthenticationManagerBuilder auth )
    {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception
    {
        http
                // remove csrf and setting stateless policy
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()
                .and()
                //jwt filters 
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                //setting cors policy
                .cors().and()
                .authorizeRequests()
                // configure access rules
                .antMatchers(HttpMethod.POST, "/api/posts").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/comments").hasAnyRole("USER", "ADMIN");
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings( CorsRegistry registry )
            {
                registry.addMapping("/**").allowedOrigins("*").exposedHeaders("Location", "Authorization").allowedHeaders("*");
            }
        };
    }
}
