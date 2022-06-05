package io.khusanjon.onlineclassroom.model.domain;

import io.khusanjon.onlineclassroom.model.domain.audit.DateAudit;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/24/22 - 4:32 PM
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File extends DateAudit {
    @Id
    private Long id;

    private String topic;

    private String lecturer;

    private String path;

    private String url;

    private String contentType;

    private Long subjectId;

    private Long staffChairId;

    @ManyToOne
    private Staff staff;
}
