package br.com.one.forum_hub.service.security;

import br.com.one.forum_hub.model.User;
import br.com.one.forum_hub.reposity.ReposityUser;
import br.com.one.forum_hub.service.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationUser implements UserDetailsService {
    @Autowired
    private ReposityUser reposityUser;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = reposityUser.findByEmail(username);
        return user.orElse(null);
    }
}
