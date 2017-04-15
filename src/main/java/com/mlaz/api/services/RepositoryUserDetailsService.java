package com.mlaz.api.services;

import com.google.gson.Gson;
import com.mlaz.api.Repositories.MlazUserProfileRepository;
import com.mlaz.api.model.MlazUserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

/**
 * Created by jimmy on 15/4/17.
 */
public class RepositoryUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryUserDetailsService.class);

    private MlazUserProfileRepository mlazUserProfileRepository;

    @Autowired
    public RepositoryUserDetailsService(MlazUserProfileRepository mlazUserProfileRepository) {
        this.mlazUserProfileRepository = mlazUserProfileRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LOGGER.debug("Loading user by Username:{}", userName);

        MlazUserProfile userProfile = mlazUserProfileRepository.findByUserId(userName);
        LOGGER.debug("Found a user:{}", (new Gson()).toJson(userProfile));

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return userProfile.getPassword();
            }

            @Override
            public String getUsername() {
                return userProfile.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };

        return userDetails;
    }
}
