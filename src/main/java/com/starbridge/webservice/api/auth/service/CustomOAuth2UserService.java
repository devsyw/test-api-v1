package com.starbridge.webservice.api.auth.service;

import com.starbridge.webservice.api.auth.entity.User;
import com.starbridge.webservice.api.auth.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = defaultOAuth2UserService.loadUser(userRequest);

        // 유입 플랫폼 확인 (Google, Facebook 등)
        String platform = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oauth2User.getAttributes();

        // 사용자 정보 추출 (OAuth2 플랫폼에 따라 필드명이 다를 수 있음)
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");
        String phoneNumber = (String) attributes.getOrDefault("phone_number", ""); // 전화번호가 제공되지 않을 경우 공백

        // DB에 저장할 사용자 정보 설정
        User user = userRepository.findByEmail(email)
                .orElse(User.builder()
                        .name(name)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .platform(platform)
                        .build());

        userRepository.save(user);

        return new DefaultOAuth2User(
                oauth2User.getAuthorities(),
                oauth2User.getAttributes(),
                "name"); // 인증 후 기본 식별 필드로 name 사용
    }
}
