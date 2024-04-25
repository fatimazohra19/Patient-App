package com.example.demo.iam.service;

import com.example.demo.iam.dao.UserRepository;
import com.example.demo.iam.dto.UserDetailsDto;
import com.example.demo.iam.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    //   private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    // TODO recuperer les autorisations
        Collection<? extends GrantedAuthority> authorities=null;
        return userRepository.findByUsernameIgnoreCase(username).map(u -> UserDetailsDto.builder()
                .id(u.getId())
                .username(u.getUsername())
                .password(u.getPassword())
                .enabled(u.isEnabled())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .authorities(authorities)
                .build()
        ).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
//        return User.builder().username(username).password(passwordEncoder.encode("1234")).roles("USER").build();
//        return UserDetailsDto.builder().username(username).password(passwordEncoder.encode("1234")).build();
    }

}
