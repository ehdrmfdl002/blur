package com.blur.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blur.entity.Member;
import com.blur.repository.MemberRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository MemberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member MemberEntity = MemberRepository.findByMemberId(memberId);
        if(MemberEntity != null) {
            return new PrincipalDetails(MemberEntity);
        }
        return null;
    }

}
