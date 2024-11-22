package com.bridge.webservice.api.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        String serverIp = "알 수 없음";
        try {
            // 현재 서버의 호스트 이름과 IP 주소를 가져옴
            InetAddress inetAddress = InetAddress.getLocalHost();
            serverIp = inetAddress.getHostAddress(); // IP 주소 추출
        } catch (UnknownHostException e) {
            // 예외 발생 시 기본 메시지 유지
            e.printStackTrace();
        }

        // 응답 메시지 생성
        String responseMessage = """
            서버와 성공적으로 통신하셨습니다.(v1)
            [서버 IP: %s]
        """.formatted(serverIp); // JDK 17의 텍스트 블록 및 `formatted` 메서드 사용

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}