package team5.aziendaenergia.entities;

import jakarta.persistence.*;
import lombok.*;
import team5.aziendaenergia.enums.TypeUser;

import java.util.List;

@Setter
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
    @Enumerated(EnumType.STRING)
    private TypeUser typeuser;

    @OneToMany(mappedBy = "user")
    private List<Cliente> clienti;


    public User(String username, String email,
                int password, String nome, String cognome,
                String avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.avatar = avatar;
        this.typeuser = TypeUser.USER;
    }
}
