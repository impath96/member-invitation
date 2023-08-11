package com.member.invitation.memberinvitation.exception;

public class NotFoundMemberException extends RuntimeException{

    private static final String MESSAGE_FORMAT = "회원을 찾을 수 없습니다. [memberId=%s]";

    public NotFoundMemberException(final Long memberId) {
        super(String.format(MESSAGE_FORMAT, memberId));
    }

}
