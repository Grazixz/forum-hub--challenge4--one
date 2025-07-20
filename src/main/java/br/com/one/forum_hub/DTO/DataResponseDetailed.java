package br.com.one.forum_hub.DTO;

import br.com.one.forum_hub.model.ResponseT;

import java.time.LocalDateTime;

public record DataResponseDetailed(
        Long id,
        Long idTopic,
        String message,
        Long idUser,
        String Author,
        LocalDateTime creationDate) {
    public DataResponseDetailed(ResponseT response) {
        this(response.getId(), response.getTopic().getId(), response.getMessage(), response.getUserR().getId(), response.getUserR().getNickname(), response.getCreationDate());
    }
}
