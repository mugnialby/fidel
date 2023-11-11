package com.alby.feedbackservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "feedback")
@EntityListeners({
        AuditingEntityListener.class
})
public class Feedback {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "feedback_id_seq"
    )
    @SequenceGenerator(
            name = "feedback_id_seq",
            sequenceName = "feedback_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "to_user_id")
    private Long toUserId;

    @Column(name =  "feedback_type_id")
    private int feedbackTypeId;

    private String contents;

    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_at")
    private Instant modifiedAt;
}
