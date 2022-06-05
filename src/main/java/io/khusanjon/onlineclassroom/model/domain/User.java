package io.khusanjon.onlineclassroom.model.domain;

import io.khusanjon.onlineclassroom.model.domain.audit.UserDateAudit;
import lombok.*;

import javax.persistence.*;

/**
 * @author Tuychiboev Khusanjon
 * @created 1/15/22 - 3:41 PM
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity(name = "users")
public class User extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(name = "phone1")
    private String phone1;

    @Column(name = "phone2")
    private String phone2;

    private String guarantorFullName;

    @Column(name = "guarantor_phone")
    private String guarantorPhone;

    @Builder.Default
    private Boolean deleted = false;

    @Builder.Default
    private Boolean active = true;

    public User() {
        active = true;
        deleted = false;
    }
}
