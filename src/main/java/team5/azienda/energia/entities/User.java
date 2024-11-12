package team5.azienda.energia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    private String username;

    private String email;

    private int password;

    private String nome;

    private String cognome;

    private String avatar;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, int password, String nome, String cognome, String avatar, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.avatar = avatar;
        this.roles = roles;
    }
}
