package com.osouza.teste.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioAtualizarSenhaRequestDTO {
    @NotNull
    private Long id;
    @NotEmpty
    private String senha;
}
