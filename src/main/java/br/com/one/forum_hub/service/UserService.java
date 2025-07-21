package br.com.one.forum_hub.service;

import br.com.one.forum_hub.DTO.DataResponsePost;
import br.com.one.forum_hub.DTO.DataTopicPost;
import br.com.one.forum_hub.DTO.DataUserLogin;
import br.com.one.forum_hub.model.User;
import br.com.one.forum_hub.reposity.ReposityUser;
import br.com.one.forum_hub.service.exceptions.ExceptionData;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private ReposityUser reposityUser;
    @Autowired
    private PasswordEncoder encoder;
    @Transactional
    public void userAnalysis(DataUserLogin data) {
        Optional<User> userOptional = reposityUser.findByEmail(data.email());

        if (userOptional.isEmpty()) {
            String encryptedPass = encoder.encode(data.password());
            User newUser = new User(data, encryptedPass);
            reposityUser.save(newUser);
        } else if (!userOptional.get().isActive()) userOptional.get().disable();
    }
    public User checkUserExists(Long id) {
        Optional<User> user = reposityUser.findByIdAndActiveTrue(id);
         if (user.isPresent()){
             return user.get();
         } else throw new ExceptionData("Usuário não existe no sistema!");
    }
}
