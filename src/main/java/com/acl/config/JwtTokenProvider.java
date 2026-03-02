//package com.acl.config;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import java.util.Base64;
//import java.util.Date;
//import javax.crypto.SecretKey;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtTokenProvider {
//
//  private final String jwtSecret;
//  private final int jwtExpirationInMs;
//  private final java.security.Key key;
//
//  public JwtTokenProvider(@Value("${jwt.secret}") String jwtSecret,
//      @Value("${jwt.expiration}") int jwtExpirationInMs) {
//    this.jwtSecret = jwtSecret;
//    this.jwtExpirationInMs = jwtExpirationInMs;
//    byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
//    this.key = Keys.hmacShaKeyFor(keyBytes);
//  }
//
//  public String generateToken(Authentication authentication) {
//    String username = authentication.getName();
//    Date now = new Date();
//    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
//    return Jwts.builder().subject(username).issuedAt(now).expiration(expiryDate).signWith(key)
//        .compact();
//  }
//
//  public String getUsernameFromJWT(String token) {
//    return Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload()
//        .getSubject();
//  }
//
//  public boolean validateToken(String token) {
//    try {
//      Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token);
//      return true;
//    } catch (Exception e) {
//      return false;
//    }
//  }
//}
