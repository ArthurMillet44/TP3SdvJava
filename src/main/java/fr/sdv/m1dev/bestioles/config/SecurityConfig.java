package fr.sdv.m1dev.bestioles.config;

import fr.sdv.m1dev.bestioles.dal.PersonRepository;
import fr.sdv.m1dev.bestioles.dal.RoleRepository;
import fr.sdv.m1dev.bestioles.domain.Person;
import fr.sdv.m1dev.bestioles.domain.Role;
import fr.sdv.m1dev.bestioles.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CommandLineRunner initDatabase(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        return args -> {
            Role personRole = new Role();
            personRole.setLabel("ROLE_USER");
            roleRepository.save(personRole);

            Person person = new Person();
            person.setFirstname("Arthur");
            person.setLastname("Millet");
            person.setLogin("admin");
            person.setMdp(encoder.encode("password"));
            person.setRoles(List.of(personRole));
            personRepository.save(person);
        };
    }
}
