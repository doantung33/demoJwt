package com.example.jwt.security;

import com.example.jwt.model.UserDetail;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
@Service
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${bezkoder.app.jwtSecret}")
    private  String jwtSecret;
    @Value("${bezkoder.app.jwtExpirationMs}")
    private  int jwtExpiration;
    public String generateJwtToken(Authentication authentication){
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userDetail.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature  ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token ", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token ", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty", e);
        }
        return false;
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

}
