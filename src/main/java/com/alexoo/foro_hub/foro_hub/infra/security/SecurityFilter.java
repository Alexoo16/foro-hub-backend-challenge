package com.alexoo.foro_hub.foro_hub.infra.security;

import com.alexoo.foro_hub.foro_hub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filtrando...");
        var authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);

        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            System.out.println(token);
            var subject = tokenService.getSubject(token);
            if(subject != null){
                var usuario = usuarioRepository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());    // Forzamos un inicio de sesión con el usuario encontrado
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}