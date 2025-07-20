package br.com.one.forum_hub.DTO;

import br.com.one.forum_hub.model.ResponseT;

import java.time.LocalDateTime;

public record DataResponseList(
        Long id,
        Long idTopic,
        String message,
        Long idUser,
        String author,
        LocalDateTime creationDate) {
    public DataResponseList(ResponseT response) {
        this(response.getId(), response.getTopic().getId(), response.getMessage(), response.getUserR().getId(), response.getUserR().getNickname(), response.getCreationDate());
    }

}
