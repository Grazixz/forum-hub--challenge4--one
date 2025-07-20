package br.com.one.forum_hub.controller;

import br.com.one.forum_hub.DTO.DataResponseDetailed;
import br.com.one.forum_hub.DTO.DataResponseList;
import br.com.one.forum_hub.DTO.DataResponsePost;
import br.com.one.forum_hub.DTO.DataResponseUpdate;
import br.com.one.forum_hub.model.ResponseT;
import br.com.one.forum_hub.reposity.ReposityResponse;
import br.com.one.forum_hub.service.ResponseService;
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

import java.util.Optional;

@RestController
@RequestMapping("/resposta")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {
    @Autowired
    private ReposityResponse reposity;
    @Autowired
    private ResponseService responseService;
    @PostMapping("/responder")
    @Transactional
    public ResponseEntity postResponse(@RequestBody @Valid DataResponsePost data, UriComponentsBuilder uriBuilder) {
        ResponseT response = responseService.createResponse(data);
        var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataResponseDetailed(response));
    }
    @GetMapping("/{id}")
    public ResponseEntity viewResponse(@PathVariable Long id) {
        Optional<ResponseT> response = reposity.findById(id);
        if (response.isPresent()) {
            return ResponseEntity.ok(new DataResponseDetailed(response.get()));
        } else return ResponseEntity.notFound().build();
    }
    @GetMapping("/lista")
    public ResponseEntity<Page<DataResponseList>> responseList(@PageableDefault(size = 10, sort = {"creationDate"})Pageable pageable) {
        var page = reposity.findAll(pageable)
                .map(DataResponseList::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity updateResponse(@PathVariable Long id, @RequestBody DataResponseUpdate data) {
        Optional<ResponseT> responseOptional = reposity.findById(id);
        if (responseOptional.isPresent()) {
            responseService.checkUpdateResponse(data);
            ResponseT response = responseOptional.get();
            response.update(data);
            return ResponseEntity.ok(new DataResponseDetailed(response));
        } else return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteResponse(@PathVariable Long id) {
        Optional<ResponseT> responseOptional = reposity.findById(id);
        if (responseOptional.isPresent()) {
            reposity.deleteById(id);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
}
