package org.example.toy_restboard.common.security.service;

import lombok.RequiredArgsConstructor;
import org.example.toy_restboard.domain.entity.User;
import org.example.toy_restboard.repository.UserRepository;
import org.example.toy_restboard.domain.dto.userdto.AccountContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component("userDetailsService")
public class FormUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username).orElseThrow();
        if(user == null) {
            throw new UsernameNotFoundException("not found LoginId: " + username);
        }
        AccountDto accountDto = AccountDto.toDto(user);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        return new AccountContext(authorities, accountDto);
    }
}
