package com.osouza.teste.components;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

import javax.crypto.SecretKey;

import com.osouza.teste.domain.entity.Permissao;
import com.osouza.teste.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtTokenUtil {
    
    private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;

    private static final String TOKEN_ROLES = "ROLES";

    @Value("${jwt.secret}")
    private String secret;

    private final UsuarioService usuarioService;

    public JwtTokenUtil(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public String generateToken(UserDetails usuarioDetails) {

        Map<String, Object>  claims = new HashMap<>();

        List<String> permissions = new ArrayList<>();
        for (Permissao permissao : findAllPermissoesByLoginAndSenha(usuarioDetails)) {
            String nome = permissao.getNome();
            permissions.add(nome);
        }

        claims.put("ROLES", permissions);
        
        return doGenerateToken(claims, usuarioDetails.getUsername());

    }

    private List<Permissao> findAllPermissoesByLoginAndSenha(UserDetails usuarioDetails) {
        return usuarioService.findAllPermissoesByLoginAndSenha(
                usuarioDetails.getUsername(),
                usuarioDetails.getPassword()
        );
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        Long timeInMs = System.currentTimeMillis();
        Date now = new Date(timeInMs);
        Date expDate = new Date(timeInMs + JWT_TOKEN_VALIDITY);
        
        // SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(now)
            .setExpiration(expDate)
            .signWith(key)
            .compact();

    }

    // retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // generate token for user
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        }
        return false;
    }

    public List<String> getRolesFromToken(String token) {        
        Claims claims = getAllClaimsFromToken(token);
        return (List<String>) claims.get(TOKEN_ROLES);
    }

}
