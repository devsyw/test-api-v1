package com.starbridge.webservice.api.auth.entity;

import com.starbridge.webservice.api.auth.entity.type.UserType;
import com.starbridge.webservice.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    private String name; // 이름
    private String email; // 이메일
    private String phoneNumber; // 전화번호
    private String platform; // 유입 플랫폼 (OAuth2 provider)

    private String accountNumber; // 계좌번호
    @Enumerated(EnumType.STRING)
    private UserType userType; // 회원 유형 (Enum)
    private String gender; // 성별
    private String category; // 카테고리
}