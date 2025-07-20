package br.com.one.forum_hub.DTO;

import br.com.one.forum_hub.model.User;

import java.time.LocalDateTime;

public record DataUserList(
        Long id,
        String nickname,
        LocalDateTime creationDate) {
    public DataUserList(User user) {
        this(user.getId(), user.getNickname(), user.getCreationDate());
    }
}
