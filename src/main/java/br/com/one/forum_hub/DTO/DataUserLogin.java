package br.com.one.forum_hub.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataUserLogin(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String nickname) {
}
