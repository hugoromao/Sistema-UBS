package com.osouza.teste.domain.dto.response;

import com.osouza.teste.domain.entity.Permissao;
import com.osouza.teste.domain.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String login;
    private String foto;
    private List<Permissao> permissao;

    public static UsuarioResponseDTO convert(Usuario usuario) {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
        responseDTO.setId(usuario.getId());
        responseDTO.setNome(usuario.getNome());
        responseDTO.setLogin(usuario.getLogin());
        responseDTO.setFoto(usuario.getFoto());
        responseDTO.setPermissao(usuario.getPermissao());
        return responseDTO;
    }

}
