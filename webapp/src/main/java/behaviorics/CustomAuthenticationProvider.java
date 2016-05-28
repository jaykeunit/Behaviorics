package behaviorics;

import behaviorics.httpRequests.UserRequests;
import behaviorics.models.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomAuthenticationProvider implements AuthenticationProvider{
    private LoginService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        service = new LoginService();
        User user;

        String password = authentication.getCredentials().toString();
        String name = authentication.getName();

        try {
            user = UserRequests.getUserByUsername(name);
            user.setKey(password);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username not found");
        }

        if(service.validUser(user) && name.equals(user.getUserName())){
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getPrivilege()));
            Authentication token = new UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
            return token;
        }
        else
            return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
