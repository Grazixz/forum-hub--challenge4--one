package br.com.one.forum_hub.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataTopicPost(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long idUser,
        @NotBlank
        String coursework) {
}
