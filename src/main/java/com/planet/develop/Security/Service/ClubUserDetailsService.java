package com.planet.develop.Security.Service;

import com.planet.develop.Entity.User;
import com.planet.develop.Repository.UserRepository;
import com.planet.develop.Security.DTO.ClubAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(username, false);
        if(!result.isPresent()) {
            log.info("error happened");
            throw new UsernameNotFoundException("Check Email or Social");
        }
        User user = result.get();
        ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(
                user.getUserId(),
                null,
                user.isFromSocial(),
                user.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet())
        );
        clubAuthMemberDTO.setName(user.getUserName());
        clubAuthMemberDTO.setFromSocial(user.isFromSocial());
        return clubAuthMemberDTO;
    }

}
