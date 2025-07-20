package br.com.one.forum_hub.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataResponsePost(
        @NotNull
        Long idTopic,
        @NotBlank
        String message,
        @NotNull
        Long idUser) {
}
