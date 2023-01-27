package com.blur.service;

import com.blur.api.dto.MemberProfileDto;
import com.blur.entity.Member;
import com.blur.entity.MemberProfile;
import com.blur.repository.MemberProfileRepository;
import com.blur.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberProfileRepository memberProfileRepository;

    public MemberProfileDto getProfile(String memberId) {
        Member Member = memberRepository.findByMemberId(memberId);
        MemberProfile memberProfile = memberProfileRepository.findByMemberId(memberId);
        MemberProfileDto memberProfileDto = new MemberProfileDto(Member, memberProfile);
        return memberProfileDto;
    }

    public void updateProfile(MemberProfileDto memberProfileDto) {
        Member member = memberRepository.findByMemberId(memberProfileDto.getMemberId());
        MemberProfile memberProfile = memberProfileRepository.findByMemberId(memberProfileDto.getMemberId());
        member.updateGender(memberProfileDto.getGender());
        memberProfile.updateProfile(memberProfileDto.getBirthyear(), memberProfileDto.getNickname(), memberProfileDto.getImage());
        memberRepository.save(member);
        memberProfileRepository.save(memberProfile);

    }
}
