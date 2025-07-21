package br.com.one.forum_hub.controller;

import br.com.one.forum_hub.DTO.*;
import br.com.one.forum_hub.model.User;
import br.com.one.forum_hub.reposity.ReposityResponse;
import br.com.one.forum_hub.reposity.ReposityTopic;
import br.com.one.forum_hub.reposity.ReposityUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UserController {
    @Autowired
    private ReposityUser reposityUser;
    @Autowired
    private ReposityTopic reposityTopic;
    @Autowired
    private ReposityResponse reposityResponse;
    @GetMapping("/{id}")
    public ResponseEntity viewUser(@PathVariable Long id) {
        Optional<User> user = reposityUser.findByIdAndActiveTrue(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(new DataUserDetailed(user.get()));
        } return ResponseEntity.notFound().build();
    }
    @GetMapping("/lista")
    public  ResponseEntity<Page<DataUserList>> userList(@PageableDefault(size = 10, sort = {"creationDate"})Pageable pageable) {
        var page = reposityUser.findByActiveTrue(pageable)
                .map(DataUserList::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity userUpdate(@PathVariable Long id, @RequestBody DataUserUpdate data) {
        Optional<User> userOptional = reposityUser.findByIdAndActiveTrue(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.update(data);
            return ResponseEntity.ok(new DataUserDetailed(user));
        } else return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        Optional<User> user = reposityUser.findByIdAndActiveTrue(id);
        if (user.isPresent()) {
            user.get().disable();
            return ResponseEntity.noContent().build();
        } return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}/topicos")
    public ResponseEntity<Page<DataTopicList>> topicsUser(@PathVariable Long id, @PageableDefault(size = 10, sort = {"creationDate"}) Pageable pageable) {
        Optional<User> userOptional = reposityUser.findByIdAndActiveTrue(id);
        if (userOptional.isPresent()) {
            var page = reposityTopic.findAllByUserTId(id, pageable)
                    .map(DataTopicList::new);
            return ResponseEntity.ok(page);
        } else return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}/respostas")
    public ResponseEntity<Page<DataResponseList>> responsesUser(@PathVariable Long id, @PageableDefault(size = 10, sort = {"creationDate"}) Pageable pageable) {
        Optional<User> userOptional = reposityUser.findByIdAndActiveTrue(id);
        if (userOptional.isPresent()) {
            var page = reposityResponse.findAllByUserRId(id, pageable)
                    .map(DataResponseList::new);
            return ResponseEntity.ok(page);
        } else return ResponseEntity.notFound().build();
    }
}
