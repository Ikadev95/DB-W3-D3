package it.epicode.entity;

import it.epicode.SessoEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "persone")
@Data

public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @Getter
    @Column (name = "nome",nullable = false , length = 50)
    private String nome;

    @Setter
    @Getter
    @Column (name = "cognome", nullable = false, length = 50)
    private String cognome;

    @Getter
    @Setter
    @Column (name = "email", length = 50)
    private String email;

    @Setter
    @Getter
    @Column (name = "data_di_nascita")
    private LocalDate dataDiNascita;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column (name = "sesso")
    private SessoEnum Sesso;

    @Setter
    @Getter
    @OneToMany (mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Partecipazione> partecipazioni;


}
