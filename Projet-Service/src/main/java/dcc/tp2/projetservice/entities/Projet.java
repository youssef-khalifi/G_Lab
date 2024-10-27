package dcc.tp2.projetservice.entities;

import dcc.tp2.projetservice.module.Chercheur;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Builder
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private String status;
    private Long id_chercheur;
    private Long id_enseignant;

    @Transient
    private Chercheur chercheur; // une fois récuperer chercher -> alors on a le Enseignant



}
