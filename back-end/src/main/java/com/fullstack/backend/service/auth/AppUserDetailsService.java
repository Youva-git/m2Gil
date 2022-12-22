package com.fullstack.backend.service.auth;

import com.fullstack.backend.dto.UserDto;
import com.fullstack.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository vUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<com.fullstack.backend.modele.User> user = vUserRepository.findByMail(email);
        UserDto vUser = UserDto.fromEntity(user.get());

        //List<SimpleGrantedAuthority> vAuthorityList = new ArrayList<>();
        //vUser.getRoles().forEach(role -> vAuthorityList.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new User(vUser.getMail(), vUser.getMdp(), Collections.emptyList());
    }
}
