package ru.dozorov.tabookServer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "userId")
//    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Record> records;

    @Id
    private String username; //Логин от обычной аутентификации ИЛИ id от гугла! урааа
    private String email;
    private String password;

    @Column(updatable = false)
    private LocalDateTime created;

    public User(String username, String email, String password, LocalDateTime created) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.created = created;
    }
}
