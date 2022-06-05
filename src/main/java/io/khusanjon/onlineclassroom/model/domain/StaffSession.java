package io.khusanjon.onlineclassroom.model.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Staff staff;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String token;


    private String deviceIp;

    private String deviceModel;

    private String deviceOsVersion;

    @CreationTimestamp
    private LocalDateTime loginedAt;

}
