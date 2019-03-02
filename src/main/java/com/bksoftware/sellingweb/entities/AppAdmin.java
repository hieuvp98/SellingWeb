package com.bksoftware.sellingweb.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "admin")
public class AppAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String email;

    private int phone;

    public Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add((GrantedAuthority) () -> "ADMIN");
        return grantedAuthorities;
    }
}
