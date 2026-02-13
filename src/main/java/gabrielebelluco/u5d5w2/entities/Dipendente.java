package gabrielebelluco.u5d5w2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dipendente {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dipendenteId")
    private Long dipendenteId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false)
    private String email;


    public Dipendente(String username, String cognome, String nome, String email) {
        this.username = username;
        this.cognome = cognome;
        this.nome = nome;
        this.email = email;
    }
}
