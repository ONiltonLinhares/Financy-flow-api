package com.simpleapps.FinancyFlow.model;

import com.simpleapps.FinancyFlow.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senhaHash;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        switch (tipo) {
            case TipoUsuario.ADMIM:
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                // Admin também herda permissões de pagante e gratuito
                authorities.add(new SimpleGrantedAuthority("ROLE_PAGANTE"));
                authorities.add(new SimpleGrantedAuthority("ROLE_GRATUITO"));
                break;

            case TipoUsuario.PAGANTE:
                authorities.add(new SimpleGrantedAuthority("ROLE_PAGANTE"));
                authorities.add(new SimpleGrantedAuthority("ROLE_GRATUITO")); // herda gratuito
                break;

            case TipoUsuario.GRATUITO:
                authorities.add(new SimpleGrantedAuthority("ROLE_GRATUITO"));
                break;

            default:
                throw new IllegalArgumentException("Tipo de usuário desconhecido: " + tipo);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return senhaHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}