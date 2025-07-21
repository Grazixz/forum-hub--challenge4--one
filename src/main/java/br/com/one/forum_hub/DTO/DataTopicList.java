package br.com.one.forum_hub.DTO;

import br.com.one.forum_hub.model.Topic;

import java.time.LocalDateTime;

public record DataTopicList(
        Long Id,
        String Title,
        String message,
        LocalDateTime creationDate,
        Long idUser,
        String author,
        Long idCourse,
        String course,
        boolean resolved) {
    public DataTopicList(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getUserT().getId(), topic.getUserT().getNickname(), topic.getCourse().getId(), topic.getCourse().getTitle(), topic.isResolved());
    }

}
