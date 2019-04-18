package com.max.fruitshop.security;

import com.max.fruitshop.domain.AppUser;
import com.max.fruitshop.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepository userRepository;

    public UserDetailsServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optAppUser = userRepository.findByUsername(username);

        if (!optAppUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        AppUser appUser = optAppUser.get();

        return User//
                .withUsername(appUser.getUsername())//
                .password(appUser.getPassword())//
                .authorities(appUser.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
