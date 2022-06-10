package com.example.Tissue_back.service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Date;

@Service
public class SecurityService {

    public String createToken(String memberId, String memberPw, long expTime) {
        if(expTime <= 0) {
            throw new RuntimeException("만료시간이 0보다 커야함");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] pwBytes = DatatypeConverter.parseBase64Binary(memberPw);
        Key signingKey = new SecretKeySpec(pwBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(memberId)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }
}
