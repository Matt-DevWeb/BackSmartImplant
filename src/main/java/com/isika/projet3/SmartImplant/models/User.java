package com.isika.projet3.SmartImplant.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User implements UserDetails { // ✅ Implémentation de UserDetails pour Spring Security

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String firstName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // ✅ Ajout de @Column(nullable = false) pour éviter les valeurs null

    // 🔹 Constructeurs
    public User() {
    }

    public User(Integer id, String nom, String prenom, String email, String password, Role role) {
        this.id = id;
        this.name = nom;
        this.firstName = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // 🔹 Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return name;
    }

    public void setNom(String nom) {
        this.name = nom;
    }

    public String getPrenom() {
        return firstName;
    }

    public void setPrenom(String prenom) {
        this.firstName = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // 🔹 Implémentation de UserDetails (Spring Security)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email; // 🔹 Utilisé pour l'authentification
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 🔹 Méthode toString améliorée
    @Override
    public String toString() {
        return "User{id=" + id + ", nom='" + name + "', prenom='" + firstName + "', email='" + email + "', role=" + role
                + "}";
    }
}
