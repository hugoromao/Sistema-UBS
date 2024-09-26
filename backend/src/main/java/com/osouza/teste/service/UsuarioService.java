package com.osouza.teste.service;

import com.osouza.teste.domain.dto.BaseDTO;
import com.osouza.teste.domain.dto.request.UsuarioAtualizarSenhaRequestDTO;
import com.osouza.teste.domain.dto.response.UsuarioResponseDTO;
import com.osouza.teste.domain.entity.Permissao;
import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.repository.PermissaoRepository;
import com.osouza.teste.repository.UsuarioRepository;
import com.osouza.teste.service.exception.UsuarioException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PermissaoRepository permissaoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PermissaoRepository permissaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.permissaoRepository = permissaoRepository;
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new UsuarioException(UsuarioException.USUARIO_NAO_EXISTE);
        }
        return usuarioOptional.get();
    }

    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        if(usuario.getId() == null) {
            throw new UsuarioException(UsuarioException.USUARIO_ERRO_ATUALIZAR_SEM_ID);
        }

        Optional<Usuario> usuarioAntigoOptional = usuarioRepository.findById(usuario.getId());
        if(usuarioAntigoOptional.isEmpty()) throw new UsuarioException(UsuarioException.USUARIO_NAO_EXISTE);

        Usuario usuarioAntigo = usuarioAntigoOptional.get();
        BeanUtils.copyProperties(usuario, usuarioAntigo, BaseDTO.getNullPropertyNames(usuario));

        usuarioRepository.save(usuarioAntigo);
        return usuarioAntigo;
    }

    public Usuario deletar(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
        return usuario;
    }

    public Page<UsuarioResponseDTO> listar(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(UsuarioResponseDTO::convert);
    }

    public List<Permissao> findAllPermissoesByUsuarioId(Long id) {
        return permissaoRepository.findAllPermissoesByUsuarioId(id);
    }

    public List<Permissao> findAllPermissoesByLoginAndSenha(String login, String senha) {
        return permissaoRepository.findAllPermissoesByLoginAndSenha(login, senha);
    }

    public void atualizarSenha(UsuarioAtualizarSenhaRequestDTO usuarioAtualizarSenhaDTO, String nomeToken) {
        Usuario usuario = findById(usuarioAtualizarSenhaDTO.getId());

        if (!usuario.getNome().equals(nomeToken)) {
            throw new UsuarioException(UsuarioException.USUARIO_ERRO_ATUALIZAR_SENHA);
        }

        usuario.setSenha(usuarioAtualizarSenhaDTO.getSenha());
        usuarioRepository.save(usuario);
    }

}
