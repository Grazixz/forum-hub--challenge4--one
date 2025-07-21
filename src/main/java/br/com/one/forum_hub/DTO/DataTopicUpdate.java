package br.com.one.forum_hub.DTO;

public record DataTopicUpdate(
        String title,
        String message,
        Long idCourse,
        boolean resolved) {
}
