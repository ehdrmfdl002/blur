package com.blur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blur.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(String memberId);
//    public Member findByMemberNo(Long MemberNo);
//    boolean existsByMemberId(String MemberId);
}
