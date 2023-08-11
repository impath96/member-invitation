package com.member.invitation.memberinvitation.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

}
