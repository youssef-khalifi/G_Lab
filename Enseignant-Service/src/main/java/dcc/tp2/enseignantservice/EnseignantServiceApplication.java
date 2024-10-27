package dcc.tp2.enseignantservice;

import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.repository.EnseignantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class EnseignantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnseignantServiceApplication.class, args);
    }



    @Bean
    CommandLineRunner start(EnseignantRepository repository) {
        return args -> {
            Enseignant enseignant1 = Enseignant.builder()
                    .nom("John")
                    .prenom("Doe")
                    .cne("CNE12345")
                    .email("john.doe@example.com")
                    .password("password123")
                    .thematique("Informatique")
                    .Role("Enseignant")
                    .build();

            Enseignant enseignant2 = Enseignant.builder()
                    .nom("Jane")
                    .prenom("Smith")
                    .cne("CNE67890")
                    .email("jane.smith@example.com")
                    .password("password456")
                    .thematique("Mathématiques")
                    .Role("Enseignant")
                    .build();

            Enseignant enseignant3 = Enseignant.builder()
                    .nom("Emily")
                    .prenom("Johnson")
                    .cne("CNE11223")
                    .email("emily.johnson@example.com")
                    .password("password789")
                    .thematique("Physique")
                    .Role("Enseignant")
                    .build();

            repository.save(enseignant1);
            repository.save(enseignant2);
            repository.save(enseignant3);

        };
    }
}
