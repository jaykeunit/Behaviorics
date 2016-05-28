package behaviorics;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/login", "/resources/**", "/css/**","/images/**","lib/**","/scripts/**").permitAll()
                .antMatchers("/admin/addentity", "/admin/addbuilding", "/admin/addfloor", "/admin/addcamera", "/admin/addUser").hasAnyAuthority("admin")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/accessdenied")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/reports")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.authenticationProvider(new CustomAuthenticationProvider());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }
}
