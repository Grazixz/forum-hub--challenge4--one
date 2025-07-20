package br.com.one.forum_hub.controller;

import br.com.one.forum_hub.DTO.*;
import br.com.one.forum_hub.model.ResponseT;
import br.com.one.forum_hub.model.Topic;
import br.com.one.forum_hub.reposity.ReposityResponse;
import br.com.one.forum_hub.reposity.ReposityTopic;
import br.com.one.forum_hub.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    private ReposityTopic reposity;
    @Autowired
    private ReposityResponse reposityResponse;
    @Autowired
    private TopicService topicService;
    @Autowired
    private ResponseController responseController;
    @PostMapping("/postar")
    @Transactional
    public ResponseEntity postTopic(@RequestBody @Valid DataTopicPost data, UriComponentsBuilder uriBuilder){
        Topic topic = topicService.createTopic(data);
        var uri = uriBuilder.path("/topico/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataTopicDetailed(topic));
    }
    @GetMapping("/{id}")
    public ResponseEntity viewTopic(@PathVariable Long id) {
        Optional<Topic> topic = reposity.findById(id);
        if (topic.isPresent()){
            return ResponseEntity.ok().body(new DataTopicDetailed(topic.get()));
        } else return ResponseEntity.notFound().build();
    }
    @GetMapping("/lista")
    public ResponseEntity<Page<DataTopicList>> topicList(@PageableDefault(size = 10, sort = {"creationDate"}) Pageable pageable) {
        var page = reposity.findAll(pageable)
                .map(DataTopicList::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody DataTopicUpdate data) {
        Optional<Topic> topicOptional = reposity.findById(id);

        if (topicOptional.isPresent()) {
            topicService.checkUpdateTopic(data);
            Topic topic = topicOptional.get();
            topic.update(data);
            return ResponseEntity.ok(new DataTopicDetailed(topic));
        } else return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        Optional<Topic> topicOptional = reposity.findById(id);

        if (topicOptional.isPresent()) {
            var responseTList = reposityResponse.findAllByTopicId(id, null);
            responseTList.forEach(r -> reposityResponse.delete(r));
            reposity.deleteById(id);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}/respostas")
    public ResponseEntity<Page<DataResponseList>> responsesTopic(@PathVariable Long id, @PageableDefault(size = 10, sort = {"creationDate"}) Pageable pageable) {
        Optional<Topic> topicOptional = reposity.findById(id);
        if (topicOptional.isPresent()) {
            var page = reposityResponse.findAllByTopicId(id, pageable)
                            .map(DataResponseList::new);

            return ResponseEntity.ok(page);
        } else return ResponseEntity.notFound().build();
    }
}
