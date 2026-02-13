package gabrielebelluco.u5d5w2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dipendente {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "dipendenteId")
    private UUID dipendenteId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false)
    private String email;
    @Column
    private String immagineDip;


    public Dipendente(String username, String cognome, String nome, String email, String immagineDip) {
        this.username = username;
        this.cognome = cognome;
        this.nome = nome;
        this.email = email;
        this.immagineDip = immagineDip;
    }
}
