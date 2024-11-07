package com.starbridge.webservice.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 생성 날짜

    @LastModifiedDate
    private LocalDateTime updatedAt; // 업데이트 날짜

    @Column(updatable = false)
    private String createdBy; // 만든 사람

    private String updatedBy; // 업데이트한 사람
}