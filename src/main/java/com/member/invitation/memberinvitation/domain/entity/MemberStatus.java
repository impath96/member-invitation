package com.member.invitation.memberinvitation.domain.entity;

import lombok.Getter;

@Getter
public enum MemberStatus {

    TEMPORARY("임시 가입 상태"),
    ACTIVATE("계정 활성화 상태"),
    DEACTIVATE("계정 비활성화 상태"),
    WITHDRAWAL("계정 탈퇴 상태"),
    ;

    private final String description;

    MemberStatus(final String description) {
        this.description = description;
    }

}
