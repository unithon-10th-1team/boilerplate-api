package com.flickspick.auth.application;

import com.flickspick.auth.model.AuthToken;
import com.flickspick.auth.model.AuthUser;
import com.flickspick.auth.model.AuthUserImpl;
import com.flickspick.user.infrastructure.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.flickspick.auth.AuthConstants.AUTH_TOKEN_HEADER_KEY;

@Component
@RequiredArgsConstructor
public class TokenService {
    private final long accessTokenValidMillisecond = 1000L * 60 * 100000; // AccessToken 30초 토큰 유효
    private final long refreshTokenValidMillisecond = 1000L * 60 * 1; // 1분 토큰 유효
    private final UserRepository userRepository;
    private static String key = "a025a0872a66ae40a8528c56293bb576bfb8fba17c797c198cc886ad41e4f42de62620a224b7aff763b51430fa00f47269609d0cdc4ec54903e7c3e23b855280";

    public String getAuthToken(HttpServletRequest request) {
        String accessToken = request.getHeader(AUTH_TOKEN_HEADER_KEY); //인증토큰 값 가져오기

        if (accessToken.isEmpty()) {
            return null;
        }

        return accessToken;
    }

    public void verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token);
        } catch (Exception e) {
            if (e.getMessage().contains("JWT expired")) {
                throw new RuntimeException();
            }
            throw new RuntimeException();
        }
        Long uid = getUserIdFromToken(token);
        if (!userRepository.existsById(uid)) {
            throw new RuntimeException();
        }
    }

    public AuthUser getAuthUser(AuthToken token) {
        verifyToken(token.getToken());
        var id = getUserIdFromToken(token.getToken());
        var user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return new AuthUserImpl(id);
    }

    public Long getUserIdFromToken(String token) {
        return Long.valueOf((Integer) Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody().get("uid"));
    }

    public String jwtBuilder(Long id, String nickname) {
        Claims claims = Jwts.claims().setSubject(nickname);
        claims.put("uid", id);
        Date now = new Date();
        return Jwts.builder().setClaims(claims)
                .setClaims(claims)
                .setIssuedAt(now)
                .setIssuer(String.valueOf(id))
                .setExpiration(new Date(now.getTime() + accessTokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
    }
}
