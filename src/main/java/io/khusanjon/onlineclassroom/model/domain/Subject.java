package io.khusanjon.onlineclassroom.model.domain;

import io.khusanjon.onlineclassroom.model.domain.audit.DateAudit;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/24/22 - 4:37 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Subject extends DateAudit {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
}
