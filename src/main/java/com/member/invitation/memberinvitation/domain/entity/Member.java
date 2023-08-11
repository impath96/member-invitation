package com.member.invitation.memberinvitation.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private Member(String name, String email, String phoneNumber, MemberStatus status) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public static Member newParticipant(String name, String email, String phoneNumber) {
        return new Member(name, email, phoneNumber, MemberStatus.TEMPORARY);
    }

    public void activate() {
        this.status = MemberStatus.ACTIVATE;
    }

}
