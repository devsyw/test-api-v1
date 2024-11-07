package com.starbridge.webservice.api.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    /**
     * 1. Spring Security 설정에서 /api/login으로 OAuth2 인증 경로 설정 및 /api/oauth2/success로 인증 성공 후 리다이렉트 설정.
     * 2. AuthController에서 /api/oauth2/success 엔드포인트를 통해 인증 후 사용자 정보를 JSON으로 반환.
     * 3. React에서 /api/login으로 OAuth2 인증을 시작하고, /api/oauth2/success에서 사용자 정보를 받아 처리.
     */
    @GetMapping("/oauth2/success")
    public Map<String, Object> loginSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("message", "Welcome, " + username + "!");
        // 필요한 경우 JWT 토큰을 발급하여 response에 추가

        return response;
    }
}

/**
 * // React 컴포넌트 예시
 * import React, { useState } from 'react';
 *
 * function ResourceFetcher() {
 *   const [data, setData] = useState(null);
 *   const [error, setError] = useState(null);
 *
 *   const fetchData = async () => {
 *     try {
 *       const response = await fetch('/api/resource');
 *       if (!response.ok) {
 *         const errorData = await response.json();
 *         throw new Error(errorData.message || 'Failed to fetch data');
 *       }
 *       const result = await response.json();
 *       setData(result);
 *     } catch (err) {
 *       setError(err.message);
 *     }
 *   };
 *
 *   return (
 *     <div>
 *       <button onClick={fetchData}>Fetch Resource</button>
 *       {data && <div>Data: {JSON.stringify(data)}</div>}
 *       {error && <div style={{ color: 'red' }}>Error: {error}</div>}
 *     </div>
 *   );
 * }
 *
 * export default ResourceFetcher;
 */