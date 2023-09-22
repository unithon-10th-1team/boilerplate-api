package com.flickspick.auth.application;

import static com.flickspick.auth.AuthConstants.AUTH_TOKEN_HEADER_KEY;

import com.flickspick.auth.model.AuthToken;
import com.flickspick.auth.model.AuthUser;
import com.flickspick.auth.model.AuthUserImpl;
import com.flickspick.exception.AuthorizationException;
import com.flickspick.exception.dto.ErrorType;
import com.flickspick.user.infrastructure.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenService {
    // private final long accessTokenValidMillisecond = 1000L * 60 * 100000; // AccessToken 30초 토큰
    // 유효
    private final UserRepository userRepository;
    private String key;

    @Value("${jwt.secret.key}")
    public void getSecretKey(String secretKey) {
        log.info("secret key {}", secretKey);
        key = secretKey;
    }

    public String getAuthToken(HttpServletRequest request) {
        String accessToken = request.getHeader(AUTH_TOKEN_HEADER_KEY); // 인증토큰 값 가져오기

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
        var user =
                userRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new AuthorizationException(ErrorType.AUTHORIZATION_ERROR));
        return new AuthUserImpl(id);
    }

    public Long getUserIdFromToken(String token) {
        return Long.valueOf(
                (Integer)
                        Jwts.parser()
                                .setSigningKey(key.getBytes())
                                .parseClaimsJws(token)
                                .getBody()
                                .get("uid"));
    }

    public String jwtBuilder(Long id, String nickname) {
        Claims claims = Jwts.claims();
        claims.put("nickname", nickname);
        claims.put("uid", id);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                // TODO : 유효기간 설정은 다음 MVP에서 진행한다.
                // .setExpiration(new Date(now.getTime() + accessTokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
    }
}
