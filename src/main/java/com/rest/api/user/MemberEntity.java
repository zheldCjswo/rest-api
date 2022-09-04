package com.rest.api.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
@EqualsAndHashCode(of = "id")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private long id;

    private String email;

    private String name;
}