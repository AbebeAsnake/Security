package me.abebe.demo.security;

import me.abebe.demo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    UsersRepository usersRepository;
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return new SSUDS(usersRepository);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/index","/register","/login","/h2-console/**").permitAll()
                .antMatchers("/applicant").hasAnyAuthority("APPLICANT","EMPLOYER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll().logoutSuccessUrl("/").permitAll();
         http
                 .csrf()
                 .disable();
         http .headers()
                 .frameOptions()
                 .disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("passs")
                .authorities("ADMIN");
        auth.userDetailsService(userDetailsServiceBean());
    }
}
