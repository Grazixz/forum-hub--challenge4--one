package br.com.one.forum_hub.DTO;

import br.com.one.forum_hub.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public record DataUserDetailed(
        Long id,
        String nickname,
        LocalDateTime creationDate) {
    public DataUserDetailed(User user) {
        this(user.getId(), user.getNickname(), user.getCreationDate());
    }
}
