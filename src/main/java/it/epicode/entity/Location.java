package it.epicode.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "locations")
@Data
public class Location {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @Getter
    @Column (name = "nome" , length = 50)
    private String nome;

    @Setter
    @Getter
    @Column (name = "citta", length = 50)
    private String citta;

    @Setter
    @Getter
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Evento> eventi = new ArrayList<>();
}
