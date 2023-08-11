package com.member.invitation.memberinvitation.service;

import com.member.invitation.memberinvitation.domain.entity.Member;
import com.member.invitation.memberinvitation.domain.repository.MemberRepository;
import com.member.invitation.memberinvitation.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member newParticipant(String name, String email, String phoneNumber) {
        Member newParticipant = Member.newParticipant(name, email, phoneNumber);
        return memberRepository.save(newParticipant);
    }

    @Transactional
    public Member activate(Long memberId) {
        Member member = getMemberById(memberId);
        member.activate();
        return member;
    }

    @Transactional(readOnly = true)
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundMemberException(memberId));
    }

}
