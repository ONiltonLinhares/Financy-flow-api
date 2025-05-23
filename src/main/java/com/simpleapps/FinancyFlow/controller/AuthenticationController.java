package com.simpleapps.FinancyFlow.controller;

import com.simpleapps.FinancyFlow.dto.usuario.AuthenticationDTO;
import com.simpleapps.FinancyFlow.dto.usuario.LoginResponseDTO;
import com.simpleapps.FinancyFlow.dto.usuario.UsuarioRequestDTO;
import com.simpleapps.FinancyFlow.dto.usuario.UsuarioResponseDTO;
import com.simpleapps.FinancyFlow.model.Usuario;
import com.simpleapps.FinancyFlow.repository.UsuarioRepository;
import com.simpleapps.FinancyFlow.service.TokenService;
import com.simpleapps.FinancyFlow.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid UsuarioRequestDTO data){
        if(usuarioRepository.existsByEmail(data.getEmail())) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getSenha());
        data.setSenha(encryptedPassword);
        UsuarioResponseDTO response = usuarioService.criarUsuario(data);

        return ResponseEntity.ok().body(response);
    }
}
