package org.example.bazaaranalyze.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECURITY_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9xeyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3Mzg3NTAwMTQsImV4cCI6MTc3MDI4NjAxNCwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0x8VbMyli3whlKirblzf68sLad7yKxxmpgeESrrolFzDRmmOTxBLthciwrVQkmVSJz9lzCPZZZtbinh9zkfnGAbw";
    private final long EXPIRATION_TIME = 86_400_000L;

    // Create token
    public String generateToken(String token) {
        return Jwts
                .builder()
                .setSubject(token)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .compact();
    }

    // Valid token

    // Check token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Use token

    // Get token in User
}
