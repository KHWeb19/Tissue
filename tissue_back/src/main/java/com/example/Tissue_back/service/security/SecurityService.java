package com.example.Tissue_back.service.security;

import com.example.Tissue_back.entity.member.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Service
@Slf4j
public class SecurityService {

    private final String secretKey = "lalatissuetissuetissue20220620lala";

    public String createToken(String memberId, Long memberNo, Role roles, long expTime) {
        if(expTime <= 0) {
            throw new RuntimeException("만료시간이 0보다 커야함");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

//        byte[] pwBytes = DatatypeConverter.parseBase64Binary(secretKey);
//        Key signingKey = new SecretKeySpec(pwBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims().setSubject(memberId);
        claims.put("roles", roles);
        claims.put("memberNo", memberNo);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();

    }
    public String createRefreshToken() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + (3 * 24 * 60 * 1000 * 60)))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public String getMemberId (String token) {

        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public Claims getRole (String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token).getBody();
    }

    public JwtCode validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(token);
            return JwtCode.ACCESS;
        } catch (ExpiredJwtException e){
            // 만료된 경우에는 refresh token을 확인하기 위해
            return JwtCode.EXPIRED;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("jwtException : {}", e);
        }
        return JwtCode.DENIED;
    }


    public static enum JwtCode{
        DENIED,
        ACCESS,
        EXPIRED;
    }

}
