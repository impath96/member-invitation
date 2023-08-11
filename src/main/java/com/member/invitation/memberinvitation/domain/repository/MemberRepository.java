package com.member.invitation.memberinvitation.domain.repository;

import com.member.invitation.memberinvitation.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
