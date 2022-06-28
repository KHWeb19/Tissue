package com.example.Tissue_back.service.security;

import com.example.Tissue_back.entity.member.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;

@Service
public class SecurityService {

    private final String secretKey = "lalatissuetissuetissue20220620lala";

    public String createToken(String memberId, Role roles, long expTime) {
        if(expTime <= 0) {
            throw new RuntimeException("만료시간이 0보다 커야함");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

//        byte[] pwBytes = DatatypeConverter.parseBase64Binary(secretKey);
//        Key signingKey = new SecretKeySpec(pwBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims().setSubject(memberId);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();

    }

    public String getMemberId (String token) {

        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token).getBody().getSubject()
;    }

    public Claims getRole (String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token).getBody();
    }

}
