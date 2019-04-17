package com.max.fruitshop.security;

import com.max.fruitshop.domain.AppUser;
import com.max.fruitshop.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepository userRepository;

    public UserDetailsServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userRepository.findByUsername(username);

        if (!appUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        return new User(appUser.get().getUsername(), appUser.get().getPassword(), Collections.emptyList());
    }
}
