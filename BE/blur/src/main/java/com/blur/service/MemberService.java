package com.blur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.blur.api.dto.MemberDto;
import com.blur.api.dto.MemberProfileDto;
import com.blur.entity.Member;
import com.blur.entity.MemberProfile;
import com.blur.repository.MemberProfileRepository;
import com.blur.repository.MemberRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final MemberProfileRepository memberProfileRepository;

    @Autowired
    private final BCryptPasswordEncoder encoder;

    public Member register(MemberDto dto) {
        Member member = dto.toEntity();
        member.updatePassword(encoder.encode(dto.getPassword()));
        memberRepository.save(member);
        MemberProfileDto memberProfileDto = new MemberProfileDto();
        MemberProfile memberProfile = memberProfileDto.toEntity(member);
        memberProfile.setMember(member);
        memberProfileRepository.save(memberProfile);
        System.out.println("DB에 회원 저장 성공");
        return member;

    }

    public Integer checkId(@RequestParam("MemberId")String memberId) {
        Member memberEntity = memberRepository.findByMemberId(memberId);
        if (memberEntity!=null) {
            System.out.println("아이디 있음, 회원가입 불가");
            return 1;
        }
        System.out.println("회원가입가능");
        return 0;
    }







}
