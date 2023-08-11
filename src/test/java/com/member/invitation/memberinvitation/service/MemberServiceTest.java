package com.member.invitation.memberinvitation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.member.invitation.memberinvitation.domain.entity.Member;
import com.member.invitation.memberinvitation.domain.entity.MemberStatus;
import com.member.invitation.memberinvitation.domain.repository.MemberRepository;
import com.member.invitation.memberinvitation.exception.NotFoundMemberException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void 임시_회원_생성() {

        String name = "홍길동";
        String email = "test@example.com";
        String phoneNumber = "010-0000-1111";

        when(memberRepository.save(any())).then(returnsFirstArg());

        Member newParticipant = memberService.newParticipant(name, email, phoneNumber);

        assertThat(newParticipant).isNotNull();
        assertThat(newParticipant.getName()).isEqualTo(name);
        assertThat(newParticipant.getEmail()).isEqualTo(email);
        assertThat(newParticipant.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(newParticipant.getStatus()).isEqualTo(MemberStatus.TEMPORARY);

    }

    @Test
    void 임시_회원_활성화() {

        Long memberId = 1L;
        String name = "홍길동";
        String email = "test@example.com";
        String phoneNumber = "010-0000-1111";

        Member newParticipant = createNewParticipant(name, email, phoneNumber);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(newParticipant));

        Member activateMember = memberService.activate(memberId);

        verify(memberRepository, times(1)).findById(memberId);

        assertThat(activateMember.getName()).isEqualTo(name);
        assertThat(activateMember.getEmail()).isEqualTo(email);
        assertThat(activateMember.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(activateMember.getStatus()).isEqualTo(MemberStatus.ACTIVATE);

    }
    
    @Test
    void memberId_를_통해_회원_조회_성공시_엔티티_반환() {

        Long memberId = 1L;
        String name = "홍길동";
        String email = "test@example.com";
        String phoneNumber = "010-0000-1111";

        Member member = createNewParticipant(name, email, phoneNumber);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

        Member actual = memberService.getMemberById(memberId);

        assertThat(actual.getName()).isEqualTo(actual.getName());
        assertThat(actual.getPhoneNumber()).isEqualTo(actual.getPhoneNumber());
        assertThat(actual.getEmail()).isEqualTo(actual.getEmail());
        assertThat(actual.getStatus()).isEqualTo(MemberStatus.TEMPORARY);

    }

    @Test
    void memberId_를_통해_회원_조회_실패시_NotFoundException_발생() {

        Long memberId = 1L;

        when(memberRepository.findById(memberId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> memberService.getMemberById(memberId))
            .isInstanceOf(NotFoundMemberException.class);
    }

    private Member createNewParticipant(String name, String email, String phoneNumber) {
        return Member.newParticipant(name, email, phoneNumber);
    }


}