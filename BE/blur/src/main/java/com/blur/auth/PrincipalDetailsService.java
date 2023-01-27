package com.blur.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blur.entity.User;
import com.blur.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User UserEntity = UserRepository.findByUserId(userId);
        if(UserEntity != null) {
            return new PrincipalDetails(UserEntity);
        }
        return null;
    }

}
