package fr.sdv.m1dev.bestioles.service;

import fr.sdv.m1dev.bestioles.dal.PersonRepository;
import fr.sdv.m1dev.bestioles.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PersonRepository dao;

    @Autowired
    public CustomUserDetailsService(PersonRepository dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person user = dao.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getMdp(),
                user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getLabel())).toList()
        );
    }
}