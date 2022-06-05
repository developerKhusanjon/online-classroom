package io.khusanjon.onlineclassroom.model.domain;

import io.khusanjon.onlineclassroom.model.domain.audit.DateAudit;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/24/22 - 4:56 PM
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class StaffChair extends DateAudit {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
}
