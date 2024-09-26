package com.osouza.teste.service;

import java.util.ArrayList;

import com.osouza.teste.service.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.osouza.teste.domain.entity.Usuario;
import com.osouza.teste.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        
        // nesse contexto o username será tratado como login (uma coluna unica na tabela)
        Usuario usuario = usuarioRepository.findByLogin(login);
        if(usuario == null) {
            throw new UsuarioException(UsuarioException.USUARIO_NAO_EXISTE);
        }

        return new User(usuario.getNome(), usuario.getSenha(), new ArrayList<>());

    }
    
}
