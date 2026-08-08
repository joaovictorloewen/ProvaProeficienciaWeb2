package br.furb.resapifurb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.furb.resapifurb.entity.Equipment;
import br.furb.resapifurb.entity.EquipmentType;
import br.furb.resapifurb.entity.User;
import br.furb.resapifurb.repository.EquipmentRepository;
import br.furb.resapifurb.repository.EquipmentTypeRepository;
import br.furb.resapifurb.repository.UserRepository;

@SpringBootApplication
public class ResApifurbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResApifurbApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(EquipmentTypeRepository typeRepo, EquipmentRepository equipRepo, UserRepository userRepo) {
        return args -> {
            EquipmentType computador = typeRepo.save(new EquipmentType(null, "Computador"));
            EquipmentType audiovisual = typeRepo.save(new EquipmentType(null, "Audiovisual"));
            EquipmentType impressora = typeRepo.save(new EquipmentType(null, "Impressora"));

            equipRepo.save(new Equipment(null, "Notebook Dell", computador));
            equipRepo.save(new Equipment(null, "Projetor Epson", audiovisual));
            equipRepo.save(new Equipment(null, "Notebook Lenovo", computador));
            
            // Hash BCrypt válido para a senha "senha123" (o hash anterior não correspondia a essa senha)
            userRepo.save(new User(null, "admin", "$2b$10$zW0cHlQ.yNJD3eYekRXYku/JbFvI5r8wB9lFBEtFvOgNaT/tbpP6."));
        };
    }
}
