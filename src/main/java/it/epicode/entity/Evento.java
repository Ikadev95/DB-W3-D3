package it.epicode.entity;

import it.epicode.TipoEventoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "eventi")
@Data
public class Evento {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @Getter
    @Column (name = "titolo",nullable = false , length = 50)
    private String titolo;

    @Setter
    @Getter
    @Column (name = "data_evento", nullable = false)
    private LocalDate dataEvento;

    @Setter
    @Getter
    @Column (name = "descrizione", nullable = false)
    private String descrizione;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", nullable = false)
    private TipoEventoEnum tipoEvento;

    @Getter
    @Setter
    @Column(name = "numero_massimo_partecipanti")
    private Integer numeroMaxPartecipanti;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Partecipazione> partecipazioni = new ArrayList<>();


}
