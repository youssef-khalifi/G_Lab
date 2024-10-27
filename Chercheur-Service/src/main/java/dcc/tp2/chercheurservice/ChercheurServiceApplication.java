package dcc.tp2.chercheurservice;

import dcc.tp2.chercheurservice.entities.Chercheur;
import dcc.tp2.chercheurservice.repository.ChercheurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class ChercheurServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChercheurServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ChercheurRepository chercheurRepository) {
        return args -> {
            // Add sample data using the builder pattern
            Chercheur chercheur1 = Chercheur.builder()
                    .nom("ussf")
                    .prenom("khalifi")
                    .email("ussf@example.com")
                    .password("password123")
                    .tel("0123456789")
                    .role("Chercheur")
                    .id_enseignant(1L)
                    .id_projet(1L)
                    .build();

            Chercheur chercheur2 = Chercheur.builder()
                    .nom("Martin")
                    .prenom("Sophie")
                    .email("sophie.martin@example.com")
                    .password("password456")
                    .tel("9876543210")
                    .role("Chercheur")
                    .id_enseignant(2L)
                    .id_projet(2L)
                    .build();

            Chercheur chercheur3 = Chercheur.builder()
                    .nom("Bernard")
                    .prenom("Luc")
                    .email("luc.bernard@example.com")
                    .password("password789")
                    .tel("0192837465")
                    .role("Chercheur")
                    .id_enseignant(3L)
                    .id_projet(3L)
                    .build();

            // Save the researchers to the database
            chercheurRepository.save(chercheur1);
            chercheurRepository.save(chercheur2);
            chercheurRepository.save(chercheur3);

            System.out.println("Sample researchers have been added to the database using the Builder pattern.");
        };
    }

}
