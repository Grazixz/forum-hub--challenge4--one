package br.com.one.forum_hub.service.security;

import br.com.one.forum_hub.reposity.ReposityUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ReposityUser reposityUser;
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getAuthorization(request);
        if (token != null){
            var subject = tokenService.getSubject((String) token);
            var user = reposityUser.findByEmail(subject);

            if (user.isPresent()){
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private Object getAuthorization(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        if (authorization != null) return authorization.replace("Bearer ", "");
        return null;
    }

}
