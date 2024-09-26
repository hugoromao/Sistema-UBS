package com.osouza.teste.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
    
    @NotBlank(message = "Login é necessário")
    private String login;
    @NotBlank(message = "Senha é um campo obrigatório")
    private String senha;


}
