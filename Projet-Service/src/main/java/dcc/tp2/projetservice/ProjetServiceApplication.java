package dcc.tp2.projetservice;

import dcc.tp2.projetservice.entities.Projet;
import dcc.tp2.projetservice.repository.ProjetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class ProjetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProjetRepository projetRepository) {
        return args -> {
            // Add sample data using the builder pattern
            Projet projet1 = Projet.builder()
                    .titre("Projet Informatique")
                    .description("Développement d'une application web")
                    .status("En cours")
                    .id_chercheur(1L)
                    .id_enseignant(1L)
                    .build();

            Projet projet2 = Projet.builder()
                    .titre("Projet Mathématiques")
                    .description("Recherche sur les équations différentielles")
                    .status("Terminé")
                    .id_chercheur(2L)
                    .id_enseignant(2L)
                    .build();

            Projet projet3 = Projet.builder()
                    .titre("Projet Physique")
                    .description("Étude sur la relativité générale")
                    .status("En attente")
                    .id_chercheur(3L)
                    .id_enseignant(3L)
                    .build();

            projetRepository.save(projet1);
            projetRepository.save(projet2);
            projetRepository.save(projet3);

        };
    }
}
