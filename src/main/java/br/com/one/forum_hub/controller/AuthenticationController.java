package br.com.one.forum_hub.controller;

import br.com.one.forum_hub.DTO.DataUserLogin;
import br.com.one.forum_hub.DTO.dataToken;
import br.com.one.forum_hub.model.User;
import br.com.one.forum_hub.reposity.ReposityUser;
import br.com.one.forum_hub.service.UserService;
import br.com.one.forum_hub.service.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @PostMapping
    @Transactional
    public ResponseEntity loginUser(@RequestBody @Valid DataUserLogin data) {
        userService.userAnalysis(data);

        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        User user = (User) authentication.getPrincipal();
        var token = tokenService.generateToken(user);
        return ResponseEntity.ok().body(new dataToken(token));
    }
}
