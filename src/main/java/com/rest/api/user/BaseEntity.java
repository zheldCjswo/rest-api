package com.rest.api.user;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
abstract class BaseEntity {

    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;

    @PrePersist
    public void createAt(){
        this.createAt = ZonedDateTime.now();
        this.updateAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void updateAt(){
        this.updateAt = ZonedDateTime.now();
    }
}
