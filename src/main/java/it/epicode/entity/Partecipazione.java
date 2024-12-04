package it.epicode.entity;

import it.epicode.SessoEnum;
import it.epicode.StatoEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "partecipazioni")
@Data

public class Partecipazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column (name = "stato")
    private StatoEnum stato;
}
